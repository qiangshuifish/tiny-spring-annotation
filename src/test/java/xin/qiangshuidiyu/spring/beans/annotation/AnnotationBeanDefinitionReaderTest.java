package xin.qiangshuidiyu.spring.beans.annotation;

import org.junit.Test;
import xin.qiangshuidiyu.spring.test.app.HelloWorldService;
import xin.qiangshuidiyu.spring.test.app.OutputService;
import xin.qiangshuidiyu.spring.beans.io.ResourceLoader;

public class AnnotationBeanDefinitionReaderTest {

    @Test
    public void loadDefinitionReader() throws Exception {
        AnnotationBeanDefinitionReader reader = new AnnotationBeanDefinitionReader(new ResourceLoader());
        reader.loadDefinitionReader("xin");

        System.out.println(reader.getRegistry());
        HelloWorldService helloWorldService = (HelloWorldService) reader.getRegistry().get("helloWorldService").getBean();
        OutputService outputService = (OutputService) reader.getRegistry().get("outputService").getBean();

        // 应该为 null
        System.out.println(helloWorldService);
        System.out.println(outputService);
    }
}