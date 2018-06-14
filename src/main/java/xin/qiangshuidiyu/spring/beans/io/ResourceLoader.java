package xin.qiangshuidiyu.spring.beans.io;

import java.net.URL;

/**
 * 资源加载类
 * @author wpy
 * @date 2018/6/14 10:23
 */
public class ResourceLoader {

    /**
     * 加载资源获取 Resource 对象
     * @param packageName
     * @return
     */
    public Resource getResource(String packageName){
        URL url = this.getClass().getClassLoader().getResource(packageName);
        return new UrlResource(url);
    }
}
