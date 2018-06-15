package xin.qiangshuidiyu.spring.aop;

import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Method;

/**
 * @author wpy
 * @date 2018/6/15 10:09
 */
public class ReflectiveMethodInvocation implements MethodInvocation {

    /**
     * 需要代理的方法
     */
    private Method method;

    /**
     * 被代理的对象（原对象）
     */
    private Object object;

    /**
     * 方法参数
     */
    private Object[] arguments;


    public ReflectiveMethodInvocation(Method method, Object object, Object[] arguments) {
        this.method = method;
        this.object = object;
        this.arguments = arguments;
    }

    @Override
    public Method getMethod() {
        return this.method;
    }

    @Override
    public Object[] getArguments() {
        return this.arguments;
    }

    @Override
    public Object proceed() throws Throwable {
        return method.invoke(object,arguments);
    }

    @Override
    public Object getThis() {
        return this.object;
    }

    @Override
    public AccessibleObject getStaticPart() {
        return this.method;
    }
}
