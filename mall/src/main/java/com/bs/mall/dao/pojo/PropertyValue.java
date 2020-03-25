package com.bs.mall.dao.pojo;

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



}
