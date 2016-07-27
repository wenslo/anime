package org.wen.util;

import java.io.*;
import java.util.Properties;

/**
 * 2016年7月19日16:03:01
 * 温海林
 * properties文件工具类
 */
public class PropertiesUtil {
    private static Properties properties;
    private static String url;
    static{
        url = "E:"+File.separator+"InterideaProject"+File.separator+"anime"+File.separator+"src"+File.separator+"main"+File.separator+"resources"+File.separator+"config.properties";
//        url = "E:"+File.separator+"InterideaProject"+File.separator+"anime"+File.separator+"src"+File.separator+"test"+File.separator+"resources"+File.separator+"config.properties";
        System.out.println("文件的地址为"+url);
        properties = new Properties();
        try{
            InputStream in = new BufferedInputStream(new FileInputStream(url));
            properties.load(in);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取属性
     * @param key  key
     * @return 返回读取到的properties文件的值
     */
    public static String getProperty(String key){
        return properties.getProperty(key);
    }

    public static void setProperties(String key,String value) throws IOException {
        OutputStream out = new FileOutputStream(url);
        properties.setProperty(key,value);
        properties.store(out,"『comments』Update key：" + key);
    }
}
