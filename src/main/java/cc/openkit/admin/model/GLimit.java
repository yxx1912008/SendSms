package cc.openkit.admin.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="kit_g_limit")
public class GLimit {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer limitId;

    private Integer limitSequence;

    private String limitIco;

    private String limitTitle;

    private String limitAction;

    private Integer limitLeaderid;

    private Integer limitSystem;

    public Integer getLimitId() {
        return limitId;
    }

    public void setLimitId(Integer limitId) {
        this.limitId = limitId;
    }

    public Integer getLimitSequence() {
        return limitSequence;
    }

    public void setLimitSequence(Integer limitSequence) {
        this.limitSequence = limitSequence;
    }

    public String getLimitIco() {
        return limitIco;
    }

    public void setLimitIco(String limitIco) {
        this.limitIco = limitIco == null ? null : limitIco.trim();
    }

    public String getLimitTitle() {
        return limitTitle;
    }

    public void setLimitTitle(String limitTitle) {
        this.limitTitle = limitTitle == null ? null : limitTitle.trim();
    }

    public String getLimitAction() {
        return limitAction;
    }

    public void setLimitAction(String limitAction) {
        this.limitAction = limitAction == null ? null : limitAction.trim();
    }

    public Integer getLimitLeaderid() {
        return limitLeaderid;
    }

    public void setLimitLeaderid(Integer limitLeaderid) {
        this.limitLeaderid = limitLeaderid;
    }

    public Integer getLimitSystem() {
        return limitSystem;
    }

    public void setLimitSystem(Integer limitSystem) {
        this.limitSystem = limitSystem;
    }
}