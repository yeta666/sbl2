package com.yeta.sbl2.wechat_web.domain;

import java.util.Arrays;

/**
 * 微信初始化返回消息封装类
 * @author YETA
 * @date 2018/06/06/10:08
 */
public class MPSubscribeMsgList {

    private String UserName;

    private Integer MPArticleCount;

    private MPArticleList[] MPArticleList;

    private Integer Time;

    private String NickName;

    public MPSubscribeMsgList() {
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public Integer getMPArticleCount() {
        return MPArticleCount;
    }

    public void setMPArticleCount(Integer MPArticleCount) {
        this.MPArticleCount = MPArticleCount;
    }

    public com.yeta.sbl2.wechat_web.domain.MPArticleList[] getMPArticleList() {
        return MPArticleList;
    }

    public void setMPArticleList(com.yeta.sbl2.wechat_web.domain.MPArticleList[] MPArticleList) {
        this.MPArticleList = MPArticleList;
    }

    public Integer getTime() {
        return Time;
    }

    public void setTime(Integer time) {
        Time = time;
    }

    public String getNickName() {
        return NickName;
    }

    public void setNickName(String nickName) {
        NickName = nickName;
    }

    @Override
    public String toString() {
        return "MPSubscribeMsgList{" +
                "UserName='" + UserName + '\'' +
                ", MPArticleCount=" + MPArticleCount +
                ", MPArticleList=" + Arrays.toString(MPArticleList) +
                ", Time=" + Time +
                ", NickName='" + NickName + '\'' +
                '}';
    }
}
