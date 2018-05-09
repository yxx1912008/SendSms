package cc.openkit.admin.service.pay.paySetting.impl;

import cc.openkit.admin.dao.PaySettingMapper;
import cc.openkit.admin.model.PaySetting;
import cc.openkit.admin.model.PaySettingAlipay;
import cc.openkit.admin.model.PaySettingWeixin;
import cc.openkit.admin.model.util.KitPay;
import cc.openkit.admin.service.common.impl.BaseServiceImpl;
import cc.openkit.admin.service.pay.paySetting.PaySettingService;
import cc.openkit.admin.service.pay.paySettingAlipay.PaySettingAlipayService;
import cc.openkit.admin.service.pay.paySettingWeixin.PaySettingWeixinService;
import cc.openkit.kitPay.alipay.config.AliPayConfig;
import cc.openkit.kitPay.alipay.model.Alipay;
import cc.openkit.kitPay.alipay.service.AlipayService;
import cc.openkit.kitPay.alipay.service.impl.AlipayServiceImpl;
import cc.openkit.kitPay.weichart.model.WeichartModel;
import cc.openkit.kitPay.weichart.service.WeixinPayService;
import cc.openkit.kitPay.weichart.service.impl.WeixinPayServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Service
@Transactional(rollbackFor = Exception.class)
public class PaySettingServiceImpl extends BaseServiceImpl<PaySetting> implements PaySettingService{
    private Logger log = Logger.getLogger(PaySettingServiceImpl.class);

    @Resource
    private PaySettingMapper paySettingMapper;
    @Resource
    private PaySettingWeixinService paySettingWeixinService;
    @Resource
    private PaySettingAlipayService paySettingAlipayService;

    /**
     * 通用 APP 支付接口
     * @param request
     * @param response
     * @param payUtil
     * @return
     */
    public Map<String, Object> kitAppPayUnifyAPI(HttpServletRequest request, HttpServletResponse response, KitPay payUtil) throws Exception{

        // Service 层调用容器

        Map<String, Object> map = new HashMap<String, Object>();
        if("WEIXINPAY".equals(payUtil.getType())){
            // 微信支付
            // 数据库查找微信支付配置参数，封装数据
            WeichartModel weichartModel = new WeichartModel();

            PaySettingWeixin wx = paySettingWeixinService.queryById(1);

            weichartModel.setAppId(wx.getPswAppId());
            weichartModel.setAppSecret(wx.getPswAppSecret());
            weichartModel.setAppKey(wx.getPswAppKey());
            weichartModel.setMchId(wx.getPswMchid());
            weichartModel.setBody(wx.getPswBody());
            weichartModel.setPartnerKey(wx.getPswPartnerKey());
            weichartModel.setPartnerId(wx.getPswPartnerId());
            weichartModel.setGrantType(wx.getPswGrantType());
            weichartModel.setGateUrl(wx.getPswGateUrl());
            weichartModel.setNotifyUrl(wx.getPswNotifyUrl());
            weichartModel.setSpbillCreateIp(wx.getPswSpbillCreateip());

            // 调用方法
            WeixinPayService weixinPayService = new WeixinPayServiceImpl();
            map = weixinPayService.getOrder(request, response, weichartModel,payUtil.getMoney(), payUtil.getOrderNumber(), payUtil.getNotifyUrl());
        }

        if("ALIPAY".equals(payUtil.getType())){
            // 支付宝支付
            PaySettingAlipay a = paySettingAlipayService.queryById(1);
            AliPayConfig aliPayConfig = new AliPayConfig(
                    a.getPsaAppId(),
                    a.getPsaPrivateKey(),
                    a.getPsaPublicKey(),
                    a.getPsaServerUrl(),
                    a.getPsaDoMain(),
                    a.getPsaCharset(),
                    a.getPsaFormat(),
                    a.getPsaSignType()
            );

            Alipay alipay =new Alipay(payUtil.getBody(),payUtil.getSubject(), payUtil.getOrderNumber(), payUtil.getMoney(), payUtil.getNotifyUrl());

            AlipayService alipayService = new AlipayServiceImpl();
            map = alipayService.aliAppPay(aliPayConfig, alipay);
        }
        return map;
    }
}
