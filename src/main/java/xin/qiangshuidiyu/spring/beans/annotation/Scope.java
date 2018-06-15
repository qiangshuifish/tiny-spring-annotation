package xin.qiangshuidiyu.spring.beans.annotation;

import java.lang.annotation.*;

/**
 * 配置bean创建是单例还是多例
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Scope {

    String value() default SCOPE_SINGLETON;

    /**
     * 单例
     */
    String SCOPE_SINGLETON = "singleton";

    /**
     * 多例
     */
    String SCOPE_PROTOTYPE = "prototype";
}
