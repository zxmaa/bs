package com.bs.mall.entity;

import lombok.Data;

/**
 * author:xs
 * date:2020/3/13 20:30
 * description:属性值实体类
 */
@Data
public class PropertyValue {
    private Integer propertyValue_id/*属性值ID*/;
    private String propertyValue_value/*属性值Value*/;
    private Property propertyValue_property/*属性值对应属性*/;
    private Product propertyValue_product/*属性值对应产品*/;

    public PropertyValue() {
    }

    public PropertyValue(Integer propertyValue_id, String propertyValue_value, Property propertyValue_property, Product propertyValue_product) {
        this.propertyValue_id = propertyValue_id;
        this.propertyValue_value = propertyValue_value;
        this.propertyValue_property = propertyValue_property;
        this.propertyValue_product = propertyValue_product;
    }



}
