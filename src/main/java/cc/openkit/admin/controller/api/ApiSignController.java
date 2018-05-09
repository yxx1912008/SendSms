package cc.openkit.admin.controller.api;

import cc.openkit.admin.model.AdvHello;
import cc.openkit.admin.model.Sign;
import cc.openkit.admin.model.User;
import cc.openkit.admin.model.WebSetting;
import cc.openkit.admin.model.util.KitMessage;
import cc.openkit.admin.service.message.MessageService;
import cc.openkit.admin.service.sign.SignService;
import cc.openkit.admin.service.user.UserService;
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

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 统一后去短信的接口
 *
 * @author bigben
 * @date 2018.2.5
 */
@Controller
@Scope("prototype")
@RequestMapping("/apiSign")
public class ApiSignController {
    private Logger log = Logger.getLogger(AdvController.class);

    @Resource
    private SignService signService;
    @Resource
    private WebSettingService webSettingService;
    @Resource
    private MessageService messageService;
    @Resource
    private UserService userService;

    /**
     * 获取短信验证码
     * @return
     */
    @RequestMapping(value = "/get", method = RequestMethod.POST)
    @ResponseBody
    public Object get(HttpServletRequest request){
        log.info("获取验证码");
        Map<String, Object> map = new HashMap<String, Object>();
        // APP验证
        if(!AppUtil.isApp(request)){
            return JSONObject.toJSON(KitUtil.returnMap("4001", StaticFinalVar.APP_ERR));
        }

        // APP验证通过

        // 获取前台传来的手机号码
        String phone = request.getParameter("phone");
        if(!KitUtil.feikong(phone)){
            return JSONObject.toJSON(KitUtil.returnMap("101","手机号码不能为空"));
        }
        // 验证手机是否可以发送验证码
        map = signService.isOk(phone);

        if ("101".equals(map.get("code"))){
            return JSONObject.toJSON(map);
        }

        // 找到数据库中配置的位数
        WebSetting webSetting = webSettingService.queryById(1);
        // 根据要求生产验证码并且加密
        int code = KitUtil.randomNumber(webSetting.getKitSigeSize());
        log.info("你发送的验证码是："+code);
        String kst = KitUtil.uuid();

        // 找到要显示几个数据
        Sign sign = new Sign(kst,phone,AppUtil.md5pwd(Integer.toString(code)),new Date(),1);

        // 获取是有给用户发消息，还是没有注册时给用户发短息
        String type = request.getParameter("type");
        User user = new User();
        user.setUserPhone(phone);
        // 更具手机号码查看是否存在
        int i = userService.queryCount(user);
        if(!KitUtil.feikong(type)){
            return JSONObject.toJSON(KitUtil.returnMap("101",StaticFinalVar.PARAMETER_ERR));
        }
        if("have".equals(type) && i==0){
            return JSONObject.toJSON(KitUtil.returnMap("101",StaticFinalVar.HAVE_NOT_PHONE));
        }
        if("null".equals(type) && i>0){
            return JSONObject.toJSON(KitUtil.returnMap("101",StaticFinalVar.HAVE_PHONE));
        }
        // 存储
        int line = signService.save(sign);
        KitMessage kitMessage = new KitMessage();
        if (line==1){
            kitMessage.setPhone(phone);
            kitMessage.setCode("code,"+Integer.toString(code));
        }
        // 发送短信
        messageService.send(kitMessage);
        map=KitUtil.returnMap("200","");
        map.put("KST",kst);

        return JSONObject.toJSON(map);
    }



}
