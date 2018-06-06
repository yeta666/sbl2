package com.yeta.sbl2.wechat_web.domain;

/**
 * 微信初始化返回消息封装类
 * @author YETA
 * @date 2018/06/06/10:09
 */
public class MPArticleList {

    private String Title;

    private String Digest;

    private String Cover;

    private String Url;

    public MPArticleList() {
    }

    public MPArticleList(String title, String digest, String cover, String url) {
        Title = title;
        Digest = digest;
        Cover = cover;
        Url = url;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDigest() {
        return Digest;
    }

    public void setDigest(String digest) {
        Digest = digest;
    }

    public String getCover() {
        return Cover;
    }

    public void setCover(String cover) {
        Cover = cover;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }

    @Override
    public String toString() {
        return "MPArticleList{" +
                "Title='" + Title + '\'' +
                ", Digest='" + Digest + '\'' +
                ", Cover='" + Cover + '\'' +
                ", Url='" + Url + '\'' +
                '}';
    }
}
