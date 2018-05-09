package cc.openkit.admin.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import java.util.Date;

@Table(name="kit_adv_hello")
public class AdvHello {
    @Id
    @GeneratedValue(generator = "UUID")
    private String ahId;

    private Integer ahSequence;

    private String ahImg;

    private String ahUrl;

    private Date ahTime;
//    @OrderBy
    private Date ahStartTime;

    private Date ahEndTime;

    private Integer ahType;

    private String ahTitle;

    private String ahSummary;

    public String getAhId() {
        return ahId;
    }

    public void setAhId(String ahId) {
        this.ahId = ahId;
    }

    public Integer getAhSequence() {
        return ahSequence;
    }

    public void setAhSequence(Integer ahSequence) {
        this.ahSequence = ahSequence;
    }

    public String getAhImg() {
        return ahImg;
    }

    public void setAhImg(String ahImg) {
        this.ahImg = ahImg;
    }

    public String getAhUrl() {
        return ahUrl;
    }

    public void setAhUrl(String ahUrl) {
        this.ahUrl = ahUrl;
    }

    public Date getAhTime() {
        return ahTime;
    }

    public void setAhTime(Date ahTime) {
        this.ahTime = ahTime;
    }

    public Date getAhStartTime() {
        return ahStartTime;
    }

    public void setAhStartTime(Date ahStartTime) {
        this.ahStartTime = ahStartTime;
    }

    public Date getAhEndTime() {
        return ahEndTime;
    }

    public void setAhEndTime(Date ahEndTime) {
        this.ahEndTime = ahEndTime;
    }

    public Integer getAhType() {
        return ahType;
    }

    public void setAhType(Integer ahType) {
        this.ahType = ahType;
    }

    public String getAhTitle() {
        return ahTitle;
    }

    public void setAhTitle(String ahTitle) {
        this.ahTitle = ahTitle;
    }

    public String getAhSummary() {
        return ahSummary;
    }

    public void setAhSummary(String ahSummary) {
        this.ahSummary = ahSummary;
    }
}