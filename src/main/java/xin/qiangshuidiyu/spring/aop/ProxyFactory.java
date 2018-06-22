package xin.qiangshuidiyu.spring.aop;

import xin.qiangshuidiyu.spring.aop.cglib.CglibAopProxy;

/**
 * @author wpy
 * @date 2018/6/21 10:46
 */
public class ProxyFactory extends AdvisedSupport implements AopProxy {
    @Override
    public Object getProxy() {
        return createCglibAopProxy().getProxy();
    }

    /**
     * 创建 cglid 的代理对象
     * @return
     */
    protected final AopProxy createCglibAopProxy(){
        return new CglibAopProxy(this);
    }

    /**
     * 创建 jdk 代理对象
     * @return
     */
    protected final AopProxy createJdkAopProxy(){
        return new JdkDynamicAopProxy(this);
    }
}
