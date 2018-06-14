package xin.qiangshuidiyu.spring.beans.io;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author wpy
 * @date 2018/6/14 10:22
 */
public class UrlResource implements Resource {
    /**
     * 通过这个url获取资源
     */
    private final URL url;

    public UrlResource(URL url) {
        this.url = url;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        URLConnection urlConnection = url.openConnection();
        urlConnection.connect();
        return urlConnection.getInputStream();
    }

    public URL getUrl() {
        return url;
    }
}
