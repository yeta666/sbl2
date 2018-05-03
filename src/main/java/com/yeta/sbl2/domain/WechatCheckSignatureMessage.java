package com.yeta.sbl2.domain;

/**
 * 检验签微信加密签名参数类
 * Created by YETA666 on 2018/4/30 0030.
 */
public class WechatCheckSignatureMessage {

    /**
     * 微信加密签名
     */
    private String signature;

    /**
     * 时间戳
     */
    private String timestamp;
    
    /**
     * 随机数
     */
    private String nonce;
    
    /**
     * 随机字符串
     */
    private String echostr;
    
    /**
     * token
     */
    private String token = "yeta";

    public WechatCheckSignatureMessage() {
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getNonce() {
        return nonce;
    }

    public void setNonce(String nonce) {
        this.nonce = nonce;
    }

    public String getEchostr() {
        return echostr;
    }

    public void setEchostr(String echostr) {
        this.echostr = echostr;
    }

    public String getToken() {
        return token;
    }

    @Override
    public String toString() {
        return "WechatCheckSignatureMessage{" +
                "signature='" + signature + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", nonce='" + nonce + '\'' +
                ", echostr='" + echostr + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
