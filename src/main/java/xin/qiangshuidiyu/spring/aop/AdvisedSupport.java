package xin.qiangshuidiyu.spring.aop;

import org.aopalliance.intercept.MethodInterceptor;
import xin.qiangshuidiyu.spring.aop.aspectj.MethodMatcher;

/**
 * @author wpy
 * @date 2018/6/15 10:34
 */
public class AdvisedSupport {

    /**
     * 被代理对象的描述
     */
    private TargetSource targetSource;

    /**
     * 方法拦截器，拦截方法进行操作
     */
    private MethodInterceptor methodInterceptor;

    /**
     * 方法匹配器
     */
    private MethodMatcher methodMatcher;

    public TargetSource getTargetSource() {
        return targetSource;
    }

    public void setTargetSource(TargetSource targetSource) {
        this.targetSource = targetSource;
    }

    public MethodInterceptor getMethodInterceptor() {
        return methodInterceptor;
    }

    public void setMethodInterceptor(MethodInterceptor methodInterceptor) {
        this.methodInterceptor = methodInterceptor;
    }

    public MethodMatcher getMethodMatcher() {
        return methodMatcher;
    }

    public void setMethodMatcher(MethodMatcher methodMatcher) {
        this.methodMatcher = methodMatcher;
    }
}
