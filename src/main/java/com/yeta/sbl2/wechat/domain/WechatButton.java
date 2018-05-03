package com.yeta.sbl2.wechat.domain;

import java.util.Arrays;

/**
 * 微信菜单按钮
 * Created by YETA666 on 2018/5/3 0003.
 */
public class WechatButton {
    
    /**
     * 菜单标题，不超过16个字节，子菜单不超过60个字节
     */
    private String name;
    
    /**
     * 菜单的响应动作类型，view表示网页类型，click表示点击类型，miniprogram表示小程序类型
     */
    private String type;
    
    /**
     * 二级菜单数组，个数应为1~5个
     */
    private WechatButton[] sub_button;

    public WechatButton() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public WechatButton[] getSub_button() {
        return sub_button;
    }

    public void setSub_button(WechatButton[] sub_button) {
        this.sub_button = sub_button;
    }

    @Override
    public String toString() {
        return "WechatButton{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", sub_button=" + Arrays.toString(sub_button) +
                '}';
    }
}
