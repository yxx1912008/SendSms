package cc.openkit.admin.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="kit_message")
public class Message {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    private String keyId;

    private String ketSecret;

    private String signName;

    private String templateDayu;

    private String sid;

    private String appId;

    private String token;

    private String templateYzx;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKeyId() {
        return keyId;
    }

    public void setKeyId(String keyId) {
        this.keyId = keyId == null ? null : keyId.trim();
    }

    public String getKetSecret() {
        return ketSecret;
    }

    public void setKetSecret(String ketSecret) {
        this.ketSecret = ketSecret == null ? null : ketSecret.trim();
    }

    public String getSignName() {
        return signName;
    }

    public void setSignName(String signName) {
        this.signName = signName == null ? null : signName.trim();
    }

    public String getTemplateDayu() {
        return templateDayu;
    }

    public void setTemplateDayu(String templateDayu) {
        this.templateDayu = templateDayu == null ? null : templateDayu.trim();
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid == null ? null : sid.trim();
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId == null ? null : appId.trim();
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token == null ? null : token.trim();
    }

    public String getTemplateYzx() {
        return templateYzx;
    }

    public void setTemplateYzx(String templateYzx) {
        this.templateYzx = templateYzx == null ? null : templateYzx.trim();
    }
}