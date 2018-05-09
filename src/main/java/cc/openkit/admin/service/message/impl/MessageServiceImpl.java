package cc.openkit.admin.service.message.impl;

import cc.openkit.admin.dao.MessageMapper;
import cc.openkit.admin.model.Message;
import cc.openkit.admin.model.WebSetting;
import cc.openkit.admin.model.util.KitMessage;
import cc.openkit.admin.service.common.impl.BaseServiceImpl;
import cc.openkit.admin.service.message.MessageService;
import cc.openkit.admin.service.web.WebSettingService;
import cc.openkit.common.KitUtil;
import cc.openkit.kitMessage.alidayu.model.AliDayuModel;
import cc.openkit.kitMessage.alidayu.service.AliDayuService;
import cc.openkit.kitMessage.ucpaas.model.UcpaasConfig;
import cc.openkit.kitMessage.ucpaas.model.UcpaasModel;
import cc.openkit.kitMessage.ucpaas.service.UcpaasService;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service
@Transactional(rollbackFor = Exception.class)
public class MessageServiceImpl  extends BaseServiceImpl<Message> implements MessageService {

    private Logger log = Logger.getLogger(MessageServiceImpl.class);

    @Resource
    private MessageMapper messageMapper;

    @Resource
    private WebSettingService webSettingService;

    public Map<String,Object> send(KitMessage kitMessage) {
        // 先看看我们有没有强制传参说用阿里大鱼还是
        WebSetting webSetting = new WebSetting();

        if(kitMessage.getMessagetype()==0){
            // 查询当前设置的是哪一个
            webSetting = webSettingService.queryById(1);
        }else{
            webSetting.setKitWebMessage(kitMessage.getMessagetype());
        }

        Message message = messageMapper.selectByPrimaryKey(1);

        // 返回值
        Map<String,Object> map = new HashMap<String, Object>();

        // 判断是哪一个短信第三方
        if(webSetting.getKitWebMessage() == 1){
            // 新建阿里大鱼
            AliDayuModel aliDayuModel = new AliDayuModel();
            aliDayuModel.setAccessKeyId(message.getKeyId());
            aliDayuModel.setAccessKeySecret(message.getKetSecret());
            aliDayuModel.setPhone(kitMessage.getPhone());
            aliDayuModel.setSignName(message.getSignName());

            aliDayuModel.setTemplateCode( kitMessage.getTemplate()==null || "".equals(kitMessage.getTemplate()) ? message.getTemplateDayu() : kitMessage.getTemplate() );

            // 拼接code
            String[] as = kitMessage.getCode().split(",");

            if(as.length%2!=0){
                map = KitUtil.returnMap("101","你的验证码输入的格式有错误");
                return map;
            }

            String code = "{";
            for (int i = 0; i < as.length; i++) {
                i = i+1;
                code = code + "\"" + as[i-1] + "\":" + as[i];
                if(i == as.length-1){
                    code = code + "}";
                }else{
                    code = code + ",";
                }
            }

            System.out.println(code);
            aliDayuModel.setTemplateParam(code);

            try {
                SendSmsResponse xin = AliDayuService.xin(aliDayuModel);

                map.put("code","OK".equals(xin.getCode())?"200":"101");
                map.put("msg","OK".equals(xin.getCode())?"短信发送成功":xin.getMessage());

                System.out.println(xin.getCode());
            } catch (ClientException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }



        } else if(webSetting.getKitWebMessage() == 2) {
            // 新建云之讯
            UcpaasConfig config = new UcpaasConfig(message.getSid(), message.getToken(), message.getAppId());
            UcpaasModel model = new UcpaasModel();
            model.setTemplateid(kitMessage.getTemplate()==null || "".equals(kitMessage.getTemplate()) ? message.getTemplateYzx() : kitMessage.getTemplate());

            // 拼接code
            String[] as = kitMessage.getCode().split(",");

            if(as.length%2!=0){
                map = KitUtil.returnMap("101","你的验证码输入的格式有错误");
                return map;
            }

            String code = "";
            for (int i = 0; i < as.length; i++) {
                i = i+1;
                code = as[i];
                if(i != as.length-1){
                    code = code + ",";
                }
            }

            model.setParam(code);
            model.setMobile(kitMessage.getPhone());

            // 调用短信发送接口
            String result = UcpaasService.sendSms(config,model);

            JSONObject jsonObject = JSONObject.fromObject(result);
            System.out.println(jsonObject);

            String j = (String) jsonObject.get("code");
            System.out.println(j);

            map.put("code","000000".equals(j)?"200":"101");
            map.put("msg","000000".equals(j)?"短信发送成功":"短信发送失败，错误状态码："+j+"请查看具体状态码说明");
        }

        return map;
    }







}
