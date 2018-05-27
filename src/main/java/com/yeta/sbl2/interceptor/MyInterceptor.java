package com.yeta.sbl2.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 拦截器类
 * 拦截器可以拿到原始的HTTP请求和响应的信息，也可以拿到方法的信息，但是拿不到参数
 * Created by YETA666 on 2018/4/22 0022.
 */
@Component
public class MyInterceptor implements HandlerInterceptor {

    @Value("${server.servlet.context-path}")
    private String contextPath;

    @Value("${permit.uri}")
    private String permitUri;

    private static final Logger LOGGER = LoggerFactory.getLogger(MyInterceptor.class);

    /**
     * 判断请求uri是在允许范围内
     * @param uri
     * @return
     */
    public boolean isPremited(String uri) {
        String[] permitUriArr = permitUri.split(",");
        for (String permit : permitUriArr) {
            if (uri.indexOf(permit) != -1) {
                return true;
            }
        }
        return false;
    }

    /**
     * 在请求处理之前调用
     * @param request
     * @param response
     * @param handler
     * @return  true表示通过拦截，返回请求目标
     *          false表示没有通过拦截，返回空白页面
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        LOGGER.info("拦截器preHandle...");

        //设置请求编码
        request.setCharacterEncoding("UTF-8");

        //获取请求uri
        String uri = request.getServletPath();

        //判断是否访问的是允许的资源
        if (isPremited(uri)) {
            LOGGER.info("请求访问：" + uri + "，允许访问的资源，放行！");
            return true;
        }else {
            if (request.getCookies() != null) {
                Cookie[] cookies = request.getCookies();
                boolean isLogin = false;
                for (Cookie cookie : cookies) {
                    //验证cookie验证用户是否登陆
                    if ("sbl2Login".equals(cookie.getName()) && "true".equals(cookie.getValue().split("#")[0])) {
                        isLogin = true;
                    }
                }
                //如果登陆，则放行
                if (isLogin) {
                    //放行到请求URI
                    LOGGER.info("请求访问：" + uri + "，已登录，放行！");
                    return true;
                }
            }
        }

        //未登录，重定向到登陆页面
        LOGGER.info("请求访问：" + uri + "，未登录，拦截！");
        response.sendRedirect(contextPath + "/login");
        return false;
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

        LOGGER.info("拦截器postHandle...");

        //设置响应编码
        response.setCharacterEncoding("UTF-8");
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
        LOGGER.info("拦截器afterCompletion...");
    }
}
