package com.yeta.sbl2.exception;

/**
 * 秒杀关闭异常
 * @author YETA
 * @date 2018/05/18/14:31
 */
public class SeckillCloseException extends SeckillException {

    public SeckillCloseException(String message) {
        super(message);
    }

    public SeckillCloseException(String message, Throwable cause) {
        super(message, cause);
    }
}
