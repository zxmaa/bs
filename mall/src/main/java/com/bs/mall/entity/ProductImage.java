package com.bs.mall.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 *产品图片
 */
@Data
public class ProductImage {
    /**
     * 产品图片id
     */
    @TableId(type = IdType.AUTO)
    private Integer productImageId;

    /**
     * 产品图片类型:0 概述图片 1详情图片
     */
    private Byte productImageType;

    /**
     * 产品图片路径
     */
    private String productImageSrc;

    /**
     * 产品图片对应产品id
     */
    private Integer productImageProductId;


    private Product productImageProduct/*产品图片对应产品*/;

}
