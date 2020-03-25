package com.bs.mall.dao.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.List;

/**
 * 属性
 */
@Data
public class Property {
    /**
     * 属性ID
     */
    @TableId(type = IdType.AUTO)
    private Integer propertyId;

    /**
     * 属性名称
     */
    private String propertyName;

    /**
     * 属性对应分类id
     */
    private Integer categoryId;





}
