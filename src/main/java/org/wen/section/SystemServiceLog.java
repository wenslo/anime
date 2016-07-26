package org.wen.section;

import java.lang.annotation.*;

/**
 * 自定义注解，拦截Service
 */
@Target({ElementType.PARAMETER,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SystemServiceLog {
    String description() default "";
}
