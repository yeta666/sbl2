package com.yeta.sbl2.controller;

import com.yeta.sbl2.domain.WechatCheckSignatureMessage;
import com.yeta.sbl2.service.WechatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 微信相关接口定义
 * Created by YETA666 on 2018/4/30 0030.
 */
@RestController
public class WechatController {

    @Autowired
    private WechatService wechatService;

    @GetMapping(value = "/wechat")
    public String checkSignature(WechatCheckSignatureMessage wechatCheckSignatureMessage) {
        return wechatService.checkSignature(wechatCheckSignatureMessage);
    }

    @PostMapping(value = "/wechat")
    public String message(HttpServletRequest request) throws Exception {
        return wechatService.messageHandle(request);
    }

    @GetMapping(value = "/wechat/login")
    public void login(HttpServletResponse response) throws IOException {
        wechatService.login(response);
    }

    @GetMapping(value = "/wechat/login/callback")
    public void callback(HttpServletRequest request) throws Exception {
        wechatService.callback(request);
    }
}
