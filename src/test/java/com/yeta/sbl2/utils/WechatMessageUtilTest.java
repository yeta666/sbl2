package com.yeta.sbl2.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by YETA666 on 2018/5/2 0002.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class WechatMessageUtilTest {

    @Autowired
    private WechatMessageUtil wechatMessageUtil;

    @Test
    public void getAccessToken() throws Exception {
        System.out.println(wechatMessageUtil.getAccessToken());
    }

    @Test
    public void upload() throws Exception {
        System.out.println(wechatMessageUtil.uploadGetMedialId("F:/Projects/Java/sbl2/src/main/resources/static/img/yeta.png", "image"));
    }

    @Test
    public void initMenu() throws Exception {
        wechatMessageUtil.initMenu();
    }

}