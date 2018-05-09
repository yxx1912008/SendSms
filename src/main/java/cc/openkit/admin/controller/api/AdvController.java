package cc.openkit.admin.controller.api;

import cc.openkit.admin.model.AdvHello;
import cc.openkit.admin.model.WebSetting;
import cc.openkit.admin.service.adv_hello.AdvHelloService;
import cc.openkit.admin.service.web.WebSettingService;
import cc.openkit.admin.util.AppUtil;
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
import javax.jws.Oneway;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * APP端的首次登录欢迎页面
 * APP端调用接口
 *
 * @author bigben
 * @date 2018.2.5
 */
@Controller
@Scope("prototype")
@RequestMapping("/apiHello")
public class AdvController {
    private Logger log = Logger.getLogger(AdvController.class);

    @Resource
    private AdvHelloService advHelloService;

    @Resource
    private WebSettingService webSettingService;

    /**
     * 去首次打开APP广告列表页面
     * @return
     */
    @RequestMapping(value = "/get", method = RequestMethod.POST)
    @ResponseBody
    public Object get(HttpServletRequest request){

        // APP验证
        if(!AppUtil.isApp(request)){
            return JSONObject.toJSON(KitUtil.returnMap("4001", StaticFinalVar.APP_ERR));
        }

        // APP验证通过
        // 找到要显示几个数据
        WebSetting webSetting = webSettingService.queryById(1);
        List<AdvHello> advHelloList = advHelloService.appGet(webSetting.getKitWebHellowAdvSize());

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("kitList",advHelloList);
        map.put("code","200");
        map.put("msg","");

        return JSONObject.toJSON(map);
    }
}
