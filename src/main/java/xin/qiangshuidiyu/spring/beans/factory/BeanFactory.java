package xin.qiangshuidiyu.spring.beans.factory;

/**
 * 如何装配、获取 Bean 实例的问题
 */
public interface BeanFactory {

    /**
     * 通过类名来获取 bean 实例
     * @param name
     * @param <T>
     * @return
     * @throws Exception
     */
    <T> T getBean(String name) throws Exception;

    /**
     * 通过 Class 来获取 bean 实例
     * @param clazz
     * @param <T>
     * @return
     * @throws Exception
     */
    <T> T getBean(Class<T> clazz) throws Exception;
}
