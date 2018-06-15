package xin.qiangshuidiyu.spring.aop.aspectj;

/**
 * 类匹配器
 */
public interface ClassFilter {

    /**
     * targetClass 时候需要拦截
     * @param targetClass
     * @return
     */
    boolean matches(Class<?> targetClass);
}
