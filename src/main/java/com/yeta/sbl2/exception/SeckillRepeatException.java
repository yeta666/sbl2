package com.yeta.sbl2.exception;

/**
 * 秒杀重复异常
 * @author YETA
 * @date 2018/05/18/14:31
 */
public class SeckillRepeatException extends SeckillException {

    public SeckillRepeatException(String message) {
        super(message);
    }

    public SeckillRepeatException(String message, Throwable cause) {
        super(message, cause);
    }
}
