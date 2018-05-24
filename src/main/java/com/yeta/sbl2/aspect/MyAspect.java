package com.yeta.sbl2.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Aspect类
 * Aspect可以拿到原始的HTTP请求和响应的信息，也可以拿到方法的信息，还可以拿到参数
 * Created by YETA666 on 2018/4/22 0022.
 */
@Aspect
@Component
public class MyAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyAspect.class);

    @Pointcut("execution(public * com.yeta.sbl2.controller.*.*(..))")
    public void log() {

    }

    @Before("log()")
    public void doBefore(JoinPoint joinPoint) {

        LOGGER.info("Aspect doBefore...");

        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        LOGGER.info("url={}", request.getRequestURL());
        LOGGER.info("method={}", request.getMethod());
        LOGGER.info("ip={}", request.getRemoteAddr());
        LOGGER.info("classMethod={}", joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        LOGGER.info("args={}", joinPoint.getArgs());
        HttpServletResponse response = requestAttributes.getResponse();
        //设置哪url可以跨域请求到本域
        //response.setHeader("Access-Control-Allow-Origin", "*");
    }

    @After("log()")
    public void doAfter() {
        LOGGER.info("Aspect doAfter...");
    }

    @AfterReturning(returning = "object", pointcut = "log()")
    public void doAfterReturning(Object object) {

        LOGGER.info("Aspect doAfterReturning...");

        if (object != null) {
            LOGGER.info("response={}", object.toString());
        }
    }

}
