package xin.qiangshuidiyu.spring.beans.factory;

import xin.qiangshuidiyu.spring.beans.BeanReference;
import xin.qiangshuidiyu.spring.beans.BeanDefinition;
import xin.qiangshuidiyu.spring.beans.MethodReference;
import xin.qiangshuidiyu.spring.beans.PropertyValue;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
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
                value = getBeanReferenceObject(value, field.getType());
            }
            // 初始化时会有问题，暂时未找到更好的办法
            if(value instanceof MethodReference){
//                value = getMethodReferenceObject(value);
            }
            field.setAccessible(true);
            field.set(bean,value);
        }
    }

    private Object getMethodReferenceObject(Object value) throws Exception {
        MethodReference methodReference = (MethodReference) value;
        // 获取方法所属的对象
        BeanReference beanReference = methodReference.getBeanReference();
        Object target = getBeanReferenceObject(beanReference,methodReference.getMethod().getReturnType());
        // 获取方法需要的参数
        Class<?>[] parameterTypes = methodReference.getMethod().getParameterTypes();
        if(parameterTypes.length> 0){
            Object[] args = new Object[parameterTypes.length];
            for (int i = 0; i < parameterTypes.length; i++) {
                args[i] = getBean(parameterTypes[i]);
            }
            value = methodReference.getMethod().invoke(target,args);
        }else {
            value = methodReference.getMethod().invoke(target);
        }
        return value;
    }

    private Object getBeanReferenceObject(Object value, Class<?> ObjectType) throws Exception {
        BeanReference beanReference = (BeanReference) value;
        // 首先尝试通过名称获取,在通过类型获取
        value = getBean(beanReference.getName());
        if(Objects.isNull(value)){
            value = getBean(ObjectType);
        }
        return value;
    }
}
