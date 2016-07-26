package Object;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;

/**
 * Created by wen on 2016/7/22.
 */
public class RefObject {
    /**
     * 把新的对象中有值的成员变量的值，赋给旧的对象中
     */
    public static void setNewValue1(Object orig, Object dest) throws Exception {
        Field[] destFields = dest.getClass().getDeclaredFields();
        for (Field field : destFields) {
            field.setAccessible(true);
            Object value = field.get(dest);
            if (value == null)
                continue;
            if (value instanceof String && StringUtils.isBlank((String) value))
                continue;
            Field origField = orig.getClass().getDeclaredField(field.getName());
            origField.setAccessible(true);
            origField.set(orig, value);
        }
    }
    public static void setNewValue(Object orig,Object dest) throws IllegalAccessException, NoSuchFieldException {
        Field[] descFields = dest.getClass().getDeclaredFields();
        for(Field field : descFields){
            field.setAccessible(true);
            Object value = field.get(dest);
            if(value == null){
                continue;
            }
            if(value instanceof String && StringUtils.isBlank((String)value)){
                continue;
            }
            Field origField = orig.getClass().getDeclaredField(field.getName());
            origField.setAccessible(true);
            origField.set(orig,value);
        }
    }
}
