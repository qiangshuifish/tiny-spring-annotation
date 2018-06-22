package xin.qiangshuidiyu.spring.aop.cglib;

import net.sf.cglib.proxy.Enhancer;
import xin.qiangshuidiyu.spring.aop.AbstractAopProxy;
import xin.qiangshuidiyu.spring.aop.AdvisedSupport;

/**
 * @author wpy
 * @date 2018/6/21 10:14
 */
public class CglibAopProxy extends AbstractAopProxy {

    public CglibAopProxy(AdvisedSupport advisedSupport) {
        super(advisedSupport);
    }

    @Override
    public Object getProxy() {

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(super.advisedSupport.getTargetSource().getTargetClass());
        enhancer.setInterfaces(super.advisedSupport.getTargetSource().getInterfaces());

        enhancer.setCallback(new DynamicAdvisedInterceptor(super.advisedSupport));
        return enhancer.create();
    }
}
