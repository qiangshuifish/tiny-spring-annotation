package xin.qiangshuidiyu.spring.beans.factory;

import xin.qiangshuidiyu.spring.beans.BeanDefinition;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author wpy
 * @date 2018/6/13 15:02
 */
public abstract class AbstractBeanFactory implements BeanFactory {

    private Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    private final List<String> beanDefinitionNames = new ArrayList<>();


    @SuppressWarnings("unchecked")
    @Override
    public <T> T getBean(String name) throws Exception {
        BeanDefinition beanDefinition = beanDefinitionMap.get(name);
        assert Objects.nonNull(beanDefinition) : "没有：" + name;

        Object bean = beanDefinition.getBean();
        if (Objects.isNull(bean)) {
            bean = doCreateBean(beanDefinition);
            beanDefinition.setBean(bean);
        }
        return (T) bean;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getBean(Class<T> clazz) throws Exception {
        T t = null;
        Collection<BeanDefinition> values = beanDefinitionMap.values();
        for (BeanDefinition beanDefinition : values) {
            if(clazz.isAssignableFrom(beanDefinition.getBeanClass())){
                if(Objects.isNull(beanDefinition.getBean())){
                    Object bean = doCreateBean(beanDefinition);
                    beanDefinition.setBean(bean);
                }
                t = (T) beanDefinition.getBean();
            }
        }
        assert Objects.nonNull(t) :"没有：" + clazz.getName();
        return t;
    }

    private Object doCreateBean(BeanDefinition beanDefinition) throws Exception {
        Object bean = createBeanInstance(beanDefinition);
        beanDefinition.setBean(bean);
        // 一些需要赋值的操作，交给具体的子类去实现
        applyPropertyValues(bean,beanDefinition);
        return bean;
    }

    private Object createBeanInstance(BeanDefinition beanDefinition) throws Exception {
        return beanDefinition.getBeanClass().newInstance();
    }

    /**
     * 向工厂 beanDefinitionMap 注册bean
     * @param name
     * @param beanDefinition
     */
    public void registerBeanDefintion(String name, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(name, beanDefinition);
        beanDefinitionNames.add(name);
    }


    /**
     * 具体的赋值操作
     * @param bean
     * @param beanDefinition
     * @throws Exception
     */
    protected abstract void applyPropertyValues(Object bean,BeanDefinition beanDefinition) throws Exception;

}
