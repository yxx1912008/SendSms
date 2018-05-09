package cc.openkit.admin.vo;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="kit_g_limit")
public class GLimitVo {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer limitId;

    private Integer limitSequence;

    private String limitIco;

    private String limitTitle;

    private String limitAction;

    private Integer limitLeaderid;

    private Integer limitSystem;

    // 控制修改的时候的显示用的
    private Integer type;
    private Integer c;
    private Integer r;
    private Integer u;
    private Integer d;

    public GLimitVo (){}

    public GLimitVo (Integer limitId,
                     Integer limitSequence,
                     String limitIco,
                     String limitTitle,
                     String limitAction,
                     Integer limitLeaderid,
                     Integer limitSystem){
        this.limitId = limitId;
        this.limitSequence = limitSequence;
        this.limitIco = limitIco;
        this.limitTitle = limitTitle;
        this.limitAction = limitAction;
        this.limitLeaderid = limitLeaderid;
        this.limitSystem = limitSystem;
        this.type = 2;
        this.c = 2;
        this.r = 2;
        this.u = 2;
        this.d = 2;
    }

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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getC() {
        return c;
    }

    public void setC(Integer c) {
        this.c = c;
    }

    public Integer getR() {
        return r;
    }

    public void setR(Integer r) {
        this.r = r;
    }

    public Integer getU() {
        return u;
    }

    public void setU(Integer u) {
        this.u = u;
    }

    public Integer getD() {
        return d;
    }

    public void setD(Integer d) {
        this.d = d;
    }
}