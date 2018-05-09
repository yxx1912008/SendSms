package cc.openkit.admin.model.sms;

import java.io.Serializable;

public abstract class YtxSmsYtxParentOrder implements Serializable {

    /**
     * <p class="detail">
     * 功能：
     * </p>
     *
     * @Fields serialVersionUID
     * @author panwuhai
     * @date 2016年5月13日
     */
    private static final long serialVersionUID = 285752858169472445L;

    /**
     * <p class="detail">
     * 功能：接收短信者(多人用英文逗号隔开，一次最多200人)
     * </p>
     *
     * @Fields receiverPhone
     * @author panwuhai
     * @date 2016年5月13日
     */
    protected String receiverPhone;

    /**
     * <p class="detail">
     * 功能：地址
     * </p>
     *
     * @Fields smsUrl
     * @author panwuhai
     * @date 2016年5月13日
     */
    protected String smsUrl;

    /**
     * <p class="detail">
     * 功能：端口
     * </p>
     *
     * @Fields smsPort
     * @author panwuhai
     * @date 2016年5月13日
     */
    protected String smsPort;

    /**
     * <p class="detail">
     * 功能：账号
     * </p>
     *
     * @Fields accountName
     * @author panwuhai
     * @date 2016年5月13日
     */
    protected String accountName;

    /**
     * <p class="detail">
     * 功能：密码
     * </p>
     *
     * @Fields accountPsd
     * @author panwuhai
     * @date 2016年5月13日
     */
    protected String accountPsd;

    /**
     * <p class="detail">
     * 功能：对接应用id
     * </p>
     *
     * @Fields appId
     * @author panwuhai
     * @date 2016年5月13日
     */
    protected String appId;

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

    /**
     * @return smsUrl
     */

    public String getSmsUrl() {
        return smsUrl;
    }

    /**
     * @param smsUrl
     */
    public void setSmsUrl(String smsUrl) {
        this.smsUrl = smsUrl;
    }

    /**
     * @return smsPort
     */

    public String getSmsPort() {
        return smsPort;
    }

    /**
     * @param smsPort
     */
    public void setSmsPort(String smsPort) {
        this.smsPort = smsPort;
    }

    /**
     * @return accountName
     */

    public String getAccountName() {
        return accountName;
    }

    /**
     * @param accountName
     */
    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    /**
     * @return accountPsd
     */

    public String getAccountPsd() {
        return accountPsd;
    }

    /**
     * @param accountPsd
     */
    public void setAccountPsd(String accountPsd) {
        this.accountPsd = accountPsd;
    }

    /**
     * @return appId
     */

    public String getAppId() {
        return appId;
    }

    /**
     * @param appId
     */
    public void setAppId(String appId) {
        this.appId = appId;
    }

}