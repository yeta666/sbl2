package com.yeta.sbl2.wechat_web.domain;

/**
 * 微信初始化返回消息封装类
 * @author YETA
 * @date 2018/06/05/12:55
 */
public class User {

    private Integer Uin;

    private String UserName;

    private String NickName;

    private String HeadImgUrl;

    private String RemarkName;

    private String PYInitial;

    private String PYQuanPin;

    private String RemarkPYInitial;

    private String RemarkPYQuanPin;

    private Integer HideInputBarFlag;

    private Integer StarFriend;

    private Integer Sex;

    private String Signature;

    private Integer AppAccountFlag;

    private Integer VerifyFlag;

    private Integer ContactFlag;

    private Integer WebWxPluginSwitch;

    private Integer HeadImgFlag;

    private Integer SnsFlag;

    public User() {
    }

    public Integer getUin() {
        return Uin;
    }

    public void setUin(Integer uin) {
        Uin = uin;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getNickName() {
        return NickName;
    }

    public void setNickName(String nickName) {
        NickName = nickName;
    }

    public String getHeadImgUrl() {
        return HeadImgUrl;
    }

    public void setHeadImgUrl(String headImgUrl) {
        HeadImgUrl = headImgUrl;
    }

    public String getRemarkName() {
        return RemarkName;
    }

    public void setRemarkName(String remarkName) {
        RemarkName = remarkName;
    }

    public String getPYInitial() {
        return PYInitial;
    }

    public void setPYInitial(String PYInitial) {
        this.PYInitial = PYInitial;
    }

    public String getPYQuanPin() {
        return PYQuanPin;
    }

    public void setPYQuanPin(String PYQuanPin) {
        this.PYQuanPin = PYQuanPin;
    }

    public String getRemarkPYInitial() {
        return RemarkPYInitial;
    }

    public void setRemarkPYInitial(String remarkPYInitial) {
        RemarkPYInitial = remarkPYInitial;
    }

    public String getRemarkPYQuanPin() {
        return RemarkPYQuanPin;
    }

    public void setRemarkPYQuanPin(String remarkPYQuanPin) {
        RemarkPYQuanPin = remarkPYQuanPin;
    }

    public Integer getHideInputBarFlag() {
        return HideInputBarFlag;
    }

    public void setHideInputBarFlag(Integer hideInputBarFlag) {
        HideInputBarFlag = hideInputBarFlag;
    }

    public Integer getStarFriend() {
        return StarFriend;
    }

    public void setStarFriend(Integer starFriend) {
        StarFriend = starFriend;
    }

    public Integer getSex() {
        return Sex;
    }

    public void setSex(Integer sex) {
        Sex = sex;
    }

    public String getSignature() {
        return Signature;
    }

    public void setSignature(String signature) {
        Signature = signature;
    }

    public Integer getAppAccountFlag() {
        return AppAccountFlag;
    }

    public void setAppAccountFlag(Integer appAccountFlag) {
        AppAccountFlag = appAccountFlag;
    }

    public Integer getVerifyFlag() {
        return VerifyFlag;
    }

    public void setVerifyFlag(Integer verifyFlag) {
        VerifyFlag = verifyFlag;
    }

    public Integer getContactFlag() {
        return ContactFlag;
    }

    public void setContactFlag(Integer contactFlag) {
        ContactFlag = contactFlag;
    }

    public Integer getWebWxPluginSwitch() {
        return WebWxPluginSwitch;
    }

    public void setWebWxPluginSwitch(Integer webWxPluginSwitch) {
        WebWxPluginSwitch = webWxPluginSwitch;
    }

    public Integer getHeadImgFlag() {
        return HeadImgFlag;
    }

    public void setHeadImgFlag(Integer headImgFlag) {
        HeadImgFlag = headImgFlag;
    }

    public Integer getSnsFlag() {
        return SnsFlag;
    }

    public void setSnsFlag(Integer snsFlag) {
        SnsFlag = snsFlag;
    }

    @Override
    public String toString() {
        return "User{" +
                "Uin=" + Uin +
                ", UserName='" + UserName + '\'' +
                ", NickName='" + NickName + '\'' +
                ", HeadImgUrl='" + HeadImgUrl + '\'' +
                ", RemarkName='" + RemarkName + '\'' +
                ", PYInitial='" + PYInitial + '\'' +
                ", PYQuanPin='" + PYQuanPin + '\'' +
                ", RemarkPYInitial='" + RemarkPYInitial + '\'' +
                ", RemarkPYQuanPin='" + RemarkPYQuanPin + '\'' +
                ", HideInputBarFlag=" + HideInputBarFlag +
                ", StarFriend=" + StarFriend +
                ", Sex=" + Sex +
                ", Signature='" + Signature + '\'' +
                ", AppAccountFlag=" + AppAccountFlag +
                ", VerifyFlag=" + VerifyFlag +
                ", ContactFlag=" + ContactFlag +
                ", WebWxPluginSwitch=" + WebWxPluginSwitch +
                ", HeadImgFlag=" + HeadImgFlag +
                ", SnsFlag=" + SnsFlag +
                '}';
    }
}
