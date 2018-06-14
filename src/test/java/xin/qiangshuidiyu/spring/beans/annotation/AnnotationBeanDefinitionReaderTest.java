package xin.qiangshuidiyu.spring.beans.annotation;

import org.junit.Test;
import xin.qiangshuidiyu.spring.test.app.impl.HelloWorldServiceImpl;
import xin.qiangshuidiyu.spring.test.app.impl.OutputServiceImpl;
import xin.qiangshuidiyu.spring.beans.io.ResourceLoader;

public class AnnotationBeanDefinitionReaderTest {

    @Test
    public void loadDefinitionReader() throws Exception {
        AnnotationBeanDefinitionReader reader = new AnnotationBeanDefinitionReader(new ResourceLoader());
        reader.loadDefinitionReader("xin");

        System.out.println(reader.getRegistry());
        HelloWorldServiceImpl helloWorldServiceImpl = (HelloWorldServiceImpl) reader.getRegistry().get("helloWorldServiceImpl").getBean();
        OutputServiceImpl outputServiceImpl = (OutputServiceImpl) reader.getRegistry().get("outputServiceImpl").getBean();

        // 应该为 null
        System.out.println(helloWorldServiceImpl);
        System.out.println(outputServiceImpl);
    }
}