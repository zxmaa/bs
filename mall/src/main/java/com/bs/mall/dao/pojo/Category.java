package com.bs.mall.dao.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * 类型
 */
@Data
public class Category {
    /**
     * 类型ID
     */
    @TableId(type = IdType.AUTO)
    private Integer categoryId;

    /**
     * 类型名称
     */
    private String  categoryName;

    /**
     * 类型图片路径:单个商品点进去后，显示在该商品图片上面的一个图片
     */
    private String  categoryImageSrc;
}
