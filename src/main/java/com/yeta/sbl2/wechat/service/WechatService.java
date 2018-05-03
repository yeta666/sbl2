package com.yeta.sbl2.wechat.service;

import com.yeta.sbl2.wechat.domain.WechatCheckSignatureMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 微信相关服务
 * Created by YETA666 on 2018/4/30 0030.
 */
public interface WechatService {

    String checkSignature(WechatCheckSignatureMessage wechatCheckSignatureMessage);

    String messageHandle(HttpServletRequest request) throws Exception;

    void login(HttpServletResponse response) throws IOException;

    void callback(HttpServletRequest request) throws Exception;
}
