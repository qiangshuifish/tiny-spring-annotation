package xin.qiangshuidiyu.spring.beans.factory;

import xin.qiangshuidiyu.spring.BeanReference;
import xin.qiangshuidiyu.spring.beans.BeanDefinition;
import xin.qiangshuidiyu.spring.beans.PropertyValue;

import java.lang.reflect.Field;
import java.util.List;

/**
 * @author wpy
 * @date 2018/6/14 14:19
 */
public class AnnotationBeanFactory extends AbstractBeanFactory {
    @Override
    protected void applyPropertyValues(Object bean, BeanDefinition beanDefinition) throws Exception {
        List<PropertyValue> propertyValues = beanDefinition.getPropertyValues().getList();

        for (PropertyValue propertyValue : propertyValues) {
            Object value = propertyValue.getValue();

            if(value instanceof BeanReference){
                BeanReference beanReference = (BeanReference) value;

                value = getBean(beanReference.getName());

                Field field = bean.getClass().getDeclaredField(propertyValue.getName());
                field.setAccessible(true);
                field.set(bean,value);
            }
        }
    }
}
