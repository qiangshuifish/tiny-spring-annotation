package xin.qiangshuidiyu.spring.aop;

/**
 * 封装被代理的对象的相关信息
 * @author wpy
 * @date 2018/6/15 10:35
 */
public class TargetSource {

    /**
     * 需要被代理的对象（原对象）
     */
    private Object target;
    /**
     * 原对象的class
     */
    private Class<?> targetClass;
    /**
     * 需要被代理的方法对应的接口
     */
    private Class<?>[] interfaces;

    public TargetSource(Object target, Class<?> targetClass, Class<?>[] interfaces) {
        this.target = target;
        this.targetClass = targetClass;
        this.interfaces = interfaces;
    }

    public Object getTarget() {
        return target;
    }

    public Class<?> getTargetClass() {
        return targetClass;
    }

    public Class<?>[] getInterfaces() {
        return interfaces;
    }
}
