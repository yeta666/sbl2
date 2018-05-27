package com.yeta.sbl2.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.List;

/**
 * Session监听器类
 * @author YETA
 * @date 2018/05/25/13:30
 */
@WebListener(value = "Session监听器")
public class MyHttpSessionLinstener implements HttpSessionListener {

    private static final Logger LOGGGE = LoggerFactory.getLogger(MyHttpSessionLinstener.class);

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        LOGGGE.info("监听器sessionCreated...{}...", httpSessionEvent.getSession().getId());

        List<HttpSession> sessionList = (List<HttpSession>) httpSessionEvent.getSession().getServletContext().getAttribute("onlines");
        sessionList.add(httpSessionEvent.getSession());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        LOGGGE.info("监听器sessionDestroyed...{}...", httpSessionEvent.getSession().getId());

        List<HttpSession> sessionList = (List<HttpSession>) httpSessionEvent.getSession().getServletContext().getAttribute("onlines");
        sessionList.remove(httpSessionEvent.getSession());
    }
}
