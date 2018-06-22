package xin.qiangshuidiyu.spring.context;

import org.apache.commons.lang3.StringUtils;
import xin.qiangshuidiyu.spring.beans.BeanDefinition;
import xin.qiangshuidiyu.spring.beans.annotation.AnnotationBeanDefinitionReader;
import xin.qiangshuidiyu.spring.beans.factory.AbstractBeanFactory;
import xin.qiangshuidiyu.spring.beans.factory.AnnotationBeanFactory;
import xin.qiangshuidiyu.spring.beans.io.ResourceLoader;

import java.util.Map;

/**
 * @author wpy
 * @date 2018/6/14 15:58
 */
public class AnnotationApplicationContext extends AbstractApplicationContext {

    private String packageName;
    private String propertiesName;

    public AnnotationApplicationContext(AbstractBeanFactory beanFactory, String packageName,String propertiesName) throws Exception {
        super(beanFactory);
        this.packageName = packageName;
        this.propertiesName = StringUtils.isEmpty(propertiesName)? "application.properties":propertiesName;
        this.refresh();
    }

    public AnnotationApplicationContext(String packageName) throws Exception {
        this(new AnnotationBeanFactory(),packageName,null);
    }

    public AnnotationApplicationContext(String packageName,String propertiesName) throws Exception {
        this(new AnnotationBeanFactory(),packageName,null);
    }

    @Override
    protected void loadBeanDefinitions(AbstractBeanFactory beanFactory) throws Exception {
        // 调用 AnnotationBeanDefinitionReader 去加载包
        AnnotationBeanDefinitionReader reader = new AnnotationBeanDefinitionReader(new ResourceLoader());
        reader.loadPropertiesReader(this.propertiesName);
        reader.loadDefinitionReader(this.packageName);

        // 加载完毕之后 把信息从AnnotationBeanDefinitionReader 复制到工程里头去生成bean
        for (Map.Entry<String, BeanDefinition> beanDefinitionEntry : reader.getRegistry().entrySet()) {
            beanFactory.registerBeanBeanDefinition(beanDefinitionEntry.getKey(),beanDefinitionEntry.getValue());
        }
    }
}
