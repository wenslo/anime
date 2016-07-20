package org.wen.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 2016年7月19日16:04:53
 * 温海林
 * 发送邮件工具类
 */
public class MailUtil {
    private static final Logger log = LoggerFactory.getLogger(MailUtil.class);
    private static String HOST;
    private static String PROTOCOL;
    private static String PORT;
    private static String USER;
    private static String PWD;
    static {
        HOST = PropertiesUtil.getProperty("mail.host");
        PROTOCOL = PropertiesUtil.getProperty("mail.protocol");
        PORT = PropertiesUtil.getProperty("mail.port");
        USER = PropertiesUtil.getProperty("mail.user");
        PWD = PropertiesUtil.getProperty("mail.pwd");
    }
    /**
     * 获取session
     * @return
     */
    public static Session getSession(){
        //1.1构造一个保存用户名密码的Authenticator类
        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(USER,PWD);
            }
        };
        Properties props = new Properties();
        props.put("mail.smtp.host",HOST);
        props.put("mail.store.protocol",PROTOCOL);
        props.put("mail.smtp.port",PORT);
        props.put("mail.smtp.auth",true);
        return Session.getDefaultInstance(props,authenticator);
    }
    public static void send(final String toEmail,String content) throws MessagingException {
        Session session = getSession();
        log.debug("准备发送给"+toEmail);
        final Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(USER));
        msg.setRecipient(Message.RecipientType.TO,new InternetAddress(toEmail));
        msg.setSubject("帐号激活邮件");
        msg.setSentDate(new Date());
        msg.setContent(content,"text/html;charset=utf-8");
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        fixedThreadPool.execute(new Runnable() {
            public void run() {
                try {
                    log.info("邮件开始发送！");
                    Transport.send(msg);
                    log.info("成功发送邮件"+toEmail);
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
            }
        });
//        Thread t = new Thread(new Runnable() {
//            public void run() {
//
//            }
//        });
//        t.start();
    }
}
