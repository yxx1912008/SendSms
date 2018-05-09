package cc.openkit.admin.controller.admin;

import cc.openkit.admin.model.*;
import cc.openkit.admin.service.admin.AdminService;
import cc.openkit.admin.service.g.GGroupLimitService;
import cc.openkit.admin.service.g.GGroupService;
import cc.openkit.admin.service.web.WebSettingService;
import cc.openkit.admin.util.AppUtil;
import cc.openkit.admin.util.StaticFinalVar;
import cc.openkit.admin.vo.AdminVo;
import cc.openkit.admin.vo.LimitVo;
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
import javax.json.Json;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 后台管理员类的方法
 *
 * @author bigben
 * @date 2018.2.5
 */
@Controller
@Scope("prototype")
@RequestMapping("/admin")
public class AdminController {
    private Logger log = Logger.getLogger(AdminController.class);

    @Resource
    private AdminService adminService;
    @Resource
    private WebSettingService webSettingService;
    @Resource
    private GGroupLimitService gGroupLimitService;
    @Resource
    private GGroupService gGroupService;


    /**
     * 用户后台登录页面跳转
     * @return
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView index(HttpSession session){
        log.info("用户登录跳转方法");
        log.debug("测试debug");
        WebSetting webSetting = webSettingService.queryById(1);
        // 新建返回值对象
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/admin/login");
        session.setAttribute("kitWeb",webSetting);
        return mv;
    }

    /**
     * 登录验证方法
     * @param request
     * @param session
     * @return
     */
    @RequestMapping(value = "/tologin", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView tologin(HttpServletRequest request, HttpSession session){
        log.info("开始登陆了");
        // 新建返回值对象
        ModelAndView mv = new ModelAndView();
        // 取值
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // 组装对象
        Admin admin = new Admin();
        admin.setKitAdminUsername(username);
        admin.setKitAdminPassword(password);

        // 查询
        Admin returnAdmin = adminService.getModelByUsernameAndPassword(admin);

        // 判断
        if(returnAdmin==null){
            // 如果为空，跳转到登录页面
            mv.setViewName("/admin/login");
            mv.addObject("msg", StaticFinalVar.USERNAME_OR_PWD_ERR);
            return mv;
        }

        // 得到他的权限
        List<LimitVo> limitVoList = gGroupLimitService.getModelByGroupId(returnAdmin.getGroupId());
        log.info("他的权限有 "+limitVoList.size()+" 个！");


        // 获取他的功能模块
        mv.setViewName("/admin/index");
        session.setAttribute("admin",returnAdmin);
        session.setAttribute("limitList",limitVoList);
        return mv;
    }

    /**
     * 后台用户退出方法
     * @param session
     * @return
     */
    @RequestMapping(value = "/uplogin", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView uplogin(HttpSession session){
        log.info("后台退出");
        // 新建返回值对象
        ModelAndView mv = new ModelAndView();
        WebSetting webSetting = webSettingService.queryById(1);
        // 新建返回值对象
        session.setAttribute("kitWeb",webSetting);
        session.removeAttribute("admin");
        session.removeAttribute("limitList");
        mv.setViewName("/admin/login");
        return mv;
    }

    /**
     * 跳转到所有的用户页面
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public ModelAndView getAll(HttpServletRequest request){
        // 权限验证
        GGroupLimit gGroupLimit = gGroupLimitService.testGroup(request, 5);
        ModelAndView mv = new ModelAndView();
        // 没有权限，返回错误页面
        if(gGroupLimit==null){
            mv.setViewName("/admin/err/no_group_err");
            mv.addObject("msg", StaticFinalVar.NO_GROUP_MSG+StaticFinalVar.CALL_GROUP);
            return mv;
        }
        // 取值
        mv.setViewName("/admin/admin/list");
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
        log.info("查看所有的管理员，返回 json");

        Map<String,Object> map = new HashMap<String,Object>();

        String page = request.getParameter("page");// 获得页数
        String limit = request.getParameter("limit");// 获得每页显示条数
        String search = request.getParameter("search");// 获取搜索条件

//        封装数据
        Admin admin = new Admin();
        if(KitUtil.feikong(search)){
            admin.setKitAdminName(search);
        }

        // 分页查询
        List<Admin> adminList = adminService.queryPageListByWhere(admin, Integer.valueOf(page), Integer.valueOf(limit));
        int size = adminService.queryCount(admin);

        List<GGroup> gGroupList = gGroupService.queryAll();
        // 封装返回数据
        List<AdminVo> adminVoList = new ArrayList<AdminVo>();
        for(int i=0; i<adminList.size(); i++){
            AdminVo adminVo = new AdminVo(adminList.get(i));
            for(int j=0; j<gGroupList.size(); j++){
                if(adminList.get(i).getGroupId().equals(gGroupList.get(j).getGroupId())){
                    adminVo.setGroupName(gGroupList.get(j).getGroupName());
                }
            }
            adminVoList.add(adminVo);
        }

        System.out.println("抓到的数据总数："+adminVoList.size());

        // 返回数据
        map.put("code",0);
        map.put("msg","");
        map.put("count",size);
        map.put("data",adminVoList);

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
        log.info("用户 》 添加 》 跳转");
        ModelAndView mv = new ModelAndView();
        List<GGroup> groups = gGroupService.queryAll();
        mv.setViewName("/admin/admin/add");
        mv.addObject("kitList",groups);
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
        log.info("用户 》 添加 》 保存");
        String kitAdminName = request.getParameter("kitAdminName");
        String imgurl = request.getParameter("imgurl");
        String kitAdminUsername = request.getParameter("kitAdminUsername");
        String kitAdminPassword = request.getParameter("kitAdminPassword");
        String kitAdminPasswordAgen = request.getParameter("kitAdminPasswordAgen");
        String groupId = request.getParameter("groupId");

        if(!kitAdminPassword.equals(kitAdminPasswordAgen)){
            return KitUtil.returnMap("200",StaticFinalVar.PWD_NOT_EQUERY);
        }

        Admin admin = new Admin();
        admin.setKitAdminId(KitUtil.uuid());
        admin.setKitAdminName(kitAdminName);
        admin.setKitAdminUsername(kitAdminUsername);
        admin.setKitAdminImgUrl(imgurl);
        admin.setKitAdminPassword(AppUtil.md5pwd(kitAdminPassword));
        admin.setGroupId(Integer.valueOf(groupId));

        return JSONObject.toJSON(adminService.save(admin)==1 ? KitUtil.returnMap("200",StaticFinalVar.ADD_OK) : KitUtil.returnMap("101",StaticFinalVar.ADD_ERR));
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
        log.info("用户 》 删除");
        String id = request.getParameter("id");
        // 获取session
        HttpSession session = request.getSession();
        Admin admin = (Admin) session.getAttribute("admin");
        // 如果被删除的 是当前登录的用户
        if(admin.getGroupId().equals(id)){
            return KitUtil.returnMap("101",StaticFinalVar.DEL_ADMIN_BUT_IS_LOGIN);
        }

        int i = adminService.deleteByUUId(id);
        return i==1?KitUtil.returnMap("200",StaticFinalVar.DEL_OK):KitUtil.returnMap("101",StaticFinalVar.DEL_ERR);

    }

    /**
     * 去修改页面
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/goUpdate", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView goUpdate(HttpServletRequest request){
        log.info("用户 》 修改 》 跳转");
        ModelAndView mv = new ModelAndView();
        String id = request.getParameter("id");
        Admin admin = adminService.queryByUUID(id);
        List<GGroup> groups = gGroupService.queryAll();
        mv.setViewName("/admin/admin/update");
        mv.addObject("kitList",groups);
        mv.addObject("kitModel",admin);
        return mv;
    }

    /**
     * 修改
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Object update(HttpServletRequest request){
        log.info("用户 》 修改 》 保存");
        String kitAdminId = request.getParameter("kitAdminId");
        String kitAdminName = request.getParameter("kitAdminName");
        String imgurl = request.getParameter("imgurl");
        String kitAdminUsername = request.getParameter("kitAdminUsername");
        String kitAdminPassword = request.getParameter("kitAdminPassword");
        String kitAdminPasswordAgen = request.getParameter("kitAdminPasswordAgen");
        String groupId = request.getParameter("groupId");

        if(!kitAdminPassword.equals(kitAdminPasswordAgen)){
            return KitUtil.returnMap("200",StaticFinalVar.PWD_NOT_EQUERY);
        }

        Admin admin = new Admin();
        admin.setKitAdminId(kitAdminId);
        admin.setKitAdminName(kitAdminName);
        admin.setKitAdminUsername(kitAdminUsername);
        admin.setKitAdminImgUrl(imgurl);
        if(kitAdminPassword!=null && !"".equals(kitAdminPassword)) {
            admin.setKitAdminPassword(AppUtil.md5pwd(kitAdminPassword));
        }
        admin.setGroupId(Integer.valueOf(groupId));

        int i = adminService.updateByAdminId(admin);
//        int i = adminService.save(admin);
        System.out.println(i);

        return JSONObject.toJSON(i==1 ? KitUtil.returnMap("200",StaticFinalVar.UPDATE_OK) : KitUtil.returnMap("101",StaticFinalVar.UPDATE_ERR));
    }
}
