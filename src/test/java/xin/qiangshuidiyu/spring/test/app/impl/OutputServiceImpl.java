package xin.qiangshuidiyu.spring.test.app.impl;

import xin.qiangshuidiyu.spring.beans.annotation.Component;
import xin.qiangshuidiyu.spring.beans.annotation.Scope;
import xin.qiangshuidiyu.spring.test.app.OutputService;

import javax.annotation.Resource;

/**
 * @author wpy
 * @date 2018/6/13 16:16
 */
@Component
@Scope(value = Scope.SCOPE_PROTOTYPE)
public class OutputServiceImpl implements OutputService {

    @Resource
    private HelloWorldServiceImpl helloWorldServiceImpl;

    @Override
    public void output(){
        helloWorldServiceImpl.helloWorld();
    }

}
