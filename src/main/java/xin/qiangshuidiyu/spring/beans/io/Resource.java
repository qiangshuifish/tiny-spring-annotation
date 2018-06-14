package xin.qiangshuidiyu.spring.beans.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * 标识一个外部资源，读取包名路径下的class文件
 */
public interface Resource {
    /**
     * 读取包名路径下的class文件
     * @return
     * @throws IOException
     */
    InputStream getInputStream() throws IOException;
}
