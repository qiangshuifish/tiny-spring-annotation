package xin.qiangshuidiyu.spring.beans;

/**
 * bean 属性的描述
 * @author wpy
 * @date 2018/6/13 14:47
 */
public class PropertyValue {
    private final String name;
    private final Object value;

    public PropertyValue(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }
}
