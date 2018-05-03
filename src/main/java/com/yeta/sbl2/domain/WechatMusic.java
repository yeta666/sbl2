package com.yeta.sbl2.domain;

/**
 * 微信音乐消息
 * Created by YETA666 on 2018/5/2 0002.
 */
public class WechatMusic {

    /**
     * 音乐标题
     */
    private String Title;
    
    /**
     * 音乐描述
     */
    private String Description;
    
    /**
     * 音乐链接
     */
    private String MusicUrl;
    
    /**
     * 高质量音乐链接，WIFI环境优先使用该链接播放音乐
     */
    private String HQMusicUrl;
    
    /**
     * 缩略图的媒体id，通过素材管理中的接口上传多媒体文件，得到的id
     */
    private String ThumbMediaId;

    public WechatMusic() {
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getMusicUrl() {
        return MusicUrl;
    }

    public void setMusicUrl(String musicUrl) {
        MusicUrl = musicUrl;
    }

    public String getHQMusicUrl() {
        return HQMusicUrl;
    }

    public void setHQMusicUrl(String HQMusicUrl) {
        this.HQMusicUrl = HQMusicUrl;
    }

    public String getThumbMediaId() {
        return ThumbMediaId;
    }

    public void setThumbMediaId(String thumbMediaId) {
        ThumbMediaId = thumbMediaId;
    }

    @Override
    public String toString() {
        return "WechatMusic{" +
                "Title='" + Title + '\'' +
                ", Description='" + Description + '\'' +
                ", MusicUrl='" + MusicUrl + '\'' +
                ", HQMusicUrl='" + HQMusicUrl + '\'' +
                ", ThumbMediaId='" + ThumbMediaId + '\'' +
                '}';
    }
}
