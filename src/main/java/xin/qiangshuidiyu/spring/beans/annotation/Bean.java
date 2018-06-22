package xin.qiangshuidiyu.spring.beans.annotation;

import java.lang.annotation.*;

/**
 * 实现自定义注入
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Bean {

    String value() default "";
}
