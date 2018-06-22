package xin.qiangshuidiyu.spring.test.app.impl;

import xin.qiangshuidiyu.spring.beans.annotation.Bean;
import xin.qiangshuidiyu.spring.beans.annotation.Component;
import xin.qiangshuidiyu.spring.beans.annotation.Scope;
import xin.qiangshuidiyu.spring.beans.annotation.Value;
import xin.qiangshuidiyu.spring.test.app.HelloWorldService;

import javax.annotation.Resource;

/**
 * @author wpy
 * @date 2018/6/13 15:33
 */
@Component
public class HelloWorldServiceImpl implements HelloWorldService {

    @Value("${hello-world}")
    private String text;

/*    @Bean
    public HelloWorld getHelloWorld(){
        HelloWorld helloWorld = new HelloWorld(text);
        return helloWorld;
    }*/

    @Resource
    private OutputServiceImpl outputServiceImpl;

    @Override
    public void helloWorld(){
        System.out.println(text);
    }

    @Override
    public void output(){
        outputServiceImpl.output();
    }
}
