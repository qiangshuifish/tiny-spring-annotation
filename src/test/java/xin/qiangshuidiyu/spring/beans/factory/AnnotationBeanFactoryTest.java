package xin.qiangshuidiyu.spring.beans.factory;

import org.junit.Test;
import xin.qiangshuidiyu.spring.beans.BeanDefinition;
import xin.qiangshuidiyu.spring.beans.annotation.AnnotationBeanDefinitionReader;
import xin.qiangshuidiyu.spring.beans.io.ResourceLoader;
import xin.qiangshuidiyu.spring.test.app.HelloWorldService;
import xin.qiangshuidiyu.spring.test.app.OutputService;
import xin.qiangshuidiyu.spring.test.app.impl.HelloWorldServiceImpl;
import xin.qiangshuidiyu.spring.test.app.impl.OutputServiceImpl;

import java.util.Map;

public class AnnotationBeanFactoryTest {

    @Test
    public void test() throws Exception {

        AnnotationBeanFactory beanFactory = new AnnotationBeanFactory();
        AnnotationBeanDefinitionReader reader = new AnnotationBeanDefinitionReader(new ResourceLoader());

        reader.loadDefinitionReader("xin");

        for (Map.Entry<String, BeanDefinition> beanDefinitionEntry : reader.getRegistry().entrySet()) {
            beanFactory.registerBeanDefintion(beanDefinitionEntry.getKey(),beanDefinitionEntry.getValue());
        }




        System.out.println("================按类型===============");
        HelloWorldService helloWorldServiceImpl = beanFactory.getBean(HelloWorldService.class);
        OutputService outputServiceImpl = beanFactory.getBean(OutputService.class);

        helloWorldServiceImpl.helloWorld();
        helloWorldServiceImpl.output();
        outputServiceImpl.output();

        System.out.println("================按名字================");
        HelloWorldServiceImpl helloWorldServiceImpl1 = beanFactory.getBean("helloWorldServiceImpl");
        OutputServiceImpl outputServiceImpl1 = beanFactory.getBean("outputServiceImpl");
        helloWorldServiceImpl1.helloWorld();
        helloWorldServiceImpl1.output();
        outputServiceImpl1.output();


    }

}