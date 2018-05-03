package com.yeta.sbl2.domain;

/**
 * 微信菜单按钮click类型
 * Created by YETA666 on 2018/5/3 0003.
 */
public class WechatButtonClick extends WechatButton {
    
    /**
     * 菜单KEY值，用于消息接口推送，不超过128字节
     */
    private String key;

    public WechatButtonClick() {
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "WechatButtonClick{" +
                "key='" + key + '\'' +
                '}';
    }
}
