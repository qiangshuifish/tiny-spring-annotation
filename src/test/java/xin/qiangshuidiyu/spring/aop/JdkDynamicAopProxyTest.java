package xin.qiangshuidiyu.spring.aop;

import org.junit.Test;
import xin.qiangshuidiyu.spring.context.AnnotationApplicationContext;
import xin.qiangshuidiyu.spring.context.ApplicationContext;
import xin.qiangshuidiyu.spring.test.app.HelloWorldService;

import java.util.Arrays;

public class JdkDynamicAopProxyTest {


    @Test
    public void test() throws Exception {

        // 获取对象
        ApplicationContext applicationContext = new AnnotationApplicationContext("xin");
        HelloWorldService helloWorldService = applicationContext.getBean(HelloWorldService.class);

        // 封装对象信息
        TargetSource targetSource = new TargetSource(helloWorldService,
                helloWorldService.getClass(),
                helloWorldService.getClass().getInterfaces());

        // 封装拦截操作需要的信息和拦截操作
        AdvisedSupport advisedSupport = new AdvisedSupport();
        advisedSupport.setTargetSource(targetSource);
        //拦截器
        advisedSupport.setMethodInterceptor(methodInvocation -> {
            System.out.println(String.format("开始执行%s",methodInvocation.getMethod().getName()));
            System.out.println("参数："+ Arrays.toString(methodInvocation.getArguments()));

            Object proceed;
            try {
                proceed = methodInvocation.proceed();
            }catch (Throwable throwable){
                System.out.println("执行"+methodInvocation.getMethod().getName()+"出错");
                throwable.printStackTrace();
                throw throwable;
            }

            System.out.println("执行"+methodInvocation.getMethod().getName()+"完毕");
            return proceed;
        });

        /// 获取代理对象
        JdkDynamicAopProxy proxyFactory = new JdkDynamicAopProxy(advisedSupport);
        HelloWorldService proxy = (HelloWorldService) proxyFactory.getProxy();

        proxy.output();
        proxy.helloWorld();


    }
}