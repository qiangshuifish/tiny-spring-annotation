package xin.qiangshuidiyu.spring.aop.cglib;

import net.sf.cglib.proxy.MethodProxy;
import xin.qiangshuidiyu.spring.aop.ReflectiveMethodInvocation;

import java.lang.reflect.Method;

/**
 * @author wpy
 * @date 2018/6/21 10:22
 */
public class CglibMethodInvocation extends ReflectiveMethodInvocation {

    private final MethodProxy methodProxy;

    public CglibMethodInvocation(Method method, Object target, Object[] arguments, MethodProxy methodProxy) {
        super(method, target, arguments);
        this.methodProxy = methodProxy;
    }

    @Override
    public Object proceed() throws Throwable {
        return this.methodProxy.invoke(this.getThis(),this.getArguments());
    }
}
