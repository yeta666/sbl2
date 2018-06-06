package com.yeta.sbl2.wechat_web.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * 微信初始化返回消息封装类
 * @author YETA
 * @date 2018/06/05/12:46
 */
public class ContactList {

    private Integer Uin;

    private String UserName;

    private String NickName;

    private String HeadImgUrl;

    private Integer ContactFlag;

    private Integer MemberCount;

    private List MemberList = new ArrayList();

    private String RemarkName;

    private Integer HideInputBarFlag;

    private Integer Sex;

    private String Signature;

    private Integer VerifyFlag;

    private Integer OwnerUin;

    private String PYInitial;

    private String PYQuanPin;

    private String RemarkPYInitial;

    private String RemarkPYQuanPin;

    private Integer StarFriend;

    private Integer AppAccountFlag;

    private Integer Statues;

    private String AttrStatus;

    private String Province;

    private String City;

    private String Alias;

    private Integer SnsFlag;

    private Integer UniFriend;

    private String DisplayName;

    private Integer ChatRoomId;

    private String KeyWord;

    private String EncryChatRoomId;

    private Integer IsOwner;

    public ContactList() {
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

    public Integer getContactFlag() {
        return ContactFlag;
    }

    public void setContactFlag(Integer contactFlag) {
        ContactFlag = contactFlag;
    }

    public Integer getMemberCount() {
        return MemberCount;
    }

    public void setMemberCount(Integer memberCount) {
        MemberCount = memberCount;
    }

    public List getMemberList() {
        return MemberList;
    }

    public void setMemberList(List memberList) {
        MemberList = memberList;
    }

    public String getRemarkName() {
        return RemarkName;
    }

    public void setRemarkName(String remarkName) {
        RemarkName = remarkName;
    }

    public Integer getHideInputBarFlag() {
        return HideInputBarFlag;
    }

    public void setHideInputBarFlag(Integer hideInputBarFlag) {
        HideInputBarFlag = hideInputBarFlag;
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

    public Integer getVerifyFlag() {
        return VerifyFlag;
    }

    public void setVerifyFlag(Integer verifyFlag) {
        VerifyFlag = verifyFlag;
    }

    public Integer getOwnerUin() {
        return OwnerUin;
    }

    public void setOwnerUin(Integer ownerUin) {
        OwnerUin = ownerUin;
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

    public Integer getStarFriend() {
        return StarFriend;
    }

    public void setStarFriend(Integer starFriend) {
        StarFriend = starFriend;
    }

    public Integer getAppAccountFlag() {
        return AppAccountFlag;
    }

    public void setAppAccountFlag(Integer appAccountFlag) {
        AppAccountFlag = appAccountFlag;
    }

    public Integer getStatues() {
        return Statues;
    }

    public void setStatues(Integer statues) {
        Statues = statues;
    }

    public String getAttrStatus() {
        return AttrStatus;
    }

    public void setAttrStatus(String attrStatus) {
        AttrStatus = attrStatus;
    }

    public String getProvince() {
        return Province;
    }

    public void setProvince(String province) {
        Province = province;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getAlias() {
        return Alias;
    }

    public void setAlias(String alias) {
        Alias = alias;
    }

    public Integer getSnsFlag() {
        return SnsFlag;
    }

    public void setSnsFlag(Integer snsFlag) {
        SnsFlag = snsFlag;
    }

    public Integer getUniFriend() {
        return UniFriend;
    }

    public void setUniFriend(Integer uniFriend) {
        UniFriend = uniFriend;
    }

    public String getDisplayName() {
        return DisplayName;
    }

    public void setDisplayName(String displayName) {
        DisplayName = displayName;
    }

    public Integer getChatRoomId() {
        return ChatRoomId;
    }

    public void setChatRoomId(Integer chatRoomId) {
        ChatRoomId = chatRoomId;
    }

    public String getKeyWord() {
        return KeyWord;
    }

    public void setKeyWord(String keyWord) {
        KeyWord = keyWord;
    }

    public String getEncryChatRoomId() {
        return EncryChatRoomId;
    }

    public void setEncryChatRoomId(String encryChatRoomId) {
        EncryChatRoomId = encryChatRoomId;
    }

    public Integer getIsOwner() {
        return IsOwner;
    }

    public void setIsOwner(Integer isOwner) {
        IsOwner = isOwner;
    }

    @Override
    public String toString() {
        return "ContactList{" +
                "Uin=" + Uin +
                ", UserName='" + UserName + '\'' +
                ", NickName='" + NickName + '\'' +
                ", HeadImgUrl='" + HeadImgUrl + '\'' +
                ", ContactFlag=" + ContactFlag +
                ", MemberCount=" + MemberCount +
                ", MemberList=" + MemberList +
                ", RemarkName='" + RemarkName + '\'' +
                ", HideInputBarFlag=" + HideInputBarFlag +
                ", Sex=" + Sex +
                ", Signature='" + Signature + '\'' +
                ", VerifyFlag=" + VerifyFlag +
                ", OwnerUin=" + OwnerUin +
                ", PYInitial='" + PYInitial + '\'' +
                ", PYQuanPin='" + PYQuanPin + '\'' +
                ", RemarkPYInitial='" + RemarkPYInitial + '\'' +
                ", RemarkPYQuanPin='" + RemarkPYQuanPin + '\'' +
                ", StarFriend=" + StarFriend +
                ", AppAccountFlag=" + AppAccountFlag +
                ", Statues=" + Statues +
                ", AttrStatus=" + AttrStatus +
                ", Province='" + Province + '\'' +
                ", City='" + City + '\'' +
                ", Alias='" + Alias + '\'' +
                ", SnsFlag=" + SnsFlag +
                ", UniFriend=" + UniFriend +
                ", DisplayName='" + DisplayName + '\'' +
                ", ChatRoomId=" + ChatRoomId +
                ", KeyWord='" + KeyWord + '\'' +
                ", EncryChatRoomId='" + EncryChatRoomId + '\'' +
                ", IsOwner=" + IsOwner +
                '}';
    }
}
