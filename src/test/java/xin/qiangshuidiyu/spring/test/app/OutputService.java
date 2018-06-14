package xin.qiangshuidiyu.spring.test.app;

import xin.qiangshuidiyu.spring.beans.annotation.Component;

import javax.annotation.Resource;

/**
 * @author wpy
 * @date 2018/6/13 16:16
 */
@Component
public class OutputService {

    @Resource
    private HelloWorldService helloWorldService;

    public void output(){
        helloWorldService.helloWorld();
    }

}
