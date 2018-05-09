package cc.openkit.admin.model.util;

import cc.openkit.kitPay.alipay.model.Alipay;

public class KitPay {

    // 支付方式 如果是微信，请传“WEIXINPAY”，如果是支付宝“ALIPAY”
    public String type;
    // 价格
    public String money;
    // 订单号
    public String orderNumber;
    // 回调地址
    public String notifyUrl;
    // 下面是支付宝的必填
    // 商品描述
    public String body;
    // 商品名称
    public String subject;
    // 有效时间
    public String timeoutExpress;
    // 支付方式  QUICK_MSECURITY_PAY   即时到账
    public String productCode;

    public KitPay() {
        this.timeoutExpress = "30m";
        this.productCode = "QUICK_MSECURITY_PAY";
    }

    /**
     * 支付宝支付
     * @param body
     * @param subject
     * @param orderNumber
     * @param money
     * @param notifyUrl
     */
    public KitPay(String body, String subject, String orderNumber, String money, String notifyUrl) {
        this.type = "ALIPAY";
        this.body = body;
        this.subject = subject;
        this.orderNumber = orderNumber;
        this.timeoutExpress = "30m";
        this.money = money;
        this.productCode = "QUICK_MSECURITY_PAY";
        this.notifyUrl = notifyUrl;
    }

    /**
     * 微信支付的值
     * @param money
     * @param orderNumber
     * @param notifyUrl
     */
    public KitPay(String money, String orderNumber, String notifyUrl) {
        this.type = "WEIXINPAY";
        this.money = money;
        this.orderNumber = orderNumber;
        this.notifyUrl = notifyUrl;
    }

    /**
     * 所有参数自定义
     * @param type
     * @param money
     * @param orderNumber
     * @param notifyUrl
     * @param body
     * @param subject
     * @param timeoutExpress
     * @param productCode
     */
    public KitPay(String type, String money, String orderNumber, String notifyUrl, String body, String subject, String timeoutExpress, String productCode) {
        this.type = type;
        this.money = money;
        this.orderNumber = orderNumber;
        this.notifyUrl = notifyUrl;
        this.body = body;
        this.subject = subject;
        this.timeoutExpress = timeoutExpress;
        this.productCode = productCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTimeoutExpress() {
        return timeoutExpress;
    }

    public void setTimeoutExpress(String timeoutExpress) {
        this.timeoutExpress = timeoutExpress;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }
}
