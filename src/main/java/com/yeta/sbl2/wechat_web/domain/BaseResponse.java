package com.yeta.sbl2.wechat_web.domain;

/**
 * 微信初始化返回消息封装类
 * @author YETA
 * @date 2018/06/05/12:43
 */
public class BaseResponse {

    private Integer Ret;

    private String ErrMsg;

    public BaseResponse() {
    }

    public BaseResponse(Integer ret, String errMsg) {
        Ret = ret;
        ErrMsg = errMsg;
    }

    public Integer getRet() {
        return Ret;
    }

    public void setRet(Integer ret) {
        Ret = ret;
    }

    public String getErrMsg() {
        return ErrMsg;
    }

    public void setErrMsg(String errMsg) {
        ErrMsg = errMsg;
    }

    @Override
    public String toString() {
        return "BaseResponse{" +
                "Ret=" + Ret +
                ", ErrMsg='" + ErrMsg + '\'' +
                '}';
    }
}
