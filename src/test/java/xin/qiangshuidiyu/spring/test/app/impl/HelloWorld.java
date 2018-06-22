package xin.qiangshuidiyu.spring.test.app.impl;

import xin.qiangshuidiyu.spring.beans.annotation.Value;

/**
 * @author wpy
 * @date 2018/6/19 10:59
 */
public class HelloWorld {

    private String helloWorld;

    public HelloWorld(String helloWorld) {
        this.helloWorld = helloWorld;
    }

    public String getHelloWorld() {
        return helloWorld;
    }

    public void setHelloWorld(String helloWorld) {
        this.helloWorld = helloWorld;
    }
}
