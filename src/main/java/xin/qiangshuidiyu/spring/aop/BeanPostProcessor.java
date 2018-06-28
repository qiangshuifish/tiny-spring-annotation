package xin.qiangshuidiyu.spring.aop;


/**
 * bean 初始化时前置后置处理
 * 比如在 IOC 容器中植入 AOP 操作
 */
public interface BeanPostProcessor {

    /**
     * 初始化 bean 的前置处理
     * @param bean
     * @param beanName
     * @return
     * @throws Exception
     */
    Object postProcessorBeforeInitialization(Object bean, String beanName) throws Exception;

    /**
     * 初始化 bean 的后置处理
     * @param bean
     * @param beanName
     * @return
     * @throws Exception
     */
    Object postProcessorAfterInitialization(Object bean, String beanName) throws Exception;
}
