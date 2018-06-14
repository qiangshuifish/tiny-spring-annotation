package xin.qiangshuidiyu.spring;

/**
 * @author wpy
 * @date 2018/6/13 14:31
 */

public class BeanReference {

    /**
     * ref 到的bean的id
     */
    private String name;
    private Object object;

    public BeanReference(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
