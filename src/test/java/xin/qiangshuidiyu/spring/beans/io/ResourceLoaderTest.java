package xin.qiangshuidiyu.spring.beans.io;

import org.junit.Test;

import static org.junit.Assert.*;

public class ResourceLoaderTest {

    @Test
    public void getResource() {
        ResourceLoader resourceLoader = new ResourceLoader();

        UrlResource xin = (UrlResource) resourceLoader.getResource("xin");
        System.out.println(xin.getUrl());
    }
}