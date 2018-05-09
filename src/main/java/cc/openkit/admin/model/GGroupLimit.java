package cc.openkit.admin.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="kit_g_group_limit")
public class GGroupLimit {
    @Id
    @GeneratedValue(generator = "UUID")
    private String glId;

    private Integer groupId;

    private Integer limitId;

    private Integer groupC;
    private Integer groupR;
    private Integer groupU;
    private Integer groupD;

    public String getGlId() {
        return glId;
    }

    public void setGlId(String glId) {
        this.glId = glId == null ? null : glId.trim();
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getLimitId() {
        return limitId;
    }

    public void setLimitId(Integer limitId) {
        this.limitId = limitId;
    }

    public Integer getGroupC() {
        return groupC;
    }

    public void setGroupC(Integer groupC) {
        this.groupC = groupC;
    }

    public Integer getGroupR() {
        return groupR;
    }

    public void setGroupR(Integer groupR) {
        this.groupR = groupR;
    }

    public Integer getGroupU() {
        return groupU;
    }

    public void setGroupU(Integer groupU) {
        this.groupU = groupU;
    }

    public Integer getGroupD() {
        return groupD;
    }

    public void setGroupD(Integer groupD) {
        this.groupD = groupD;
    }
}