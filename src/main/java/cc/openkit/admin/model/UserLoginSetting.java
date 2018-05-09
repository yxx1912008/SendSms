package cc.openkit.admin.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="kit_user_login_setting")
public class UserLoginSetting {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer ulsId;

    private Integer ulsType;

    private Integer ulsIsWeixin;

    private Integer ulsIsQq;

    private Integer ulsIsWeibo;

    private Integer ulsIsRegister;

    private Integer ulsLoginIsRegistration;

    public Integer getUlsId() {
        return ulsId;
    }

    public void setUlsId(Integer ulsId) {
        this.ulsId = ulsId;
    }

    public Integer getUlsType() {
        return ulsType;
    }

    public void setUlsType(Integer ulsType) {
        this.ulsType = ulsType;
    }

    public Integer getUlsIsWeixin() {
        return ulsIsWeixin;
    }

    public void setUlsIsWeixin(Integer ulsIsWeixin) {
        this.ulsIsWeixin = ulsIsWeixin;
    }

    public Integer getUlsIsQq() {
        return ulsIsQq;
    }

    public void setUlsIsQq(Integer ulsIsQq) {
        this.ulsIsQq = ulsIsQq;
    }

    public Integer getUlsIsWeibo() {
        return ulsIsWeibo;
    }

    public void setUlsIsWeibo(Integer ulsIsWeibo) {
        this.ulsIsWeibo = ulsIsWeibo;
    }

    public Integer getUlsIsRegister() {
        return ulsIsRegister;
    }

    public void setUlsIsRegister(Integer ulsIsRegister) {
        this.ulsIsRegister = ulsIsRegister;
    }

    public Integer getUlsLoginIsRegistration() {
        return ulsLoginIsRegistration;
    }

    public void setUlsLoginIsRegistration(Integer ulsLoginIsRegistration) {
        this.ulsLoginIsRegistration = ulsLoginIsRegistration;
    }
}