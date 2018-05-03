package com.yeta.sbl2.wechat.domain;

/**
 * 微信文本消息参数类
 * Created by YETA666 on 2018/4/30 0030.
 */
public class WechatTextMessage extends WechatMessage {

    /**
     * 文本消息内容
     */
    private String Content;
    
    /**
     * 消息id，64位整型
     */
    private String MsgId;

    public WechatTextMessage() {
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getMsgId() {
        return MsgId;
    }

    public void setMsgId(String msgId) {
        MsgId = msgId;
    }

    @Override
    public String toString() {
        return "WechatTextMessage{" +
                "Content='" + Content + '\'' +
                ", MsgId='" + MsgId + '\'' +
                '}';
    }
}
