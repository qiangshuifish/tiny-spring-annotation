package xin.qiangshuidiyu.spring.context;

import org.junit.Test;
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
}