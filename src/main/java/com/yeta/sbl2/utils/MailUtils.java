package com.yeta.sbl2.utils;

import org.springframework.stereotype.Component;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * 邮件工具类
 * Created by YETA666 on 2018/4/29 0029.
 */
@Component
public class MailUtils {

    public void sendMail(String to, String code) throws MessagingException {
        //创建连接对象，连接到邮箱服务器
        Properties properties = new Properties();
        //连接协议
        properties.put("mail.transport.protocol", "smtp");
        //主机名
        properties.put("mail.smtp.host", "smtp.qq.com");
        //端口号
        properties.put("mail.smtp.port", 465);
        properties.put("mail.smtp.auth", "true");
        //设置是否使用ssl安全连接
        properties.put("mail.smtp.ssl.enable", "true");
        //设置是否显示debug信息  true 会在控制台显示相关信息
        //properties.put("mail.debug", "true");
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("429721783@qq.com", "taodada123456");
            }
        });
        //创建邮件对象
        Message message = new MimeMessage(session);
        //设置发件人
        message.setFrom(new InternetAddress("429721783@qq.com"));
        //设置收件人
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
        //设置主题
        message.setSubject("用户注册激活邮件");
        //设置正文
        message.setContent("<h1>激活请点击以下连接：</h1><h2>http://localhost:8080/sbl2/user/active?code=" + code + "</h2>", "text/html;charset=UTF-8");
        //发送邮件
        Transport transport = session.getTransport();
        transport.connect("429721783@qq.com", "aprvuhffzdiibgcf");
        transport.sendMessage(message, message.getAllRecipients());
    }
}
