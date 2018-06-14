package xin.qiangshuidiyu.spring.beans;

import xin.qiangshuidiyu.spring.beans.io.ResourceLoader;

import java.util.HashMap;
import java.util.Map;

/**
 * BeanDefinitionReader 接口实现抽象类
 * @author wpy
 * @date 2018/6/14 10:31
 */
public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader{

    /**
     * String-beanDefinition 映射关系
     */
    private Map<String,BeanDefinition> registry;

    /**
     * 资源加载器
     */
    private ResourceLoader resourceLoader;

    public AbstractBeanDefinitionReader(ResourceLoader resourceLoader) {
        registry = new HashMap<>();
        this.resourceLoader = resourceLoader;
    }

    public Map<String, BeanDefinition> getRegistry() {
        return registry;
    }

    public ResourceLoader getResourceLoader() {
        return resourceLoader;
    }
}
