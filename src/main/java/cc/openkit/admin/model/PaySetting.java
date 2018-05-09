package cc.openkit.admin.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="kit_pay_setting")
public class PaySetting {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer psId;

    private Integer payIsAlipay;

    private Integer payIsWeixin;

    public Integer getPsId() {
        return psId;
    }

    public void setPsId(Integer psId) {
        this.psId = psId;
    }

    public Integer getPayIsAlipay() {
        return payIsAlipay;
    }

    public void setPayIsAlipay(Integer payIsAlipay) {
        this.payIsAlipay = payIsAlipay;
    }

    public Integer getPayIsWeixin() {
        return payIsWeixin;
    }

    public void setPayIsWeixin(Integer payIsWeixin) {
        this.payIsWeixin = payIsWeixin;
    }
}