package xin.qiangshuidiyu.spring.beans;

import java.io.IOException;

/**
 * 属性文件加载器
 * @author wpy
 * @date 2018/6/15 14:38
 */
public interface PropertiesReader {
    /**
     * 加载配置文件
     *
     * @param fileName
     */
    void loadPropertiesReader(String fileName) throws IOException;
}
