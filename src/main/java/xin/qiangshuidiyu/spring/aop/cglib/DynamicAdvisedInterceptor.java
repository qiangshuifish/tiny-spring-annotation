package xin.qiangshuidiyu.spring.aop.cglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import xin.qiangshuidiyu.spring.aop.AdvisedSupport;

import java.lang.reflect.Method;

/**
 * @author wpy
 * @date 2018/6/21 10:17
 */
public class DynamicAdvisedInterceptor implements MethodInterceptor {

    /**
     * 被代理对象相关信息
     */
    private AdvisedSupport advisedSupport;

    /**
     * 用户写的方法拦截器 advisedSupport中的MethodInterceptor
     */
    private org.aopalliance.intercept.MethodInterceptor delegateMethodInterceptor;

    public DynamicAdvisedInterceptor(AdvisedSupport advisedSupport) {
        this.advisedSupport = advisedSupport;
        this.delegateMethodInterceptor = advisedSupport.getMethodInterceptor();
    }




    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        Object invoke = delegateMethodInterceptor.invoke(
                new CglibMethodInvocation(method, advisedSupport.getTargetSource().getTarget(), args, methodProxy));
        return invoke;
    }
}
