package xin.qiangshuidiyu.spring.context;

import xin.qiangshuidiyu.spring.aop.BeanPostProcessor;
import xin.qiangshuidiyu.spring.beans.factory.AbstractBeanFactory;

import java.util.List;

/**
 * @author wpy
 * @date 2018/6/14 15:39
 */
public abstract class AbstractApplicationContext implements ApplicationContext {


    private AbstractBeanFactory beanFactory;

    public AbstractApplicationContext(AbstractBeanFactory beanFactory) {
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
        // 加载到所有bean
        loadBeanDefinitions(this.beanFactory);
        //优先初始化 所有的 bean 前后置处理器
        registerBeanPostProcessors(this.beanFactory);
        // 初始化所有对象
        beanFactory.preInstantiateSingletons();
    }

    protected void registerBeanPostProcessors(AbstractBeanFactory beanFactory) throws Exception {
        List<BeanPostProcessor> beans = beanFactory.getBeans(BeanPostProcessor.class);
        beans.forEach(beanPostProcessor -> beanFactory.addBeanPostProcessor(beanPostProcessor));
    }

    /**
     * 加载 beanDefinitions
     * @param beanFactory
     * @throws Exception
     */
    protected abstract void loadBeanDefinitions(AbstractBeanFactory beanFactory) throws Exception;


}
