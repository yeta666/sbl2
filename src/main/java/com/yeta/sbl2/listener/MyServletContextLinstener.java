package com.yeta.sbl2.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * ServletContext监听器类
 * @author YETA
 * @date 2018/05/25/13:30
 */
@WebListener(value = "ServletContext监听器")
public class MyServletContextLinstener implements ServletContextListener {

    private static final Logger LOGGGE = LoggerFactory.getLogger(MyHttpSessionLinstener.class);

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        //servletContextEvent.getServletContext().getInitParameter("");
        LOGGGE.info("监听器contextInitialized...");

        //初始化的时候新建一个List，用来存所有Session
        List<HttpSession> sessionList = new ArrayList<>();
        servletContextEvent.getServletContext().setAttribute("onlines", sessionList);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        LOGGGE.info("监听器contextDestroyed...");

        //销毁的时候移除
        servletContextEvent.getServletContext().removeAttribute("onlines");
    }
}
