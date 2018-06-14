package xin.qiangshuidiyu.spring.beans.annotation;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Value {
    /**
     * 属性名
     * @return
     */
    String value() default "";
}
