package com.yeta.sbl2.wechat.domain;

import java.util.List;

/**
 * 微信图文消息参数类
 * Created by YETA666 on 2018/4/30 0030.
 */
public class WechatNewsMessage extends WechatMessage {

    /**
     * 图文消息个数，限制为8条以内
     */
    private Integer ArticleCount;

    /**
     * 多条图文消息信息，默认第一个item为大图,注意，如果图文数超过8，则将会无响应
     */
    private List<WechatNews> Articles;

    public Integer getArticleCount() {
        return ArticleCount;
    }

    public void setArticleCount(Integer articleCount) {
        ArticleCount = articleCount;
    }

    public List<WechatNews> getArticles() {
        return Articles;
    }

    public void setArticles(List<WechatNews> articles) {
        Articles = articles;
    }

    @Override
    public String toString() {
        return "WechatNewsMessage{" +
                "ArticleCount='" + ArticleCount + '\'' +
                ", Articles=" + Articles +
                '}';
    }
}
