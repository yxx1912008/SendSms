package cc.openkit.admin.model.sms;

import java.io.Serializable;

/**
 * 短信模板
 */
public class YtxSmsContextOrder extends YtxSmsYtxParentOrder implements Serializable {

    /**  */
    private static final long serialVersionUID = 5772439374407775509L;

    /**
     * <p class="detail">
     * 功能：短信模板id
     * </p>
     *
     * @Fields smsTempid
     * @author panwuhai
     * @date 2016年5月13日
     */
    private String smsTempid;
    /**
     * <p class="detail">
     * 功能：短信内容 按占位符排序
     * new String[] { verifyCode,
     * String.valueOf(timeliness) }
     * </p>
     *
     * @Fields content
     * @author panwuhai
     * @date 2016年5月13日
     */
    private String contents[];

    public String getSmsTempid() {
        return smsTempid;
    }

    public void setSmsTempid(String smsTempid) {
        this.smsTempid = smsTempid;
    }

    public String[] getContents() {
        return contents;
    }

    public void setContents(String[] contents) {
        this.contents = contents;
    }

}
