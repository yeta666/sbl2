package com.yeta.sbl2.wechat.domain;

/**
 * 微信媒体文件上传后，获取标识
 * Created by YETA666 on 2018/5/2 0002.
 */
public class WechatMediaId {
    
    /**
     * 媒体文件类型，分别有图片（image）、语音（voice）、视频（video）和缩略图（thumb，主要用于视频与音乐格式的缩略图）
     */
    private String type;
    
    /**
     * 媒体文件上传后，获取标识
     */
    private String media_id = "";

    /**
     * 媒体文件上传时间戳
     */
    private long created_at;
    
    /**
     * 错误码
     */
    private String errcode;
    
    /**
     * 错误消息
     */
    private String errmsg;

    public WechatMediaId() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMedia_id() {
        return media_id;
    }

    public void setMedia_id(String media_id) {
        this.media_id = media_id;
    }

    public long getCreated_at() {
        return created_at;
    }

    public void setCreated_at(long created_at) {
        this.created_at = created_at;
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
        return "WechatMediaId{" +
                "type='" + type + '\'' +
                ", media_id='" + media_id + '\'' +
                ", created_at=" + created_at +
                ", errcode='" + errcode + '\'' +
                ", errmsg='" + errmsg + '\'' +
                '}';
    }
}
