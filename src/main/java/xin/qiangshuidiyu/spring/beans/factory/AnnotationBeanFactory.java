package xin.qiangshuidiyu.spring.beans.factory;

import xin.qiangshuidiyu.spring.BeanReference;
import xin.qiangshuidiyu.spring.beans.BeanDefinition;
import xin.qiangshuidiyu.spring.beans.PropertyValue;
import xin.qiangshuidiyu.spring.beans.annotation.Value;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Objects;

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
            Field field = bean.getClass().getDeclaredField(propertyValue.getName());
            // 依赖注入
            if(value instanceof BeanReference){
                BeanReference beanReference = (BeanReference) value;
                // 首先尝试通过名称获取,在通过类型获取
                value = getBean(beanReference.getName());
                if(Objects.isNull(value)){
                    value = getBean(field.getType());
                }
            }
            field.setAccessible(true);
            field.set(bean,value);
        }
    }
}
