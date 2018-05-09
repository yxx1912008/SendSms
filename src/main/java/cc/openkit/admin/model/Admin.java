package cc.openkit.admin.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="kit_admin")
public class Admin {
    @Id
    @GeneratedValue(generator = "UUID")
    private String kitAdminId;

    private String kitAdminUsername;

    private String kitAdminPassword;

    private String kitAdminName;
    private String kitAdminImgUrl;
    private Integer groupId;

    public String getKitAdminId() {
        return kitAdminId;
    }

    public void setKitAdminId(String kitAdminId) {
        this.kitAdminId = kitAdminId == null ? null : kitAdminId.trim();
    }

    public String getKitAdminUsername() {
        return kitAdminUsername;
    }

    public void setKitAdminUsername(String kitAdminUsername) {
        this.kitAdminUsername = kitAdminUsername == null ? null : kitAdminUsername.trim();
    }

    public String getKitAdminPassword() {
        return kitAdminPassword;
    }

    public void setKitAdminPassword(String kitAdminPassword) {
        this.kitAdminPassword = kitAdminPassword == null ? null : kitAdminPassword.trim();
    }

    public String getKitAdminName() {
        return kitAdminName;
    }

    public void setKitAdminName(String kitAdminName) {
        this.kitAdminName = kitAdminName == null ? null : kitAdminName.trim();
    }

    public String getKitAdminImgUrl() {
        return kitAdminImgUrl;
    }

    public void setKitAdminImgUrl(String kitAdminImgUrl) {
        this.kitAdminImgUrl = kitAdminImgUrl;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }
}