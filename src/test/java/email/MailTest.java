package email;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wen.util.MailUtil;

import javax.mail.MessagingException;
import java.io.File;
import java.io.UnsupportedEncodingException;

/**
 * Created by wen on 2016/7/19.
 */
public class MailTest {
    private static final Logger log = LoggerFactory.getLogger(MailTest.class);
    private static final String EMAIL = "szz-385788209@qq.com";
    private static final String CONTEXT = "来来来，你要的福利！！！！！";

//    public static void main(String[] args) {
//        for (int i = 0; i < 10; i++) {
//            File file = new File("D:\\zzzzz\\豆瓣妹子");
//            File files[] = file.listFiles();
//            try {
//                Thread.sleep(2000);
//                MailUtil.send(EMAIL, CONTEXT,files[i]);
//                log.info("当前发送的是第{}封邮件！",i);
//            } catch (MessagingException e) {
//                e.printStackTrace();
//            } catch (UnsupportedEncodingException e) {
//                e.printStackTrace();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    }
    public static void main(String[] args) {
        String str = "";
        str = RandomStringUtils.random(1000);
        for (int i = 0; i < 10; i++) {
            try {
                    Thread.sleep(2000);
                MailUtil.send(EMAIL, str);
            } catch (MessagingException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
