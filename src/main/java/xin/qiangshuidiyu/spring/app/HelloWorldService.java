package xin.qiangshuidiyu.spring.app;

import xin.qiangshuidiyu.spring.beans.annotation.Component;

import javax.annotation.Resource;

/**
 * @author wpy
 * @date 2018/6/13 15:33
 */
@Component
public class HelloWorldService {

    @Resource
    private OutputService outputService;

    public void helloWorld(){
        System.out.println("hello World");
    }
}
