package com.bs.mall.dto;

import lombok.Data;

@Data
public class ForeProductSimpleDto {
    /**
     * 产品ID
     */
    private Integer productId;

    /**
     * 产品标题
     */
    private String productTitle;

    /**
     * 产品名称
     */
    private String  productName;

    /**
     * 产品促销价格
     */
    private Double productSalePrice;

    /**
     * 一张概述图
     */
    private String productImageSrc;
}
