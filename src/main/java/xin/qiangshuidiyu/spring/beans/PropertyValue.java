package xin.qiangshuidiyu.spring.beans;

/**
 * bean 属性的描述
 * @author wpy
 * @date 2018/6/13 14:47
 */
public class PropertyValue {
    /**
     * 属性名
     */
    private final String name;
    /**
     * 属性值
     */
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
