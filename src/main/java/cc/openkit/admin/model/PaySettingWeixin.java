package cc.openkit.admin.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="kit_pay_setting_weixin")
public class PaySettingWeixin {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer pswId;

    private String pswAppId;

    private String pswAppSecret;

    private String pswAppKey;

    private String pswMchid;

    private String pswBody;

    private String pswPartnerKey;

    private String pswPartnerId;

    private String pswGrantType;

    private String pswGateUrl;

    private String pswNotifyUrl;

    private String pswSpbillCreateip;

    public Integer getPswId() {
        return pswId;
    }

    public void setPswId(Integer pswId) {
        this.pswId = pswId;
    }

    public String getPswAppId() {
        return pswAppId;
    }

    public void setPswAppId(String pswAppId) {
        this.pswAppId = pswAppId == null ? null : pswAppId.trim();
    }

    public String getPswAppSecret() {
        return pswAppSecret;
    }

    public void setPswAppSecret(String pswAppSecret) {
        this.pswAppSecret = pswAppSecret == null ? null : pswAppSecret.trim();
    }

    public String getPswAppKey() {
        return pswAppKey;
    }

    public void setPswAppKey(String pswAppKey) {
        this.pswAppKey = pswAppKey == null ? null : pswAppKey.trim();
    }

    public String getPswMchid() {
        return pswMchid;
    }

    public void setPswMchid(String pswMchid) {
        this.pswMchid = pswMchid == null ? null : pswMchid.trim();
    }

    public String getPswBody() {
        return pswBody;
    }

    public void setPswBody(String pswBody) {
        this.pswBody = pswBody == null ? null : pswBody.trim();
    }

    public String getPswPartnerKey() {
        return pswPartnerKey;
    }

    public void setPswPartnerKey(String pswPartnerKey) {
        this.pswPartnerKey = pswPartnerKey == null ? null : pswPartnerKey.trim();
    }

    public String getPswPartnerId() {
        return pswPartnerId;
    }

    public void setPswPartnerId(String pswPartnerId) {
        this.pswPartnerId = pswPartnerId == null ? null : pswPartnerId.trim();
    }

    public String getPswGrantType() {
        return pswGrantType;
    }

    public void setPswGrantType(String pswGrantType) {
        this.pswGrantType = pswGrantType == null ? null : pswGrantType.trim();
    }

    public String getPswGateUrl() {
        return pswGateUrl;
    }

    public void setPswGateUrl(String pswGateUrl) {
        this.pswGateUrl = pswGateUrl == null ? null : pswGateUrl.trim();
    }

    public String getPswNotifyUrl() {
        return pswNotifyUrl;
    }

    public void setPswNotifyUrl(String pswNotifyUrl) {
        this.pswNotifyUrl = pswNotifyUrl == null ? null : pswNotifyUrl.trim();
    }

    public String getPswSpbillCreateip() {
        return pswSpbillCreateip;
    }

    public void setPswSpbillCreateip(String pswSpbillCreateip) {
        this.pswSpbillCreateip = pswSpbillCreateip == null ? null : pswSpbillCreateip.trim();
    }
}