package com.yeta.sbl2.domain;

/**
 * 微信图片消息参数类
 * Created by YETA666 on 2018/5/2 0002.
 */
public class WechatImageMessage extends WechatMessage {

    /**
     * 图片消息
     */
    private WechatImage Image;

    public WechatImageMessage() {
    }

    public WechatImage getWechatImage() {
        return Image;
    }

    public void setWechatImage(WechatImage wechatImage) {
        this.Image = wechatImage;
    }

    @Override
    public String toString() {
        return "WechatImageMessage{" +
                "wechatImage=" + Image +
                '}';
    }
}
