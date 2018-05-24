package com.yeta.sbl2.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * 过滤器类
 * 过滤器可以拿到原始的HTTP请求和响应的信息，但是拿不到方法的信息
 * @author YETA
 * @date 2018/05/24/20:25
 */
@Component      //这种方式用于配置自定义过滤器
@WebFilter(filterName = "MyFilter", urlPatterns = "/*")       //注解方式配置
public class MyFilter implements Filter {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        LOGGER.info("过滤器init...");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        LOGGER.info("过滤器doFilter前...");
        filterChain.doFilter(servletRequest, servletResponse);
        LOGGER.info("过滤器doFilter后...");
    }

    @Override
    public void destroy() {
        LOGGER.info("过滤器destroy...");
    }
}
