package cc.openkit.admin.controller.pay;

import cc.openkit.admin.model.GGroupLimit;
import cc.openkit.admin.model.PaySetting;
import cc.openkit.admin.model.PaySettingAlipay;
import cc.openkit.admin.model.PaySettingWeixin;
import cc.openkit.admin.model.util.KitPay;
import cc.openkit.admin.service.g.GGroupLimitService;
import cc.openkit.admin.service.pay.paySetting.PaySettingService;
import cc.openkit.admin.service.pay.paySettingAlipay.PaySettingAlipayService;
import cc.openkit.admin.service.pay.paySettingWeixin.PaySettingWeixinService;
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
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
@Scope("prototype")
@RequestMapping("/pay")
public class PayController {


    private Logger log = Logger.getLogger(PayController.class);

    @Resource
    private PaySettingService paySettingService;
    @Resource
    private PaySettingAlipayService paySettingAlipayService;
    @Resource
    private PaySettingWeixinService paySettingWeixinService;
    @Resource
    private GGroupLimitService gGroupLimitService;

    /**
     * 添加的方法
     * @return
     */
    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public ModelAndView getAll(HttpServletRequest request){

        // 权限验证。这里的最后的 5 就是上面我们找到菜单的ID，需要在这里查找
        GGroupLimit gGroupLimit = gGroupLimitService.testGroup(request, 25);

        ModelAndView mv = new ModelAndView();

        // 没有权限，返回错误页面
        if(gGroupLimit==null){
            mv.setViewName("/admin/err/no_group_err");
            mv.addObject("msg", StaticFinalVar.NO_GROUP_MSG+StaticFinalVar.CALL_GROUP);
            return mv;
        }
        // 查询微信或者是支付宝的配置参数
        PaySetting paySetting = paySettingService.queryById(1);
        PaySettingAlipay paySettingAlipay = paySettingAlipayService.queryById(1);
        PaySettingWeixin paySettingWeixin = paySettingWeixinService.queryById(1);

        // 取值
        mv.setViewName("/admin/pay/update"); // 需要跳转的页面
        mv.addObject("kitG",gGroupLimit);

        mv.addObject("ps",paySetting);
        mv.addObject("psa",paySettingAlipay);
        mv.addObject("psw",paySettingWeixin);

        return mv;
    }

