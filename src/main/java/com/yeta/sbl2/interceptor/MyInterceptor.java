package com.yeta.sbl2.interceptor;

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
        //获取cookie，验证是否已经登陆
        /*String uri = request.getServletPath();
        if (!"/login1".equals(uri) || !"/login_out/login".equals(uri)) {
            if (request.getCookies() != null) {
                Cookie[] cookies = request.getCookies();
                for (Cookie cookie : cookies) {
                    if ("login".equals(cookie.getName()) && "true".equals(cookie.getValue())) {
                        //放行到请求URI

                    }else {
                        //重定向到登陆页面
                        response.sendRedirect("login1");
                    }
                }
            }else {
                //重定向到登陆页面
                response.sendRedirect("login1");
            }
        }*/
        return true;
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
