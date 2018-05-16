package cc.openkit.admin.model;

import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "sms_temp")
public class smsTempDo {

    //模板 ID
    @Id
    private String  tempId;

    private Integer type;

    private String  title;

    private Integer placeholderNum;

    private String  shopuserid;

    private String  areaCode;

    private Integer preciseType;

    private String  content;

    private String  placeholderFiledType;

    private String  remark;

    private Date    gmtCreate;

    public String getTempId() {
        return tempId;
    }

    public void setTempId(String tempId) {
        this.tempId = tempId == null ? null : tempId.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Integer getPlaceholderNum() {
        return placeholderNum;
    }

    public void setPlaceholderNum(Integer placeholderNum) {
        this.placeholderNum = placeholderNum;
    }

    public String getShopuserid() {
        return shopuserid;
    }

    public void setShopuserid(String shopuserid) {
        this.shopuserid = shopuserid == null ? null : shopuserid.trim();
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode == null ? null : areaCode.trim();
    }

    public Integer getPreciseType() {
        return preciseType;
    }

    public void setPreciseType(Integer preciseType) {
        this.preciseType = preciseType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getPlaceholderFiledType() {
        return placeholderFiledType;
    }

    public void setPlaceholderFiledType(String placeholderFiledType) {
        this.placeholderFiledType = placeholderFiledType == null ? null : placeholderFiledType
            .trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }
}