    /**
     * 修改支付宝的状态（开启或者关闭）
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/updateAlipayPayType", method = RequestMethod.POST)
    @ResponseBody
    public Object updateAlipayPayType(HttpServletRequest request) throws Exception{
        log.info("设置支付宝开关 》 添加 》 删除");
        //取值
        String kaiguan = request.getParameter("kaiguan");

        // 封装
        PaySetting paySetting = new PaySetting();
        paySetting.setPsId(1);
        paySetting.setPayIsAlipay(Integer.valueOf(kaiguan));
        return paySettingService.updateSelective(paySetting) == 1 ? KitUtil.returnMap("200",StaticFinalVar.UPDATE_OK) : KitUtil.returnMap("101",StaticFinalVar.UPDATE_ERR);
    }

    /**
     * 修改微信的状态（开启或者关闭）
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/updateWeixinPayType", method = RequestMethod.POST)
    @ResponseBody
    public Object updateWeixinPayType(HttpServletRequest request) throws Exception{
        log.info("设置微信开关 》 添加 》 删除");
        //取值
        String kaiguan = request.getParameter("kaiguan");

        // 封装
        PaySetting paySetting = new PaySetting();
        paySetting.setPsId(1);
        paySetting.setPayIsWeixin(Integer.valueOf(kaiguan));
        return paySettingService.updateSelective(paySetting) == 1 ? KitUtil.returnMap("200",StaticFinalVar.UPDATE_OK) : KitUtil.returnMap("101",StaticFinalVar.UPDATE_ERR);
    }

    /**
     * 修改支付宝的配置
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/updateAlipay", method = RequestMethod.POST)
    @ResponseBody
    public Object updateAlipay(HttpServletRequest request) throws Exception{
        log.info("修改支付宝的配置信息 》 保存");
        //取值
        String psaAppId = request.getParameter("psaAppId");
        String psaPrivateKey = request.getParameter("psaPrivateKey");
        String psaPublicKey = request.getParameter("psaPublicKey");
        String psaServerUrl = request.getParameter("psaServerUrl");
        String psaDoMain = request.getParameter("psaDoMain");
        String psaCharset = request.getParameter("psaCharset");
        String psaFormat = request.getParameter("psaFormat");
        String psaSignType = request.getParameter("psaSignType");


        // 封装
        PaySettingAlipay paySettingAlipay = new PaySettingAlipay();
        paySettingAlipay.setPsaId(1);
        paySettingAlipay.setPsaAppId(psaAppId);
        paySettingAlipay.setPsaPrivateKey(psaPrivateKey);
        paySettingAlipay.setPsaPublicKey(psaPublicKey);
        paySettingAlipay.setPsaServerUrl(psaServerUrl);
        paySettingAlipay.setPsaDoMain(psaDoMain);
        paySettingAlipay.setPsaCharset(psaCharset);
        paySettingAlipay.setPsaFormat(psaFormat);
        paySettingAlipay.setPsaSignType(psaSignType);

        return paySettingAlipayService.updateSelective(paySettingAlipay) == 1 ? KitUtil.returnMap("200",StaticFinalVar.UPDATE_OK) : KitUtil.returnMap("101",StaticFinalVar.UPDATE_ERR);
    }


    /**
     * 修改微信的配置
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/updateWeixin", method = RequestMethod.POST)
    @ResponseBody
    public Object updateWeixin(HttpServletRequest request) throws Exception{
        log.info("修改微信的配置信息 》 保存");
        //取值
        String pswAppId = request.getParameter("pswAppId");
        String pswAppSecret = request.getParameter("pswAppSecret");
        String pswAppKey = request.getParameter("pswAppKey");
        String pswMchid = request.getParameter("pswMchid");
        String pswBody = request.getParameter("pswBody");
        String pswPartnerKey = request.getParameter("pswPartnerKey");
        String pswPartnerId = request.getParameter("pswPartnerId");
        String pswGrantType = request.getParameter("pswGrantType");
        String pswGateUrl = request.getParameter("pswGateUrl");
        String pswNotifyUrl = request.getParameter("pswNotifyUrl");
        String pswSpbillCreateip = request.getParameter("pswSpbillCreateip");

        // 封装
        PaySettingWeixin paySettingWeixin = new PaySettingWeixin();
        paySettingWeixin.setPswId(1);
        paySettingWeixin.setPswAppId(pswAppId);
        paySettingWeixin.setPswAppSecret(pswAppSecret);
        paySettingWeixin.setPswAppKey(pswAppKey);
        paySettingWeixin.setPswMchid(pswMchid);
        paySettingWeixin.setPswBody(pswBody);
        paySettingWeixin.setPswPartnerKey(pswPartnerKey);
        paySettingWeixin.setPswPartnerId(pswPartnerId);
        paySettingWeixin.setPswGrantType(pswGrantType);
        paySettingWeixin.setPswGateUrl(pswGateUrl);
        paySettingWeixin.setPswNotifyUrl(pswNotifyUrl);
        paySettingWeixin.setPswSpbillCreateip(pswSpbillCreateip);

        return paySettingWeixinService.updateSelective(paySettingWeixin) == 1 ? KitUtil.returnMap("200",StaticFinalVar.UPDATE_OK) : KitUtil.returnMap("101",StaticFinalVar.UPDATE_ERR);
    }


    /**
     * 修改微信的配置
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/test", method = RequestMethod.POST)
    @ResponseBody
    public Object test(HttpServletRequest request,HttpServletResponse response) throws Exception{
        log.info("支付公共类测试");
        //取值
        String type = request.getParameter("type");
        String payType = request.getParameter("payType");
        String money = request.getParameter("money");

        PaySettingWeixin wx = paySettingWeixinService.queryById(1);

        Map<String,Object> map = new HashMap<String,Object>();

        if("1".equals(payType)){
            // APP支付测试
            KitPay payUtil = new KitPay(money,KitUtil.getOrderCode(),"test.do");
            if("1".equals(type)){
                payUtil.setType("ALIPAY");
                payUtil.setBody("西天取经的书籍");
                payUtil.setSubject("《西游记》");
            }
            if("2".equals(type)){
                payUtil.setType("WEIXINPAY");
            }

            map = paySettingService.kitAppPayUnifyAPI(request,response,payUtil);
        }

        if("2".equals(payType)){
            // 网页支付
        }

        return JSONObject.toJSON(map);
    }





}
