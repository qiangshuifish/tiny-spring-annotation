package xin.qiangshuidiyu.spring.beans;

import xin.qiangshuidiyu.spring.beans.io.ResourceLoader;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * BeanDefinitionReader 接口实现抽象类
 * @author wpy
 * @date 2018/6/14 10:31
 */
public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader,PropertiesReader{

    /**
     * String-beanDefinition 映射关系
     */
    private Map<String,BeanDefinition> registry;

    /**
     * 存储一些配置文件的键值对信息
     */
    private Properties properties;

    /**
     * 资源加载器
     */
    private ResourceLoader resourceLoader;

    public AbstractBeanDefinitionReader(ResourceLoader resourceLoader) {
        registry = new HashMap<>();
        this.resourceLoader = resourceLoader;
        this.properties = new Properties();
    }

    public Map<String, BeanDefinition> getRegistry() {
        return registry;
    }

    public Properties getProperties() {
        return properties;
    }

    public ResourceLoader getResourceLoader() {
        return resourceLoader;
    }
}
