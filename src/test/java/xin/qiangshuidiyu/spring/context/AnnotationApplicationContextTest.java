package xin.qiangshuidiyu.spring.context;

import org.junit.Test;
import xin.qiangshuidiyu.spring.test.app.HelloWorldService;
import xin.qiangshuidiyu.spring.test.app.OutputService;
import xin.qiangshuidiyu.spring.test.app.impl.HelloWorldServiceImpl;
import xin.qiangshuidiyu.spring.test.app.impl.OutputServiceImpl;

public class AnnotationApplicationContextTest {

    @Test
    public void test() throws Exception {
        AbstractApplicationContext applicationContext = new AnnotationApplicationContext("xin");

        HelloWorldServiceImpl helloWorldServiceImpl = applicationContext.getBean("helloWorldServiceImpl");
        OutputServiceImpl outputServiceImpl = applicationContext.getBean("outputServiceImpl");

        HelloWorldServiceImpl helloWorldServiceImpl1 = applicationContext.getBean(HelloWorldServiceImpl.class);
        OutputServiceImpl outputServiceImpl1 = applicationContext.getBean(OutputServiceImpl.class);

        helloWorldServiceImpl.helloWorld();
        outputServiceImpl.output();
        helloWorldServiceImpl.output();

        helloWorldServiceImpl1.helloWorld();
        helloWorldServiceImpl.output();
        outputServiceImpl1.output();

    }

    @Test
    public void testScope() throws Exception {
        AbstractApplicationContext applicationContext = new AnnotationApplicationContext("xin");

        HelloWorldService helloWorldService1 = applicationContext.getBean(HelloWorldServiceImpl.class);
        HelloWorldService helloWorldService2 = applicationContext.getBean(HelloWorldServiceImpl.class);
        System.out.println(helloWorldService1 == helloWorldService2);


        OutputService outputService1 = applicationContext.getBean(OutputServiceImpl.class);
        OutputService outputService2 = applicationContext.getBean(OutputServiceImpl.class);
        System.out.println(outputService1 == outputService2);
    }
}