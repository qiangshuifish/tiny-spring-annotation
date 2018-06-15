package xin.qiangshuidiyu.spring.test;

import org.junit.Test;
import xin.qiangshuidiyu.spring.test.app.impl.HelloWorldServiceImpl;

import java.lang.reflect.Array;
import java.lang.reflect.Field;

/**
 * @author wpy
 * @date 2018/6/13 15:57
 */
public class TestJava {
    @Test
    public void test(){
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


        System.out.println(isBaseType(int.class));
        System.out.println(isBaseType(Integer.class));
        System.out.println(isBaseType(int.class));


        System.out.println(int[].class);
        System.out.println(boolean[].class);

        System.out.println(isBaseType(TestJava.class));
    }

    public boolean isBaseType(Class<?> clazz) {
        try {
            if(clazz.isPrimitive()){
                return true;
            }
            Class type = (Class) clazz.getField("TYPE").get(null);
            return clazz.isPrimitive() || type.isPrimitive();
        } catch (Exception e) {
            return false;
        }
    }
}
