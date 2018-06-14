package xin.qiangshuidiyu.spring.context;

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
public class AnnotationApplicationContext extends AbstracApplicationContext {

    private String packageName;

    public AnnotationApplicationContext(AbstractBeanFactory beanFactory, String packageName) throws Exception {
        super(beanFactory);
        this.packageName = packageName;
        this.refresh();
    }

    public AnnotationApplicationContext(String packageName) throws Exception {
        this(new AnnotationBeanFactory(),packageName);
        this.packageName = packageName;
    }

    @Override
    protected void loadBeanDefinitions(AbstractBeanFactory beanFactory) throws Exception {
        AnnotationBeanDefinitionReader reader = new AnnotationBeanDefinitionReader(new ResourceLoader());

        reader.loadDefinitionReader(this.packageName);

        for (Map.Entry<String, BeanDefinition> beanDefinitionEntry : reader.getRegistry().entrySet()) {
            beanFactory.registerBeanDefintion(beanDefinitionEntry.getKey(),beanDefinitionEntry.getValue());
        }
    }
}
