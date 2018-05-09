package cc.openkit.admin.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="kit_web_setting")
public class WebSetting {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer kitWebId;

    private String kitWebName;

    private Integer kitWebMessage;
    private Integer kitWebPush;
    private Integer kitWebFile;
    private Integer kitSigeSize;
    private Integer kitWebHellowAdvSize;
    private Integer kitSignActiveTime;

    public Integer getKitWebId() {
        return kitWebId;
    }

    public void setKitWebId(Integer kitWebId) {
        this.kitWebId = kitWebId;
    }

    public String getKitWebName() {
        return kitWebName;
    }

    public void setKitWebName(String kitWebName) {
        this.kitWebName = kitWebName == null ? null : kitWebName.trim();
    }

    public Integer getKitWebMessage() {
        return kitWebMessage;
    }

    public void setKitWebMessage(Integer kitWebMessage) {
        this.kitWebMessage = kitWebMessage;
    }

    public Integer getKitWebPush() {
        return kitWebPush;
    }

    public void setKitWebPush(Integer kitWebPush) {
        this.kitWebPush = kitWebPush;
    }

    public Integer getKitWebFile() {
        return kitWebFile;
    }

    public void setKitWebFile(Integer kitWebFile) {
        this.kitWebFile = kitWebFile;
    }

    public Integer getKitSigeSize() {
        return kitSigeSize;
    }

    public void setKitSigeSize(Integer kitSigeSize) {
        this.kitSigeSize = kitSigeSize;
    }

    public Integer getKitWebHellowAdvSize() {
        return kitWebHellowAdvSize;
    }

    public void setKitWebHellowAdvSize(Integer kitWebHellowAdvSize) {
        this.kitWebHellowAdvSize = kitWebHellowAdvSize;
    }

    public Integer getKitSignActiveTime() {
        return kitSignActiveTime;
    }

    public void setKitSignActiveTime(Integer kitSignActiveTime) {
        this.kitSignActiveTime = kitSignActiveTime;
    }
}