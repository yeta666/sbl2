package com.yeta.sbl2.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拦截器类
 * Created by YETA666 on 2018/4/22 0022.
 */
public class MyInterceptor implements HandlerInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyInterceptor.class);

    /**
     * 允许访问的uri
     */
    private static final String[] PERMIT_URI = {
            "/login",
            "/user/login",
            "/js/jquery-2.1.0.js"
    };

    /**
     * 在请求处理之前调用
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //获取请求uri
        String uri = request.getServletPath();

        //判断请求uri是否允许
        boolean permitFlag = false;
        for (String permit : PERMIT_URI) {
            if (permit.equals(uri)) {
                permitFlag = true;
            }
        }

        if (permitFlag) {
            return true;
        }else {
            if (request.getCookies() != null) {
                Cookie[] cookies = request.getCookies();
                for (Cookie cookie : cookies) {
                    if ("sbl2Login".equals(cookie.getName()) && "true".equals(cookie.getValue().split("#")[0])) {
                        //放行到请求URI
                        LOGGER.info("请求访问：" + uri + "，已登录，放行！");
                        return true;
                    } else {
                        //重定向到登陆页面
                        LOGGER.info("请求访问：" + uri + "，未登录，拦截！");
                        response.sendRedirect("login");
                        return false;
                    }
                }
            } else {
                //重定向到登陆页面
                LOGGER.info("请求访问：" + uri + "，未登录，拦截！");
                response.sendRedirect("login");
                return false;
            }
            return false;
        }
    }

    /**
     * 在请求处理之后调用
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    /**
     * 在请求结束之后调用
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
