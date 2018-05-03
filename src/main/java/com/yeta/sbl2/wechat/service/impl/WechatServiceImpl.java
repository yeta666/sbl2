package com.yeta.sbl2.wechat.service.impl;

import com.alibaba.fastjson.JSON;
import com.yeta.sbl2.wechat.domain.WechatCheckSignatureMessage;
import com.yeta.sbl2.wechat.service.WechatService;
import com.yeta.sbl2.utils.HttpUtil;
import com.yeta.sbl2.wechat.utils.WechatMessageUtil;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.*;

/**
 * 微信相关服务实现
 * Created by YETA666 on 2018/4/30 0030.
 */
@Service
public class WechatServiceImpl implements WechatService {

    @Autowired
    private WechatMessageUtil messageUtil;

    @Autowired
    private HttpUtil httpUtil;

    /**
     * 检验签微信加密签名方法
     *
     * @param wechatCheckSignatureMessage
     * @return
     */
    @Override
    public String checkSignature(WechatCheckSignatureMessage wechatCheckSignatureMessage) {
        //初始化返回结果
        String result = "";
        //排序
        String[] sortArr = new String[]{wechatCheckSignatureMessage.getToken(), wechatCheckSignatureMessage.getTimestamp(), wechatCheckSignatureMessage.getNonce()};
        Arrays.sort(sortArr);
        //拼接成一个字符串
        String tempStr = "";
        for (String str : sortArr) {
            tempStr += str;
        }
        //sha1加密
        tempStr = messageUtil.sha1(tempStr);
        //对比
        if (wechatCheckSignatureMessage.getSignature().equals(tempStr)) {
            //返回随机字符串
            result = wechatCheckSignatureMessage.getEchostr();
        }
        return result;
    }

    /**
     * 处理消息方法
     *
     * @param request
     * @return
     * @throws DocumentException
     * @throws IOException
     */
    @Override
    public String messageHandle(HttpServletRequest request) throws Exception {
        request.setCharacterEncoding("UTF-8");
        //xml转map
        Map<String, String> messageMap = messageUtil.xmlToMap(request);
        //初始化返回结果
        String result = "";
        //判断消息类型
        if (messageUtil.MESSAGE_TEXT.equals(messageMap.get("MsgType"))) {
            //文本消息
            String content = messageMap.get("Content");
            if ("1".equals(content)) {
                //回复文本消息
                result = messageUtil.initTextMessage(messageMap, messageUtil.RESPONSE_DESCRIPTION);
            } else if ("2".equals(content)) {
                //回复图文消息
                result = messageUtil.initNewsMessage(messageMap);
            } else if ("3".equals(content)) {
                //回复图片消息
                result = messageUtil.initImageMessage(messageMap);
            } else if ("4".equals(content)) {
                //回复音乐消息
                result = messageUtil.initMusicMessage(messageMap);
            } else if ("?".equals(content) || "？".equals(content)) {
                //回复文本消息
                result = messageUtil.initTextMessage(messageMap, messageUtil.RESPONSE_MENU);
            }
        } else if (messageUtil.MESSAGE_EVENT.equals(messageMap.get("MsgType"))) {
            //回复事件消息
            String eventType = messageMap.get("Event");
            if (messageUtil.MESSAGE_EVENT_SUBSCRIBE.equals(eventType)) {
                //关注事件
                result = messageUtil.RESPONSE_MENU;
            } else if (messageUtil.MESSAGE_EVENT_UNSUBSCRIBE.equals(eventType)) {
                //取消关注事件

            } else if (messageUtil.MESSAGE_EVENT_CLICK.equals(eventType)) {
                //点击事件
                result = messageUtil.initTextMessage(messageMap, messageUtil.RESPONSE_MENU);
            } else if (messageUtil.MESSAGE_EVENT_VIEW.equals(eventType)) {
                //跳转事件
            } else if (messageUtil.MESSAGE_EVENT_SCANCODE_PUSH.equals(eventType)) {
                //扫码事件
            }
        } else if (messageUtil.MESSAGE_LOCATION.equals(messageMap.get("MsgType"))) {
            //回复位置消息
            result = messageUtil.initTextMessage(messageMap, messageMap.toString());
        }
        return result;
    }

    private static final String APPID = "wxdb20c140c01bb64c";
    private static final String APPSECRET = "3914eccff2fcdf251ac30acb9ec2ce0f";
    private static final String REDIRECTURI = "http://pj53jx.natappfree.cc/sbl2/wechat/login/callback";

    /**
     * 微信登陆方法
     *
     * @param response
     * @throws IOException
     */
    @Override
    public void login(HttpServletResponse response) throws IOException {
        String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect"
                .replace("APPID", APPID)
                .replace("REDIRECT_URI", URLEncoder.encode(REDIRECTURI))
                .replace("SCOPE", "snsapi_userinfo");
        response.sendRedirect(url);
    }

    /**
     * 微信登陆回调方法
     *
     * @param request
     * @throws IOException
     */
    @Override
    public void callback(HttpServletRequest request) throws Exception {

        //获取code
        String code = request.getParameter("code");
        if ("10003".equals(code)) {
            throw new Exception("redirect_uri域名与后台配置不一致");
        } else if ("10004".equals(code)) {
            throw new Exception("此公众号被封禁");
        } else if ("10005".equals(code)) {
            throw new Exception("此公众号并没有这些scope的权限");
        } else if ("10006".equals(code)) {
            throw new Exception("必须关注此测试号");
        } else if ("10009".equals(code)) {
            throw new Exception("操作太频繁了，请稍后重试");
        } else if ("10010".equals(code)) {
            throw new Exception("scope不能为空");
        } else if ("10011".equals(code)) {
            throw new Exception("redirect_uri不能为空");
        } else if ("10012".equals(code)) {
            throw new Exception("appid不能为空");
        } else if ("10013".equals(code)) {
            throw new Exception("state不能为空");
        } else if ("10015".equals(code)) {
            throw new Exception("公众号未授权第三方平台，请检查授权状态");
        } else if ("10016".equals(code)) {
            throw new Exception("不支持微信开放平台的Appid，请使用公众号Appid");
        }

        //通过code获取access token
        String result1 = httpUtil.doGetStr("https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code"
                .replace("APPID", APPID)
                .replace("SECRET", APPSECRET)
                .replace("CODE", code));
        Map<String, Object> resultMap1 = (Map<String, Object>) JSON.parse(result1);
        if (resultMap1.get("errcode") != null) {
            throw new Exception("errcode: " + resultMap1.get("errcode") + "\t errmsg: " + resultMap1.get("errmsg"));
        }

        //拉取用户信息(需scope为 snsapi_userinfo)
        String result2 = httpUtil.doGetStr("https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN"
                .replace("ACCESS_TOKEN", resultMap1.get("access_token").toString())
                .replace("OPENID", resultMap1.get("openid").toString()));
        Map<String, String> resultMap2 = (Map<String, String>) JSON.parse(result2);
        if (resultMap2.get("errcode") != null) {
            throw new Exception("errcode: " + resultMap2.get("errcode") + "\t errmsg: " + resultMap2.get("errmsg"));
        }
        System.out.println(resultMap2);
    }
}
