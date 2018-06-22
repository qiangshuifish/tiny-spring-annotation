package xin.qiangshuidiyu.spring.beans;

import xin.qiangshuidiyu.spring.beans.BeanReference;

import java.lang.reflect.Method;

/**
 * 使用指定方法创建bean时的方法应用抽象
 * @author wpy
 * @date 2018/6/19 10:29
 */
public class MethodReference {
    private BeanReference beanReference;
    private Method method;

    public MethodReference(BeanReference beanReference, Method method) {
        this.beanReference = beanReference;
        this.method = method;
    }

    public BeanReference getBeanReference() {
        return beanReference;
    }

    public void setBeanReference(BeanReference beanReference) {
        this.beanReference = beanReference;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }
}
