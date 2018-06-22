package xin.qiangshuidiyu.spring.test.app.impl;

import xin.qiangshuidiyu.spring.aop.BeanPostProcessor;
import xin.qiangshuidiyu.spring.beans.annotation.Component;

/**
 * @author wpy
 * @date 2018/6/22 11:58
 */
@Component
public class BeanInitLog implements BeanPostProcessor {
    @Override
    public Object postProcessorBeforInittialization(Object bean, String beanName) throws Exception {
        System.out.println("前置处理器 bean "+beanName);
        return bean;
    }

    @Override
    public Object postProcessorAfterInittialization(Object bean, String beanName) throws Exception {
        System.out.println("后置处理器 bean "+beanName);
        return null;
    }
}
