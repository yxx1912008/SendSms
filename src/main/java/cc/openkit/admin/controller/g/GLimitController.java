package cc.openkit.admin.controller.g;

import cc.openkit.admin.model.GGroupLimit;
import cc.openkit.admin.model.GLimit;
import cc.openkit.admin.service.g.GGroupLimitService;
import cc.openkit.admin.service.g.GLimitService;
import cc.openkit.admin.util.StaticFinalVar;
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
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 所有的菜单栏目列表
 *
 * @author bigben
 * @date 2018.2.5
 */
@Controller
@Scope("prototype")
@RequestMapping("/gLimit")
public class GLimitController {
    private Logger log = Logger.getLogger(GLimitController.class);

    @Resource
    private GLimitService gLimitService;
    @Resource
    private GGroupLimitService gGroupLimitService;

    /**
     * 获取所有 action
     * @param request
     * @return
     */
    @RequestMapping(value = "/getGroup", method = RequestMethod.GET)
    @ResponseBody
    public Object getGroup(HttpServletRequest request){
        // 权限验证
        GGroupLimit gGroupLimit = gGroupLimitService.testGroup(request, 2);
        ModelAndView mv = new ModelAndView();
        // 没有权限，返回错误页面
        if(gGroupLimit==null){
            mv.setViewName("/admin/err/no_group_err");
            mv.addObject("msg",StaticFinalVar.NO_GROUP_MSG+StaticFinalVar.CALL_GROUP);
            return mv;
        }

        // 取值
        List<GLimit> limitList = gLimitService.queryAll();
        mv.setViewName("/admin/menu/menu_set");
        mv.addObject("kitList",limitList);
        mv.addObject("kitG",gGroupLimit);
        return mv;
    }

    /**
     * 添加跳转
     * @param request
     * @return
     */
    @RequestMapping(value = "/goAdd", method = RequestMethod.GET)
    @ResponseBody
    public Object goAdd(HttpServletRequest request){
        ModelAndView mv = new ModelAndView();
        GLimit gLimit = new GLimit();
        gLimit.setLimitLeaderid(0);
        List<GLimit> limits = gLimitService.queryListByWhere(gLimit);
        mv.setViewName("/admin/menu/add");
        mv.addObject("kitList",limits);
        return mv;
    }

    /**
     * 添加提交
     * @param request
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object add(HttpServletRequest request){
        log.info("菜单管理 > 添加 > 提交");
        // 获取参数
        String limitTitle = request.getParameter("limitTitle");
        String limitSequence = request.getParameter("limitSequence");
        String isFu = request.getParameter("isFu");
        String limitLeaderid = request.getParameter("limitLeaderid");
        String limitAction = request.getParameter("limitAction");
        String isIco = request.getParameter("isIco");
        String isRevise = request.getParameter("isRevise");

        // 判断封装对象
        GLimit gLimit = new GLimit();
        gLimit.setLimitTitle(limitTitle);
        gLimit.setLimitSequence(Integer.valueOf(limitSequence));
        gLimit.setLimitLeaderid( (isFu.equals("1"))?0:Integer.valueOf(limitLeaderid));
        gLimit.setLimitAction(limitAction);
        gLimit.setLimitIco("&#"+isIco+";");
        gLimit.setLimitSystem(Integer.valueOf(isRevise));

        // 准备返回
        Map<String, Object> map = new HashMap<String, Object>();

        // 添加
        int i = gLimitService.save(gLimit);

        System.out.println(i);

        map = (i>0)? KitUtil.returnMap("200", StaticFinalVar.ADD_OK):KitUtil.returnMap("101",StaticFinalVar.ADD_ERR);
        return JSONObject.toJSON(map);
    }

    /**
     * 删除
     * @param request
     * @return
     */
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    @ResponseBody
    public Object del(HttpServletRequest request){
        Map<String, Object> map = new HashMap<String, Object>();
        String id = request.getParameter("id");
        // 查询是不是有子类
        GLimit gLimit = new GLimit();
        gLimit.setLimitLeaderid(Integer.valueOf(id));
        List<GLimit> gLimits = gLimitService.queryListByWhere(gLimit);
        // 如果有子类
        if(gLimits.size()>0){
            map = KitUtil.returnMap("101", StaticFinalVar.DEL_SOME_ZILEI);
            return JSONObject.toJSON(map);
        }
        // 删除
        int i = gLimitService.deleteById(Integer.parseInt(id));
        map = (i>0)? KitUtil.returnMap("200", StaticFinalVar.DEL_OK):KitUtil.returnMap("101",StaticFinalVar.DEL_ERR);
        return JSONObject.toJSON(map);
    }

    /**
     * 去修改
     * @param request
     * @return
     */
    @RequestMapping(value = "/goUpdate", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView goUpdate(HttpServletRequest request){
        ModelAndView mv = new ModelAndView();
        String id = request.getParameter("id");
        GLimit gLimit = gLimitService.queryById(Integer.parseInt(id));
        //
        GLimit g = new GLimit();
        g.setLimitLeaderid(0);
        List<GLimit> limits = gLimitService.queryListByWhere(g);
        System.out.println(limits.size());
        // 删除
        mv.setViewName("/admin/menu/update");
        mv.addObject("kitModel",gLimit);
        mv.addObject("kitList",limits);
        return mv;
    }

    /**
     * 修改
     * @param request
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Object update(HttpServletRequest request){
        log.info("菜单管理 > 修改 > 提交");
        // 获取参数
        String limitId = request.getParameter("limitId");
        String limitTitle = request.getParameter("limitTitle");
        String limitSequence = request.getParameter("limitSequence");
        String isFu = request.getParameter("isFu");
        String limitLeaderid = request.getParameter("limitLeaderid");
        String limitAction = request.getParameter("limitAction");
        String isIco = request.getParameter("isIco");
        String isRevise = request.getParameter("isRevise");

        // 判断封装对象
        GLimit gLimit = new GLimit();
        gLimit.setLimitId(Integer.valueOf(limitId));
        gLimit.setLimitTitle(limitTitle);
        gLimit.setLimitSequence(Integer.valueOf(limitSequence));
        gLimit.setLimitLeaderid( (isFu.equals("1"))?0:Integer.valueOf(limitLeaderid));
        gLimit.setLimitAction(limitAction);
        gLimit.setLimitIco("&#"+isIco+";");
        gLimit.setLimitSystem(Integer.valueOf(isRevise));

        // 准备返回
        Map<String, Object> map = new HashMap<String, Object>();

        // 添加
        int i = gLimitService.update(gLimit);

        System.out.println(i);

        map = (i>0)? KitUtil.returnMap("200", StaticFinalVar.UPDATE_OK):KitUtil.returnMap("101",StaticFinalVar.UPDATE_ERR);
        return JSONObject.toJSON(map);
    }
}
