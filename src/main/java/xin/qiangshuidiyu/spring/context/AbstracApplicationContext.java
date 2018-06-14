package xin.qiangshuidiyu.spring.context;

import xin.qiangshuidiyu.spring.beans.factory.AbstractBeanFactory;

/**
 * @author wpy
 * @date 2018/6/14 15:39
 */
public abstract class AbstracApplicationContext implements ApplicationContext {


    private AbstractBeanFactory beanFactory;

    public AbstracApplicationContext(AbstractBeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    @Override
    public <T> T getBean(String name) throws Exception {
        return this.beanFactory.getBean(name);
    }

    @Override
    public <T> T getBean(Class<T> clazz) throws Exception {
        return this.beanFactory.getBean(clazz);
    }


    public void refresh() throws Exception{
        loadBeanDefinitions(beanFactory);
    }

    protected abstract void loadBeanDefinitions(AbstractBeanFactory beanFactory) throws Exception;


}
