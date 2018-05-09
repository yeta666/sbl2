package com.yeta.sbl2.controller;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import org.directwebremoting.ScriptBuffer;
import org.directwebremoting.ScriptSession;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.directwebremoting.proxy.dwr.Util;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by YETA666 on 2018/5/9 0009.
 */
@RestController
@RemoteProxy
public class DWRController {

    @RemoteMethod
    public void chat(String message) {
        //为所有的用户服务
        WebContext webContext = WebContextFactory.get();
        Collection<ScriptSession> sessions = webContext.getAllScriptSessions();
        //构建发送所需的JS脚本
        ScriptBuffer scriptBuffer = new ScriptBuffer();
        //调用客户端的JS脚本函数
        scriptBuffer.appendScript("callback(");
        //这个message可以被过滤处理一下，或者做其他的处理操作。这视需求而定。
        scriptBuffer.appendData(message);
        scriptBuffer.appendScript(")");
        Util util = new Util(sessions);
        util.addScript(scriptBuffer);
    }

    private Collection<ScriptSession> sessions;

    @RemoteMethod
    public void push(String message) {
        //为所有的用户服务
        WebContext webContext = WebContextFactory.get();
        sessions = webContext.getAllScriptSessions();

        MyThread myThread = new MyThread("myThread");
        myThread.start();
    }

    class MyThread extends Thread {

        private Thread thread;
        private String threadName;

        MyThread(String name) {
            threadName = name;
        }

        public void run() {
            while (true)
            {
                //构建发送所需的JS脚本
                ScriptBuffer scriptBuffer = new ScriptBuffer();
                //调用客户端的JS脚本函数
                scriptBuffer.appendScript("callback(");
                //这个message可以被过滤处理一下，或者做其他的处理操作。这视需求而定。
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
                scriptBuffer.appendData(simpleDateFormat.format(new Date()));
                scriptBuffer.appendScript(")");

                Util util = new Util(sessions);
                util.addScript(scriptBuffer);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public void start () {
            if (thread == null) {
                thread = new Thread (this, threadName);
                thread.start ();
            }
        }
    }
}
