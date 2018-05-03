package com.yeta.sbl2.domain;

/**
 * 微信获取到的凭证
 * Created by YETA666 on 2018/5/2 0002.
 */
public class WechatAccessToken {
    
    /**
     * 获取到的凭证
     */
    private String access_token = "";
    
    /**
     * 凭证有效时间，单位：秒
     */
    private long expires_in;
    
    /**
     * 错误码
     */
    private String errcode;
    
    /**
     * 错误消息
     */
    private String errmsg;

    public WechatAccessToken() {
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public long getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(long expires_in) {
        this.expires_in = expires_in;
    }

    public String getErrcode() {
        return errcode;
    }

    public void setErrcode(String errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    @Override
    public String toString() {
        return "WechatAccessToken{" +
                "access_token='" + access_token + '\'' +
                ", expires_in=" + expires_in +
                ", errcode='" + errcode + '\'' +
                ", errmsg='" + errmsg + '\'' +
                '}';
    }
}
