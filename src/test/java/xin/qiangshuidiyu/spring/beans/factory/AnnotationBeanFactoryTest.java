package xin.qiangshuidiyu.spring.beans.factory;

import org.junit.Test;
import xin.qiangshuidiyu.spring.beans.BeanDefinition;
import xin.qiangshuidiyu.spring.beans.annotation.AnnotationBeanDefinitionReader;
import xin.qiangshuidiyu.spring.beans.io.ResourceLoader;
import xin.qiangshuidiyu.spring.test.app.HelloWorldService;
import xin.qiangshuidiyu.spring.test.app.OutputService;

import java.util.Map;

import static org.junit.Assert.*;

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
        HelloWorldService helloWorldService = beanFactory.getBean(HelloWorldService.class);
        OutputService outputService = beanFactory.getBean(OutputService.class);

        helloWorldService.helloWorld();
        helloWorldService.output();
        outputService.output();

        System.out.println("================按名字================");
        HelloWorldService helloWorldService1 = beanFactory.getBean("helloWorldService");
        OutputService outputService1 = beanFactory.getBean("outputService");
        helloWorldService1.helloWorld();
        helloWorldService1.output();
        outputService1.output();


    }

}