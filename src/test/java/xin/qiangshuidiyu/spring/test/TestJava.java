package xin.qiangshuidiyu.spring.test;

import xin.qiangshuidiyu.spring.test.app.impl.HelloWorldServiceImpl;

import java.lang.reflect.Field;

/**
 * @author wpy
 * @date 2018/6/13 15:57
 */
public class TestJava {
    public static void main(String[] args) {
        Class<HelloWorldServiceImpl> clazz = HelloWorldServiceImpl.class;


        System.out.println(clazz.isAssignableFrom(HelloWorldServiceImpl.class));

//        System.out.println(clazz.getName());
        System.out.println(clazz.getCanonicalName());
        System.out.println(clazz.getName());
        System.out.println(clazz.getSimpleName());
        Field[] fields = clazz.getDeclaredFields();


        System.out.println(fields[0].getClass().getName());
        System.out.println(fields[0].getType());
//        System.out.println(Arrays.toString(fields));
    }
}
