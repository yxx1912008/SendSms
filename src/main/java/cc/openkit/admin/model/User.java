package cc.openkit.admin.model;

import javax.persistence.*;
import java.util.Date;

@Table(name="kit_user")
public class User {
    @Id
    @GeneratedValue(generator = "UUID")
    private String userId;

    private String userName;

    private String userLoginName;

    private String userPassword;

    private String userPhone;

    private String userEmail;

    private String userImg;

    private String userToken;

    private Date userTime;

    private Date userUpdateTime;

    private Integer userType;
    private String userRecommendCode;
    private String userRecommendPerson;


    @Transient
    private String userRecommendName; // 推荐人姓名

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUserLoginName() {
        return userLoginName;
    }

    public void setUserLoginName(String userLoginName) {
        this.userLoginName = userLoginName == null ? null : userLoginName.trim();
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword == null ? null : userPassword.trim();
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone == null ? null : userPhone.trim();
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail == null ? null : userEmail.trim();
    }

    public String getUserImg() {
        return userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg == null ? null : userImg.trim();
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken == null ? null : userToken.trim();
    }

    public Date getUserTime() {
        return userTime;
    }

    public void setUserTime(Date userTime) {
        this.userTime = userTime;
    }

    public Date getUserUpdateTime() {
        return userUpdateTime;
    }

    public void setUserUpdateTime(Date userUpdateTime) {
        this.userUpdateTime = userUpdateTime;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public String getUserRecommendCode() {
        return userRecommendCode;
    }

    public void setUserRecommendCode(String userRecommendCode) {
        this.userRecommendCode = userRecommendCode;
    }

    public String getUserRecommendPerson() {
        return userRecommendPerson;
    }

    public void setUserRecommendPerson(String userRecommendPerson) {
        this.userRecommendPerson = userRecommendPerson;
    }

    public String getUserRecommendName() {
        return userRecommendName;
    }

    public void setUserRecommendName(String userRecommendName) {
        this.userRecommendName = userRecommendName;
    }
}