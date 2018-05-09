package cc.openkit.admin.controller.web;

import cc.openkit.admin.model.Admin;
import cc.openkit.admin.model.GGroupLimit;
import cc.openkit.admin.model.WebSetting;
import cc.openkit.admin.service.g.GGroupLimitService;
import cc.openkit.admin.service.web.WebSettingService;
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
import java.util.Map;

/**
 * 后台管理员类的方法
 *
 * @author bigben
 * @date 2018.2.5
 */
@Controller
@Scope("prototype")
@RequestMapping("/web")
public class WebSettingController {
    private Logger log = Logger.getLogger(WebSettingController.class);

    @Resource
    private WebSettingService webSettingService;
    @Resource
    private GGroupLimitService gGroupLimitService;

    /**
     * 获取配置模型 返回json
     * @return
     */
    @RequestMapping(value = "/getModel", method = RequestMethod.POST)
    @ResponseBody
    public Object tologin(HttpServletRequest request){

        ModelAndView mv = new ModelAndView();



        WebSetting webSetting = webSettingService.queryById(1);
        return JSONObject.toJSON(webSetting);
    }

    /**
     * 获取配置模型 返回页面
     * @param request
     * @return
     */
    @RequestMapping(value = "/getModelReturn", method = RequestMethod.GET)
    public ModelAndView getModelReturn(HttpServletRequest request){
        ModelAndView mv = new ModelAndView();

        // 权限验证
        GGroupLimit gGroupLimit = gGroupLimitService.testGroup(request, 2);
        // 没有权限，返回错误页面
        if(gGroupLimit==null){
            mv.setViewName("/admin/err/no_group_err");
            mv.addObject("msg", StaticFinalVar.NO_GROUP_MSG+StaticFinalVar.CALL_GROUP);
            return mv;
        }

        WebSetting webSetting = webSettingService.queryById(1);
        mv.addObject("kitModel",webSetting);
        mv.setViewName("/admin/menu/web_setting");
        return mv;
    }

    /**
     * 修改设置文件
     * @param request
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Object update(HttpServletRequest request){
        Map<String, Object> map = new HashMap<String, Object>();

        // 获取数据
        String kitWebName = request.getParameter("kitWebName");

        String kitWebMessage = request.getParameter("kitWebMessage");
        String kitWebPush = request.getParameter("kitWebPush");
        String kitWebFile = request.getParameter("kitWebFile");
        String kitSigeSize = request.getParameter("kitSigeSize");
        String kitWebHellowAdvSize = request.getParameter("kitWebHellowAdvSize");
        String kitSignActiveTime = request.getParameter("kitSignActiveTime");

        WebSetting webSetting  = new WebSetting();
        webSetting.setKitWebId(1);
        webSetting.setKitWebName(kitWebName);
        webSetting.setKitWebMessage(Integer.valueOf(kitWebMessage));
        webSetting.setKitWebPush(Integer.valueOf(kitWebPush));
        webSetting.setKitWebFile(Integer.valueOf(kitWebFile));
        webSetting.setKitSigeSize(Integer.valueOf(kitSigeSize));
        webSetting.setKitWebHellowAdvSize(Integer.valueOf(kitWebHellowAdvSize));
        webSetting.setKitSignActiveTime(Integer.valueOf(kitSignActiveTime));

        // 保存
        int i = webSettingService.updateSelective(webSetting);
        // 判断
        map = (i>0)? KitUtil.returnMap("200", StaticFinalVar.UPDATE_OK+StaticFinalVar.RELOG_IN):KitUtil.returnMap("101",StaticFinalVar.UPDATE_ERR);
        return JSONObject.toJSON(map);
    }

}
