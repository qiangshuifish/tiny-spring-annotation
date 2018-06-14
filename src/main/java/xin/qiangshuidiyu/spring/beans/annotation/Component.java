package xin.qiangshuidiyu.spring.beans.annotation;

import java.lang.annotation.*;

/**
 * 依赖注入组件标识
 * @author wpy
 * @date 2018/6/13 15:35
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Component {
    String value() default "";
}
