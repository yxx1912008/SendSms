package cc.openkit.admin.controller.g;

import cc.openkit.admin.model.Admin;
import cc.openkit.admin.model.GGroup;
import cc.openkit.admin.model.GGroupLimit;
import cc.openkit.admin.model.GLimit;
import cc.openkit.admin.service.admin.AdminService;
import cc.openkit.admin.service.g.GGroupLimitService;
import cc.openkit.admin.service.g.GGroupService;
import cc.openkit.admin.service.g.GLimitService;
import cc.openkit.admin.util.StaticFinalVar;
import cc.openkit.admin.vo.GLimitVo;
import cc.openkit.common.KitUtil;
import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 后台用户组管理
 *
 * @author bigben
 * @date 2018.2.5
 */
@Controller
@Scope("prototype")
@RequestMapping("/gGroup")
public class GGroupController {
    private Logger log = Logger.getLogger(GGroupController.class);

    @Resource
    private GGroupService gGroupService;
    @Resource
    private GGroupLimitService gGroupLimitService;
    @Resource
    private GLimitService gLimitService;
    @Resource
    private AdminService adminService;

    /**
     * 跳转到所有的分组页面
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public ModelAndView getAll(HttpServletRequest request){
        // 权限验证
        GGroupLimit gGroupLimit = gGroupLimitService.testGroup(request, 4);
        ModelAndView mv = new ModelAndView();
        // 没有权限，返回错误页面
        if(gGroupLimit==null){
            mv.setViewName("/admin/err/no_group_err");
            mv.addObject("msg", StaticFinalVar.NO_GROUP_MSG+StaticFinalVar.CALL_GROUP);
            return mv;
        }
        // 取值
        mv.setViewName("/admin/group/list");
        mv.addObject("kitG",gGroupLimit);
        return mv;
    }

    /**
     * 当跳转到列表页面开始渲染数据
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/getAllJson", method = RequestMethod.POST)
    @ResponseBody
    public Object getAllJson(HttpServletRequest request){
        log.info("查看所有的分组，返回 json");

        Map<String,Object> map = new HashMap<String,Object>();

        String page = request.getParameter("page");// 获得页数
        String limit = request.getParameter("limit");// 获得每页显示条数
        String search = request.getParameter("search");// 获取搜索条件

//        封装数据
        GGroup gGroup = new GGroup();
        gGroup.setGroupName(search);

        // 分页查询
        List<GGroup> gGroupList = gGroupService.queryPageListByWhere(gGroup, Integer.valueOf(page), Integer.valueOf(limit));
        int size = gGroupService.queryCount(gGroup);
        // 返回数据
        map.put("code",0);
        map.put("msg","");
        map.put("count",size);
        map.put("data",gGroupList);

        return JSONObject.toJSON(map);
    }

    /**
     * 去添加页面
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/goAdd", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView goAdd(HttpServletRequest request){
        log.info("分组 》 添加 》 跳转");
        ModelAndView mv = new ModelAndView();
        List<GLimit> gLimits = gLimitService.queryAll();
        mv.setViewName("/admin/group/add");
        mv.addObject("kitList",gLimits);
        return mv;
    }

    /**
     * 添加
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object add(HttpServletRequest request){

        Map<String, Object> map = new HashMap<String, Object>();

        log.info("分组 》 添加 》 保存");
        // 获取分组名字
        String groupName = request.getParameter("groupName");
        // 先保存组
        GGroup gGroup = new GGroup();
        gGroup.setGroupName(groupName);
        // 查询是否有重复的分组
        List<GGroup> gGroupList = gGroupService.getListByGroupName(gGroup);
        // 如果有重复
        if(gGroupList.size()>0){
            map = KitUtil.returnMap("101",StaticFinalVar.IS_NOT_NULL);
            return JSONObject.toJSON(map);
        }
        // 如果没有重复，先添加，然后查询这条数据
        int i = gGroupService.save(gGroup);
        if(i!=1){
            map = KitUtil.returnMap("101",StaticFinalVar.ADD_ERR);
            return JSONObject.toJSON(map);
        }
        // 添加成功
        List<GGroup> gGroups = gGroupService.getListByGroupName(gGroup);
        GGroup g = gGroups.get(0);  //  刚刚添加的对象的参数


        // 遍历权限
        addGroupLimit(request, g);
        map = KitUtil.returnMap("200",StaticFinalVar.ADD_OK);
        return JSONObject.toJSON(map);
    }

    /**
     * 删除
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    @ResponseBody
    public Object del(HttpServletRequest request){
        Map<String,Object> map = new HashMap<String, Object>();

        String id = request.getParameter("id");

        Admin admin = new Admin();
        admin.setGroupId(Integer.valueOf(id));
        // 查看分组里边有没有用户
        if(adminService.queryCount(admin)>0){
            return KitUtil.returnMap("101",StaticFinalVar.DEL_GROUP_MSG);
        }
        // 删除 grouplimit 下面的 所有的权限
        gGroupLimitService.delByGroupId(Integer.valueOf(id));
        // 删除 组
        return gGroupService.deleteById(Integer.parseInt(id))>0 ? KitUtil.returnMap("200",StaticFinalVar.DEL_OK) : KitUtil.returnMap("101",StaticFinalVar.DEL_ERR);
    }

    /**
     * 去编辑页面
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/goUpdate", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView goUpdate(HttpServletRequest request){
        log.info("分组 》 修改 》 去修改页面");
        ModelAndView mv = new ModelAndView();
        String id = request.getParameter("id");
        List<GLimit> gLimits = gLimitService.queryAll();
        List<GGroupLimit> gGroupLimits = gGroupLimitService.getListByGroupId(Integer.valueOf(id));
        GGroup gGroup = gGroupService.queryById(Integer.parseInt(id));


        // 遍历，获取新的对象集合
        List<GLimitVo> gLimitVos = new ArrayList<GLimitVo>();

        for(int i=0; i<gLimits.size(); i++){
            GLimitVo gLimitVo = new GLimitVo(
                    gLimits.get(i).getLimitId(),
                    gLimits.get(i).getLimitSequence(),
                    gLimits.get(i).getLimitIco(),
                    gLimits.get(i).getLimitTitle(),
                    gLimits.get(i).getLimitAction(),
                    gLimits.get(i).getLimitLeaderid(),
                    gLimits.get(i).getLimitSystem()
            );

            for(int j=0; j<gGroupLimits.size(); j++){
                if(gGroupLimits.get(j).getLimitId().equals(gLimits.get(i).getLimitId())){
                    gLimitVo.setType(1);
                    gLimitVo.setC(gGroupLimits.get(j).getGroupC());
                    gLimitVo.setR(gGroupLimits.get(j).getGroupR());
                    gLimitVo.setU(gGroupLimits.get(j).getGroupU());
                    gLimitVo.setD(gGroupLimits.get(j).getGroupD());
                }
            }
            gLimitVos.add(gLimitVo);
        }

        mv.setViewName("/admin/group/update");
        mv.addObject("kitList",gLimitVos);
        mv.addObject("kitModel",gGroup);
        return mv;
    }

    /**
     * 编辑
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Object update(HttpServletRequest request){
        Map<String, Object> map = new HashMap<String, Object>();

        log.info("分组 》 修改 》 保存");
        // 获取分组的id
        String groupId = request.getParameter("groupId");
        // 获取分组名字
        String groupName = request.getParameter("groupName");
        // 先根据ID寻找这个分组的对象 // 验证民资是否修改了
        GGroup g = gGroupService.queryById(Integer.parseInt(groupId));
        if (!g.getGroupName().equals(groupName)) {
            // 验证名字是否有重复
            GGroup gGroup = new GGroup();
            gGroup.setGroupName(groupName);
            // 查询是否有重复的分组
            List<GGroup> gGroupList = gGroupService.getListByGroupName(gGroup);
            // 如果有重复
            if(gGroupList.size()>0){
                map = KitUtil.returnMap("101",StaticFinalVar.IS_NOT_NULL);
                return JSONObject.toJSON(map);
            }
            // 被修改了名字，而且数据库没有这个重复的分组，我就要去保存分组
            gGroup.setGroupId(Integer.valueOf(groupId));
            int i = gGroupService.update(gGroup);
            if(i!=1){
                map = KitUtil.returnMap("101",StaticFinalVar.UPDATE_ERR);
                return JSONObject.toJSON(map);
            }
        }
        // 到这里标识上面的说有的添加都是OK的
        // 下面开始，先根据我们的  groupid  删除他的所有权限
        gGroupLimitService.delByGroupId(Integer.valueOf(groupId));
        addGroupLimit(request, g);

        map = KitUtil.returnMap("200",StaticFinalVar.UPDATE_OK);
        return JSONObject.toJSON(map);
    }

    private void addGroupLimit(HttpServletRequest request, GGroup g) {
        // 遍历权限
        Enumeration<String> paraNames=request.getParameterNames();
        for(Enumeration<String> e=paraNames;e.hasMoreElements();){

            String thisName=e.nextElement().toString();  //  传来的key值
            String thisValue=request.getParameter(thisName);  //  传来的key值带的参数

            if(!"groupName".equals(thisName)){
                // 根据 _ 切割 key 字符串
                String[] key = thisName.split("_");

                // 如果是一级类目
                if("limitone".equals(key[0])){
                    // 保存说明有这个权限，而且都有
                    GGroupLimit gGroupLimit = new GGroupLimit();
                    gGroupLimit.setGlId(KitUtil.uuid());
                    gGroupLimit.setGroupId(g.getGroupId());
                    gGroupLimit.setLimitId(Integer.valueOf(thisValue));
                    gGroupLimit.setGroupC(1);
                    gGroupLimit.setGroupR(1);
                    gGroupLimit.setGroupU(1);
                    gGroupLimit.setGroupD(1);
                    gGroupLimitService.save(gGroupLimit);
                }

                // 如果是二级类目
                if("limittwo0".equals(key[0])){
                    // 保存说明有这个权限，遍历查看有没有 crud
                    GGroupLimit gGroupLimit = new GGroupLimit();
                    gGroupLimit.setGlId(KitUtil.uuid());
                    gGroupLimit.setGroupId(g.getGroupId());
                    gGroupLimit.setLimitId(Integer.valueOf(thisValue));

                    String c = request.getParameter("limittwo1_"+thisValue);
                    String r = request.getParameter("limittwo2_"+thisValue);
                    String u = request.getParameter("limittwo3_"+thisValue);
                    String d = request.getParameter("limittwo4_"+thisValue);

                    gGroupLimit.setGroupC("1".equals(c)?1:2);
                    gGroupLimit.setGroupR("1".equals(r)?1:2);
                    gGroupLimit.setGroupU("1".equals(u)?1:2);
                    gGroupLimit.setGroupD("1".equals(d)?1:2);
                    gGroupLimitService.save(gGroupLimit);
                }

            }
        }
    }
}
