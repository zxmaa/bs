package com.bs.mall.dto.res;

import com.bs.mall.dao.pojo.ProductImage;
import com.bs.mall.dao.pojo.ProductOrderItem;
import lombok.Data;

/**
 * 用于确认收货的详情显示
 */
@Data
public class ForeOrderItemReceiptResDto {
    /**
     * 订单项
     * 注：产品单价，从这里读
     */
    ProductOrderItem productOrderItem;

    /**
     * 产品名称
     */
    private String productName;

    /**
     * 产品的一张概述图片
     */
    private ProductImage productImage;
}
