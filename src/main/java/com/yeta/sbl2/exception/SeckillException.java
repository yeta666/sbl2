package com.yeta.sbl2.exception;

/**
 * 秒杀异常
 * @author YETA
 * @date 2018/05/18/14:30
 */
public class SeckillException extends RuntimeException {

    public SeckillException(String message) {
        super(message);
    }

    public SeckillException(String message, Throwable cause) {
        super(message, cause);
    }
}
