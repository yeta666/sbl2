package com.yeta.sbl2.domain;

import java.util.Arrays;

/**
 * 微信菜单
 * Created by YETA666 on 2018/5/3 0003.
 */
public class WechatMenu {

    private WechatButton[] button;

    public WechatMenu() {
    }

    public WechatButton[] getButton() {
        return button;
    }

    public void setButton(WechatButton[] button) {
        this.button = button;
    }

    @Override
    public String toString() {
        return "WechatMenu{" +
                "button=" + Arrays.toString(button) +
                '}';
    }
}
