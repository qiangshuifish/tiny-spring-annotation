package xin.qiangshuidiyu.spring.beans;


/**
 * Bean 定义，描述一个Java bean
 * @author wpy
 * @date 2018/6/13 14:45
 */
public class BeanDefinition {
    private Class<?> beanClass;
    private Object bean;
    private String className;
    private PropertyValues propertyValues = new PropertyValues();

    public Class<?> getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class<?> beanClass) {
        this.beanClass = beanClass;
    }

    public Object getBean() {
        return bean;
    }

    public void setBean(Object bean) {
        this.bean = bean;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public PropertyValues getPropertyValues() {
        return propertyValues;
    }

    public void setPropertyValues(PropertyValues propertyValues) {
        this.propertyValues = propertyValues;
    }
}
