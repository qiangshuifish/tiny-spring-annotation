package xin.qiangshuidiyu.spring.aop;

import org.aopalliance.intercept.MethodInterceptor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Jdk代理模式的代理工厂
 * @author wpy
 * @date 2018/6/15 10:45
 */
public class JdkDynamicAopProxy extends AbstractAopProxy implements InvocationHandler {

    public JdkDynamicAopProxy(AdvisedSupport advisedSupport) {
        super(advisedSupport);
    }


    /**
     * 代理操作交给 advisedSupport中的 MethodInterceptor 执行
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 获取需要进行的代理操作
        MethodInterceptor methodInterceptor = this.advisedSupport.getMethodInterceptor();
        return methodInterceptor.invoke(
                new ReflectiveMethodInvocation(method,this.advisedSupport.getTargetSource().getTarget(), args)
        );
    }

    @Override
    public Object getProxy() {
        return Proxy.newProxyInstance(getClass().getClassLoader(),
                this.advisedSupport.getTargetSource().getInterfaces(), this);
    }
}
