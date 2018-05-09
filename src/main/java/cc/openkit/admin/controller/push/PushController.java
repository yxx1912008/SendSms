package cc.openkit.admin.controller.push;

import cc.openkit.admin.model.GGroupLimit;
import cc.openkit.admin.model.PushJpush;
import cc.openkit.admin.service.g.GGroupLimitService;
import cc.openkit.admin.service.g.GGroupService;
import cc.openkit.admin.service.push.jpush.PushJpushService;
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

@Controller
@Scope("prototype")
@RequestMapping("/push")
public class PushController {
    private Logger log = Logger.getLogger(PushController.class);

    @Resource
    private PushJpushService pushJpushService;
    @Resource
    private GGroupLimitService gGroupLimitService;

    /**
     * 去修改
     * @return
     */
    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public ModelAndView getAll(HttpServletRequest request){

        // 权限验证。这里的最后的 5 就是上面我们找到菜单的ID，需要在这里查找
        GGroupLimit gGroupLimit = gGroupLimitService.testGroup(request, 22);

        ModelAndView mv = new ModelAndView();

        // 没有权限，返回错误页面
        if(gGroupLimit==null){
            mv.setViewName("/admin/err/no_group_err");
            mv.addObject("msg", StaticFinalVar.NO_GROUP_MSG+StaticFinalVar.CALL_GROUP);
            return mv;
        }
        // 取值
        PushJpush pushJpush = pushJpushService.queryById(1);

        // 取值
        mv.setViewName("/admin/push/update"); // 需要跳转的页面
        mv.addObject("kitG",gGroupLimit);
        mv.addObject("jpash",pushJpush);
        return mv;
    }

    /**
     * 修改配置文件
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/updatePush", method = RequestMethod.POST)
    @ResponseBody
    public Object add(HttpServletRequest request) throws Exception{
        log.info("极光推送参数 》 修改 》 保存");

        // 取值
        String pjAppkey = request.getParameter("pjAppkey");
        String pjMastersecret = request.getParameter("pjMastersecret");

        // 封装对象
        PushJpush pushJpush = new PushJpush();
        pushJpush.setPjId(1);
        pushJpush.setPjAppkey(pjAppkey);
        pushJpush.setPjMastersecret(pjMastersecret);

        // 保存并返回
        return JSONObject.toJSON(pushJpushService.updateSelective(pushJpush)==1 ? KitUtil.returnMap("200",StaticFinalVar.ADD_OK) : KitUtil.returnMap("101",StaticFinalVar.ADD_ERR));
    }

}
