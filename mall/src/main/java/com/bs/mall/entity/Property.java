package com.bs.mall.entity;

import lombok.Data;

import java.util.List;

/**
 * author:xs
 * date:2020/3/13 20:21
 * description:属性实体类
 */
@Data
public class Property {
    private Integer property_id/*属性ID*/;
    private String property_name/*属性名称*/;
    private Category property_category/*属性对应分类*/;
    private List<PropertyValue> propertyValueList/*属性值集合*/;

    public Property() {
    }

    public Property(Integer property_id, String property_name, Category property_category, List<PropertyValue> propertyValueList) {
        this.property_id = property_id;
        this.property_name = property_name;
        this.property_category = property_category;
        this.propertyValueList = propertyValueList;
    }
}
