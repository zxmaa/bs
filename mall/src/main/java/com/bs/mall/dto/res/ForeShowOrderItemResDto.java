package com.bs.mall.dto.res;

import com.bs.mall.dao.pojo.Product;
import com.bs.mall.dao.pojo.ProductImage;
import com.bs.mall.dao.pojo.ProductOrderItem;
import lombok.Data;

import java.util.List;

@Data
public class ForeShowOrderItemResDto {

    /**
     *订单项
     */
    private ProductOrderItem productOrderItem;

    /**
     * 类型名称：用于显示xx店
     */
    private String categoryName;

    /**
     * 产品
     */
    private Product product;

    /**
     * 产品的一张概述图片
     */
    private ProductImage productImage;

    /**
     * 是否评价（此属性，用于显示订单页）
     */
    private Boolean isReview;


}
