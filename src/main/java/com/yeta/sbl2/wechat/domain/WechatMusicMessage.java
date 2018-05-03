package com.yeta.sbl2.wechat.domain;

/**
 * 微信音乐消息参数类
 * Created by YETA666 on 2018/5/2 0002.
 */
public class WechatMusicMessage extends WechatMessage {

    /**
     * 音乐消息
     */
    private WechatMusic Music;

    public WechatMusicMessage() {
    }

    public WechatMusic getWechatMusic() {
        return Music;
    }

    public void setWechatMusic(WechatMusic wechatMusic) {
        this.Music = wechatMusic;
    }

    @Override
    public String toString() {
        return "WechatMusicMessage{" +
                "wechatMusic=" + Music +
                '}';
    }
}
