package xin.qiangshuidiyu.spring.aop.cglib;

import org.junit.Test;
import xin.qiangshuidiyu.spring.aop.AdvisedSupport;
import xin.qiangshuidiyu.spring.aop.TargetSource;
import xin.qiangshuidiyu.spring.context.AnnotationApplicationContext;
import xin.qiangshuidiyu.spring.context.ApplicationContext;
import xin.qiangshuidiyu.spring.test.app.HelloWorldService;
import xin.qiangshuidiyu.spring.test.app.impl.HelloWorldServiceImpl;

public class CglibAopProxyTest {

    @Test
    public void test() throws Exception {
        ApplicationContext applicationContext = new AnnotationApplicationContext("xin");
        HelloWorldService helloWorldService = applicationContext.getBean(HelloWorldService.class);

        AdvisedSupport advisedSupport = new AdvisedSupport();

        advisedSupport.setTargetSource(
                new TargetSource(helloWorldService,HelloWorldServiceImpl.class,HelloWorldServiceImpl.class.getInterfaces()));
        advisedSupport.setMethodInterceptor(methodInvocation -> {
            System.out.println(String.format("开始执行%s",methodInvocation.getMethod().getName()));
            Object proceed = methodInvocation.proceed();
            System.out.println("执行"+methodInvocation.getMethod().getName()+"完毕");
            return proceed;
        });


        CglibAopProxy cglibAopProxy = new CglibAopProxy(advisedSupport);
        HelloWorldService proxy = (HelloWorldService) cglibAopProxy.getProxy();

        proxy.helloWorld();

    }
}