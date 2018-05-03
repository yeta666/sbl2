package com.yeta.sbl2.wechat.domain;

/**
 * 微信菜单按钮view类型
 * Created by YETA666 on 2018/5/3 0003.
 */
public class WechatButtonView extends WechatButton {

    /**
     * 网页 链接，用户点击菜单可打开链接，不超过1024字节。 type为miniprogram时，不支持小程序的老版本客户端将打开本url
     */
    private String url;

    public WechatButtonView() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "WechatButtonView{" +
                "url='" + url + '\'' +
                '}';
    }
}
