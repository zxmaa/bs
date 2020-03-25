package com.bs.mall.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * 属性值
 */
@Data
public class PropertyValue {
    /**
     * 属性值id
     */
    @TableId(type = IdType.AUTO)
    private Integer propertyValueId;
    /**
     * 属性值value
     */
    private String propertyValueValue;
    /**
     * 属性值对应属性id
     */
    private Integer propertyId;
    /**
     * 属性值对应产品的id
     */
    private Integer productId;

    /**
     * 一下两个是对应的复合属性
     */
    private Property propertyValueProperty/*属性值对应属性*/;
    private Product propertyValueProduct/*属性值对应产品*/;

}
