package cc.openkit.admin.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="kit_push_jpush")
public class PushJpush {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer pjId;

    private String pjAppkey;

    private String pjMastersecret;

    public Integer getPjId() {
        return pjId;
    }

    public void setPjId(Integer pjId) {
        this.pjId = pjId;
    }

    public String getPjAppkey() {
        return pjAppkey;
    }

    public void setPjAppkey(String pjAppkey) {
        this.pjAppkey = pjAppkey == null ? null : pjAppkey.trim();
    }

    public String getPjMastersecret() {
        return pjMastersecret;
    }

    public void setPjMastersecret(String pjMastersecret) {
        this.pjMastersecret = pjMastersecret == null ? null : pjMastersecret.trim();
    }
}