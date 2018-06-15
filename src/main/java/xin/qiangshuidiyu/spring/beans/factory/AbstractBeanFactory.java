package xin.qiangshuidiyu.spring.beans.factory;

import xin.qiangshuidiyu.spring.beans.BeanDefinition;
import xin.qiangshuidiyu.spring.beans.annotation.Scope;

import java.lang.reflect.Modifier;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

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
        // 如果是没有生成对象的，先创建对象
        if (Objects.isNull(bean)) {
            //创建对象
            bean = doCreateBean(beanDefinition);
            beanDefinition.setBean(bean);
        }else {
            recreateByScope(beanDefinition);
        }

        return (T) beanDefinition.getBean();
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getBean(Class<T> clazz) throws Exception {
        List<T> beans = getBeans(clazz);
        if(beans.size() == 1){
            return beans.get(0);
        }
        // 是否为借口或者抽象类
        if(clazz.isInterface() ||  Modifier.isAbstract(clazz.getModifiers())){
            assert beans.size() == 1 : "包含多个"+clazz.getSimpleName()+"类型: "+beans.toString();
        }
        List<T> collect = beans.stream()
                .filter(o -> o.getClass().equals(clazz))
                .collect(Collectors.toList());
        assert collect.size() == 1 : "包含多个"+clazz.getSimpleName()+"类型: "+beans.toString();
        return collect.get(0);
    }


    /**
     * 获取 clazz 或其子类所有的实例
     * @param clazz
     * @param <T>
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public <T> List<T> getBeans(Class<T> clazz)throws Exception{
        List<T> list = new ArrayList<>();
        // 查找所有扫描到的类中，是否有clazz或其子类
        Collection<BeanDefinition> values = beanDefinitionMap.values();
        for (BeanDefinition beanDefinition : values) {
            // 如果为clazz或者其子类
            if(clazz.isAssignableFrom(beanDefinition.getBeanClass())){
                //还没有创建对象就创建对象
                if(Objects.isNull(beanDefinition.getBean())){
                    T bean = (T) doCreateBean(beanDefinition);
                    beanDefinition.setBean(bean);
                }else{
                    recreateByScope(beanDefinition);
                }
                list.add((T) beanDefinition.getBean());
            }
        }
        assert !list.isEmpty() :"没有：" + clazz.getName()+"或其实例";
        return list;
    }

    /**
     * 根据 Scope 的值判断是否需要重新创建对象
     * @param beanDefinition
     * @throws Exception
     */
    private void recreateByScope(BeanDefinition beanDefinition) throws Exception {
        Scope scope = beanDefinition.getBeanClass().getAnnotation(Scope.class);
        if(Objects.nonNull(scope) && scope.value().equals(Scope.SCOPE_PROTOTYPE)){
            Object bean = doCreateBean(beanDefinition);
            beanDefinition.setBean(bean);
        }
    }

    /**
     * 工程创建对象实例
     * @param beanDefinition
     * @return
     * @throws Exception
     */
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
     * 预先加载所有的 bean
     * @throws Exception
     */
    public void preInstantiateSingletons() throws Exception {
        for (String definitionName : beanDefinitionNames) {
            getBean(definitionName);
        }
    }


    /**
     * 具体的赋值操作
     * @param bean
     * @param beanDefinition
     * @throws Exception
     */
    protected abstract void applyPropertyValues(Object bean,BeanDefinition beanDefinition) throws Exception;

}
