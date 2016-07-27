package org.wen.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 2016年7月19日16:04:53
 * 温海林
 * 发送邮件工具类
 */
public class MailUtilForFile {
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
     * @return 返回发送邮件的Session
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
    public static void send(final String toEmail,String content,File attachment) throws MessagingException, UnsupportedEncodingException {
        Session session = getSession();
        log.debug("准备发送给"+toEmail);
        final Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(USER));
        msg.setRecipient(Message.RecipientType.TO,new InternetAddress(toEmail));
        msg.setSubject("帐号激活邮件");
        msg.setSentDate(new Date());
        msg.setContent(content,"text/html;charset=utf-8");
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        // 向multipart对象中添加邮件的各个部分内容，包括文本内容和附件
        Multipart multipart = new MimeMultipart();
        // 添加邮件正文
        BodyPart contentPart = new MimeBodyPart();
        contentPart.setContent(content, "text/html;charset=UTF-8");
        multipart.addBodyPart(contentPart);
        if (attachment != null) {
            BodyPart attachmentBodyPart = new MimeBodyPart();
            DataSource source = new FileDataSource(attachment);
            attachmentBodyPart.setDataHandler(new DataHandler(source));

            // 网上流传的解决文件名乱码的方法，其实用MimeUtility.encodeWord就可以很方便的搞定
            // 这里很重要，通过下面的Base64编码的转换可以保证你的中文附件标题名在发送时不会变成乱码
            //sun.misc.BASE64Encoder enc = new sun.misc.BASE64Encoder();
            //messageBodyPart.setFileName("=?GBK?B?" + enc.encode(attachment.getName().getBytes()) + "?=");

            //MimeUtility.encodeWord可以避免文件名乱码
            attachmentBodyPart.setFileName(MimeUtility.encodeWord(attachment.getName()));
            multipart.addBodyPart(attachmentBodyPart);
        }
        msg.setContent(multipart);
        msg.saveChanges();
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
