package email;

import org.junit.Test;
import org.wen.util.MailUtil;

import javax.mail.MessagingException;

/**
 * Created by wen on 2016/7/19.
 */
public class MailTest {
    private static final String EMAIL = "szz-385788209@qq.com";
    private static final String CONTEXT = "接受哥的邮件轰炸吧！！！！！";

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            try {
//                Thread.sleep(1000);
                MailUtil.send(EMAIL, CONTEXT);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }
    }
}
