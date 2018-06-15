package xin.qiangshuidiyu.spring.aop.aspectj;

import java.lang.reflect.Method;

/**
 * 方法匹配器
 */
public interface MethodMatcher {

    /**
     * 匹配方法时候需要拦截
     * @param method
     * @param targetClass
     * @return
     */
    boolean matches(Method method,Class<?> targetClass);
}
