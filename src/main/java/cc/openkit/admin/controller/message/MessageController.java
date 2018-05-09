package cc.openkit.admin.controller.message;

import cc.openkit.admin.model.GGroupLimit;
import cc.openkit.admin.model.Message;
import cc.openkit.admin.model.util.KitMessage;
import cc.openkit.admin.service.g.GGroupLimitService;
import cc.openkit.admin.service.g.GGroupService;
import cc.openkit.admin.service.message.MessageService;
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
import sun.misc.resources.Messages;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@Scope("prototype")
@RequestMapping("/message")
public class MessageController {

    private Logger log = Logger.getLogger(MessageController.class);

    @Resource
    private MessageService messageService;
    @Resource
    private GGroupLimitService gGroupLimitService;

    /**
     * 跳转到短信设置配置页面
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView index(HttpServletRequest request){
        ModelAndView mv = new ModelAndView();

        // 权限验证
        GGroupLimit gGroupLimit = gGroupLimitService.testGroup(request, 20);
        // 没有权限，返回错误页面
        if(gGroupLimit==null){
            mv.setViewName("/admin/err/no_group_err");
            mv.addObject("msg", StaticFinalVar.NO_GROUP_MSG+StaticFinalVar.CALL_GROUP);
            return mv;
        }

        Message message = messageService.queryById(1);

        mv.setViewName("/admin/message/update");
        mv.addObject("kitModel",message);

        return mv;
    }

    /**
     * 修改保存短信配置
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Object update(HttpServletRequest request){
        Map<String, Object> map = new HashMap<String, Object>();

        // 获取参数
        String keyId = request.getParameter("keyId");
        String keySecret = request.getParameter("keySecret");
        String signName = request.getParameter("signName");
        String dayutemplate = request.getParameter("dayutemplate");
        String sid = request.getParameter("sid");
        String token = request.getParameter("token");
        String appId = request.getParameter("appId");
        String yzxtemplate = request.getParameter("yzxtemplate");

        // 封装数据
        Message message = new Message();
        message.setId(1);
        if(keyId!=null&&!"".equals(keyId)){
            message.setKeyId(keyId);
        }
        if(keySecret!=null&&!"".equals(keySecret)){
            message.setKetSecret(keySecret);
        }
        if(signName!=null&&!"".equals(signName)){
            message.setSignName(signName);
        }
        if(dayutemplate!=null&&!"".equals(dayutemplate)){
            message.setTemplateDayu(dayutemplate);
        }

        // 云之讯部分
        if(sid!=null&&!"".equals(sid)){
            message.setSid(sid);
        }
        if(token!=null&&!"".equals(token)){
            message.setToken(token);
        }
        if(appId!=null&&!"".equals(appId)){
            message.setAppId(appId);
        }
        if(yzxtemplate!=null&&!"".equals(yzxtemplate)){
            message.setTemplateYzx(yzxtemplate);
        }

        map = messageService.updateSelective(message)==1 ? KitUtil.returnMap("200", StaticFinalVar.UPDATE_OK):KitUtil.returnMap("200", StaticFinalVar.UPDATE_ERR);


        System.out.println(map.get("code"));

        return JSONObject.toJSON(map);
    }

    /**
     * 短信测试
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/messageTest", method = RequestMethod.POST)
    @ResponseBody
    public Object messageTest(HttpServletRequest request){
        Map<String,Object> map = new HashMap<String, Object>();

        // 取值
        String messagetype = request.getParameter("messagetype");  // 调用方式
        String phone = request.getParameter("phone"); // 接收短信的手机号码
        String code = request.getParameter("code"); // 验证码
        String template = request.getParameter("template");  // 短信模板

        KitMessage kitMessage = new KitMessage();
        if(messagetype!=null && !"".equals(messagetype)){
            kitMessage.setMessagetype(Integer.valueOf(messagetype));
        }
        kitMessage.setPhone(phone);
        kitMessage.setCode(code);
        kitMessage.setTemplate(template);

        map = messageService.send(kitMessage);

        return JSONObject.toJSON(map);
    }

}
