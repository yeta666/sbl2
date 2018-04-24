package com.yeta.sbl2.utils;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Http请求回复类
 * Created by YETA666 on 2018/4/19 0019.
 */
public class MyResponse {

    /**
     * 数据
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object data;

    /**
     * 错误
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message;

    public MyResponse() {
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
                "data=" + data +
                ", message='" + message + '\'' +
                '}';
    }
}
