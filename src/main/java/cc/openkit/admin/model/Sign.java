package cc.openkit.admin.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name="kit_sign")
public class Sign {
    @Id
    @GeneratedValue(generator = "UUID")
    private String signId;

    private String signPhone;

    private String signCode;

    private Date signTime;

    private Integer signType;


    public Sign() {
    }

    public Sign(String signId,String signPhone, String signCode, Date signTime, Integer signType) {
        this.signId = signId;
        this.signPhone = signPhone;
        this.signCode = signCode;
        this.signTime = signTime;
        this.signType = signType;
    }

    public String getSignId() {
        return signId;
    }

    public void setSignId(String signId) {
        this.signId = signId == null ? null : signId.trim();
    }

    public String getSignPhone() {
        return signPhone;
    }

    public void setSignPhone(String signPhone) {
        this.signPhone = signPhone == null ? null : signPhone.trim();
    }

    public String getSignCode() {
        return signCode;
    }

    public void setSignCode(String signCode) {
        this.signCode = signCode == null ? null : signCode.trim();
    }

    public Date getSignTime() {
        return signTime;
    }

    public void setSignTime(Date signTime) {
        this.signTime = signTime;
    }

    public Integer getSignType() {
        return signType;
    }

    public void setSignType(Integer signType) {
        this.signType = signType;
    }
}