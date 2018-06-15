package xin.qiangshuidiyu.spring.aop.aspectj;

/**
 * 切点
 * @author wpy
 * @date 2018/6/15 13:28
 */
public interface Pointcut {

    /**
     * 获取class筛选器
     * @return
     */
    ClassFilter getClassFilter();

    /**
     * 获取方法筛选器
     * @return
     */
    MethodMatcher getMethodMatcher();

}
