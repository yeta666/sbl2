package com.yeta.sbl2.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Http请求回复类
 * Created by YETA666 on 2018/4/19 0019.
 */
public class MyResponse {

    /**
     * 请求是否成功
     */
    private boolean success = true;

    /**
     * 数据
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Object data;

    /**
     * 错误
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message;

    public MyResponse() {
    }

    public MyResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public MyResponse(Object data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "MyResponse{" +
                "success=" + success +
                ", data=" + data +
                ", message='" + message + '\'' +
                '}';
    }
}
