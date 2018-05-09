package cc.openkit.admin.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="kit_pay_setting_alipay")
public class PaySettingAlipay {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer psaId;

    private String psaAppId;

    private String psaPrivateKey;

    private String psaPublicKey;

    private String psaServerUrl;

    private String psaDoMain;

    private String psaCharset;

    private String psaFormat;

    private String psaSignType;

    public Integer getPsaId() {
        return psaId;
    }

    public void setPsaId(Integer psaId) {
        this.psaId = psaId;
    }

    public String getPsaAppId() {
        return psaAppId;
    }

    public void setPsaAppId(String psaAppId) {
        this.psaAppId = psaAppId == null ? null : psaAppId.trim();
    }

    public String getPsaPrivateKey() {
        return psaPrivateKey;
    }

    public void setPsaPrivateKey(String psaPrivateKey) {
        this.psaPrivateKey = psaPrivateKey == null ? null : psaPrivateKey.trim();
    }

    public String getPsaPublicKey() {
        return psaPublicKey;
    }

    public void setPsaPublicKey(String psaPublicKey) {
        this.psaPublicKey = psaPublicKey == null ? null : psaPublicKey.trim();
    }

    public String getPsaServerUrl() {
        return psaServerUrl;
    }

    public void setPsaServerUrl(String psaServerUrl) {
        this.psaServerUrl = psaServerUrl == null ? null : psaServerUrl.trim();
    }

    public String getPsaDoMain() {
        return psaDoMain;
    }

    public void setPsaDoMain(String psaDoMain) {
        this.psaDoMain = psaDoMain == null ? null : psaDoMain.trim();
    }

    public String getPsaCharset() {
        return psaCharset;
    }

    public void setPsaCharset(String psaCharset) {
        this.psaCharset = psaCharset == null ? null : psaCharset.trim();
    }

    public String getPsaFormat() {
        return psaFormat;
    }

    public void setPsaFormat(String psaFormat) {
        this.psaFormat = psaFormat == null ? null : psaFormat.trim();
    }

    public String getPsaSignType() {
        return psaSignType;
    }

    public void setPsaSignType(String psaSignType) {
        this.psaSignType = psaSignType == null ? null : psaSignType.trim();
    }
}