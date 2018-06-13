package xin.qiangshuidiyu.spring.beans;

import java.util.ArrayList;
import java.util.List;

/**
 * PropertyValue 列表封装
 * @author wpy
 * @date 2018/6/13 14:48
 */
public class PropertyValues {
    private final List<PropertyValue> list = new ArrayList<>();

    public List<PropertyValue> getList() {
        return list;
    }

    public boolean addPropertyValue(PropertyValue propertyValue){
        return this.list.add(propertyValue);
    }
}
