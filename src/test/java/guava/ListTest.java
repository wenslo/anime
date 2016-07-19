package guava;

import com.google.common.base.Charsets;
import com.google.common.base.Preconditions;
import com.google.common.io.Files;
import com.google.common.primitives.Ints;
import org.junit.Test;

import java.io.File;
import java.util.List;
import java.util.Objects;


/**
 * Created by wen on 2016/7/19.
 */
public class ListTest {
    @Test
    public void test(){
//        File file = new File(getClass().getResource("/text.txt").getFile());
//        List<String> line = null;
//        try {
//            line = Files.readLines(file, Charsets.UTF_8);
//            System.out.println(line);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        int a = 3;
        int b = 2;
        int compare = Ints.compare(a, b);
        System.out.println(compare);
        Preconditions.checkArgument(compare>0);//检查是否为true,为false的时候抛出IlligeArgumentException
        String value = "Strings";
        String str = Preconditions.checkNotNull(value);//检查是否为NULL,NULL的话为抛出NullPointerException
        System.out.println(str);
        boolean flag = Objects.equals("1","1");//判断两个参数是否相等，NULL 与 NULL 返回true
        flag = Objects.equals("234",null);
        System.out.println(flag);
        System.out.println(Objects.toString(this));

    }


}
