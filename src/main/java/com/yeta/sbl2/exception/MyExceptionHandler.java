package com.yeta.sbl2.exception;

import com.yeta.sbl2.utils.MyResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 异常处理类
 * Created by YETA666 on 2018/4/19 0019.
 */
@RestControllerAdvice
public class MyExceptionHandler {

    /**
     * 默认异常处理
     * @param e
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = Exception.class)
    public MyResponse defaultExceptionHandler(Exception e) throws Exception {
        e.printStackTrace();
        MyResponse myResponse = new MyResponse();
        myResponse.setSuccess(false);
        myResponse.setMessage(e.getMessage());
        return myResponse;
    }
}
