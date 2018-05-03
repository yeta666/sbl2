package com.yeta.sbl2.wechat.domain;

/**
 * 微信图片消息
 * Created by YETA666 on 2018/5/2 0002.
 */
public class WechatImage {

    /**
     * 通过素材管理中的接口上传多媒体文件，得到的id
     */
    private String MediaId;

    public WechatImage() {
    }

    public String getMediaId() {
        return MediaId;
    }

    public void setMediaId(String mediaId) {
        MediaId = mediaId;
    }

    @Override
    public String toString() {
        return "WechatImage{" +
                "MediaId='" + MediaId + '\'' +
                '}';
    }
}
