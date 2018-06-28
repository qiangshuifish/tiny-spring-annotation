package xin.qiangshuidiyu.spring.aop;

import xin.qiangshuidiyu.spring.beans.factory.BeanFactory;

public interface BeanFactoryAware {

    void setBeanFactory(BeanFactory beanFactory) throws Exception;
}
