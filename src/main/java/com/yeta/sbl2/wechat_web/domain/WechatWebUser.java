package com.yeta.sbl2.wechat_web.domain;

import java.util.Arrays;

/**
 * 微信初始化返回消息封装类
 * @author YETA
 * @date 2018/06/05/12:42
 */
public class WechatWebUser {

    private BaseResponse BaseResponse;

    private Integer Count;

    private ContactList[] ContactList;

    private SyncKey SyncKey;

    private User user;

    private String ChatSet;

    private String SKey;

    private Integer ClientVersion;

    private Integer SystemTime;

    private Integer GrayScale;

    private Integer InviteStartCount;

    private Integer MPSubscribeMsgCount;

    private MPSubscribeMsgList[] MPSubscribeMsgList;

    private Integer  ClickReportInterval;

    public WechatWebUser() {
    }

    public com.yeta.sbl2.wechat_web.domain.BaseResponse getBaseResponse() {
        return BaseResponse;
    }

    public void setBaseResponse(com.yeta.sbl2.wechat_web.domain.BaseResponse baseResponse) {
        BaseResponse = baseResponse;
    }

    public Integer getCount() {
        return Count;
    }

    public void setCount(Integer count) {
        Count = count;
    }

    public com.yeta.sbl2.wechat_web.domain.ContactList[] getContactList() {
        return ContactList;
    }

    public void setContactList(com.yeta.sbl2.wechat_web.domain.ContactList[] contactList) {
        ContactList = contactList;
    }

    public com.yeta.sbl2.wechat_web.domain.SyncKey getSyncKey() {
        return SyncKey;
    }

    public void setSyncKey(com.yeta.sbl2.wechat_web.domain.SyncKey syncKey) {
        SyncKey = syncKey;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getChatSet() {
        return ChatSet;
    }

    public void setChatSet(String chatSet) {
        ChatSet = chatSet;
    }

    public String getSKey() {
        return SKey;
    }

    public void setSKey(String SKey) {
        this.SKey = SKey;
    }

    public Integer getClientVersion() {
        return ClientVersion;
    }

    public void setClientVersion(Integer clientVersion) {
        ClientVersion = clientVersion;
    }

    public Integer getSystemTime() {
        return SystemTime;
    }

    public void setSystemTime(Integer systemTime) {
        SystemTime = systemTime;
    }

    public Integer getGrayScale() {
        return GrayScale;
    }

    public void setGrayScale(Integer grayScale) {
        GrayScale = grayScale;
    }

    public Integer getInviteStartCount() {
        return InviteStartCount;
    }

    public void setInviteStartCount(Integer inviteStartCount) {
        InviteStartCount = inviteStartCount;
    }

    public Integer getMPSubscribeMsgCount() {
        return MPSubscribeMsgCount;
    }

    public void setMPSubscribeMsgCount(Integer MPSubscribeMsgCount) {
        this.MPSubscribeMsgCount = MPSubscribeMsgCount;
    }

    public com.yeta.sbl2.wechat_web.domain.MPSubscribeMsgList[] getMPSubscribeMsgList() {
        return MPSubscribeMsgList;
    }

    public void setMPSubscribeMsgList(com.yeta.sbl2.wechat_web.domain.MPSubscribeMsgList[] MPSubscribeMsgList) {
        this.MPSubscribeMsgList = MPSubscribeMsgList;
    }

    public Integer getClickReportInterval() {
        return ClickReportInterval;
    }

    public void setClickReportInterval(Integer clickReportInterval) {
        ClickReportInterval = clickReportInterval;
    }

    @Override
    public String toString() {
        return "WechatWebUser{" +
                "BaseResponse=" + BaseResponse +
                ", Count=" + Count +
                ", ContactList=" + Arrays.toString(ContactList) +
                ", SyncKey=" + SyncKey +
                ", user=" + user +
                ", ChatSet='" + ChatSet + '\'' +
                ", SKey='" + SKey + '\'' +
                ", ClientVersion=" + ClientVersion +
                ", SystemTime=" + SystemTime +
                ", GrayScale=" + GrayScale +
                ", InviteStartCount=" + InviteStartCount +
                ", MPSubscribeMsgCount=" + MPSubscribeMsgCount +
                ", MPSubscribeMsgList=" + Arrays.toString(MPSubscribeMsgList) +
                ", ClickReportInterval=" + ClickReportInterval +
                '}';
    }
}
