package cc.openkit.admin.service.message.rytx;

import cc.openkit.admin.model.sms.YtxSmsContextOrder;
import cc.openkit.admin.model.sms.YtxSmsYtxParentOrder;
import com.alibaba.fastjson.JSON;
import com.cloopen.rest.sdk.CCPRestSDK;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import java.util.HashMap;


public class PhoneHelper {

    /**
     * 日志.
     */
    private static final Logger logger = Logger.getLogger("SMS-lOG");


    private CCPRestSDK restSmsAPI = new CCPRestSDK();


    /**
     * 功能：容联云通讯-发送短信
     * 占位符可以为空
     * 接收者多人用英文逗号隔开
     * 单个号码接收（手机号码格式错误会返回错误信息statusCode=160042）
     * 多个号码接收（只要有一个号码是对的就会返回正确）
     *
     * @param identificationNo 日志追踪唯一标识
     * @return
     */
    public boolean sendSmsByYTX(String identificationNo, YtxSmsContextOrder smsContextOrder) {

        fillBaseConfig(smsContextOrder);
        HashMap<String, Object> result = null;
        try {
            initSmsApi(smsContextOrder);
            // 接口短信模板id
            result = restSmsAPI.sendTemplateSMS(smsContextOrder.getReceiverPhone(),
                    smsContextOrder.getSmsTempid(), smsContextOrder.getContents());
        } catch (Exception e) {
            logger.error(
                    smsContextOrder.getSmsTempid() + ":容联云通讯-发送短信接口异常:"
                            + JSON.toJSONString(smsContextOrder.getContents()), e);
            return false;
        }

        if (StringUtils.equals("000000", result.get("statusCode").toString())) {
            //正常返回输出data包体信息（map）
            return true;
        }
        // 异常返回输出错误码和错误信息
        // 单个号码发送（手机号码格式错误会返回错误信息statusCode=160042）
        // 多个号码发送（只要有一个号码是对的就会返回正确）
        String phoneLogger = null;
        if (StringUtils.isNotBlank(smsContextOrder.getReceiverPhone())
                && smsContextOrder.getReceiverPhone().length() < 24) {
            phoneLogger = ":接收手机号码=" + smsContextOrder.getReceiverPhone();
        }
        logger.error((StringUtils.isNotBlank(identificationNo) ? identificationNo : "sys")
                + phoneLogger + "；短信模板id：" + smsContextOrder.getSmsTempid() + "；短信错误码="
                + result.get("statusCode") + "；错误信息=" + result.get("statusMsg"));

        return false;
    }


    /**
     * 功能：填充配置参数
     *
     * @param smsYtxConfig
     */
    private void fillBaseConfig(YtxSmsYtxParentOrder smsYtxConfig) {
        // "sms_ytx_url"
        String smsUrl = "app.cloopen.com";
        smsYtxConfig.setSmsUrl(smsUrl);
        // sms_ytx_port
        String smsPort = "8883";
        smsYtxConfig.setSmsPort(smsPort);
        // sms_ytx_sid
        String accountName = "xxxxx";
        smsYtxConfig.setAccountName(accountName);
        // sms_ytx_token
        String accountPsd = "xxxx";
        smsYtxConfig.setAccountPsd(accountPsd);
        // sms_ytx_appid
        String appId = "xxxxx";
        smsYtxConfig.setAppId(appId);
    }

    private void initSmsApi(YtxSmsYtxParentOrder smsYtxConfig) {
        if (null == restSmsAPI) {
            restSmsAPI = new CCPRestSDK();
        }
        // 请求地址 端口
        restSmsAPI.init(smsYtxConfig.getSmsUrl(), smsYtxConfig.getSmsPort());
        // 接口账号密码
        restSmsAPI.setAccount(smsYtxConfig.getAccountName(), smsYtxConfig.getAccountPsd());
        // 接口注册应用ID
        restSmsAPI.setAppId(smsYtxConfig.getAppId());
    }


}

