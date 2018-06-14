package xin.qiangshuidiyu.spring.beans;

/**
 * 读取 Resource 到 BeanDefinition 的接口
 * @author wpy
 * @date 2018/6/14 10:28
 */
public interface BeanDefinitionReader {

    /**
     * 根据包名加载 Definition
     * @param packageName
     * @throws Exception
     */
   void loadDefinitionReader(String packageName) throws Exception;
}
