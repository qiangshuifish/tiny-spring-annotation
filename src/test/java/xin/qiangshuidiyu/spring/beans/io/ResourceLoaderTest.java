package xin.qiangshuidiyu.spring.beans.io;

import org.junit.Test;

import java.io.IOException;
import java.util.Properties;

import static org.junit.Assert.*;

public class ResourceLoaderTest {

    @Test
    public void getResource() {
        ResourceLoader resourceLoader = new ResourceLoader();

        UrlResource xin = (UrlResource) resourceLoader.getResource("xin");
        System.out.println(xin.getUrl());
    }


    @Test
    public void getProperties() throws IOException {
        ResourceLoader resourceLoader = new ResourceLoader();
        Resource resource = resourceLoader.getResource("application.properties");
        Properties properties = new Properties();
        properties.load(resource.getInputStream());

        Object o = properties.get("hello-world");
        System.out.println(o);
        System.out.println(o.getClass());


        Object number = properties.get("test-number");
        System.out.println(number);
        System.out.println(number.getClass());

        Object array = properties.get("test-number-array");
        System.out.println(array);
        System.out.println(array.getClass());



    }
}