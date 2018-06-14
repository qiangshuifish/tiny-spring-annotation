package xin.qiangshuidiyu.spring.context;

import org.junit.Test;
import xin.qiangshuidiyu.spring.test.app.HelloWorldService;
import xin.qiangshuidiyu.spring.test.app.OutputService;

public class AnnotationApplicationContextTest {

    @Test
    public void test() throws Exception {
        AbstractApplicationContext applicationContext = new AnnotationApplicationContext("xin");

        HelloWorldService helloWorldService = applicationContext.getBean("helloWorldService");
        OutputService outputService = applicationContext.getBean("outputService");

        HelloWorldService helloWorldService1 = applicationContext.getBean(HelloWorldService.class);
        OutputService outputService1 = applicationContext.getBean(OutputService.class);

        helloWorldService.helloWorld();
        outputService.output();
        helloWorldService.output();

        helloWorldService1.helloWorld();
        helloWorldService.output();
        outputService1.output();

    }
}