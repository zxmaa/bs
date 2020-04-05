package com.bs.mall.dto.res;

import com.bs.mall.dao.pojo.Product;
import com.bs.mall.dao.pojo.ProductOrder;
import com.bs.mall.dao.pojo.ProductOrderItem;
import lombok.Data;

import java.util.List;

/**
 * 支付页面响应信息
 */
@Data
public class ForePayResDto {
    /**
     * 产品详情：若待支付的只有产品，将其赋值
     */
    private Product product;

    /**
     *类型名： 若待支付的只有一个产品，将其赋值
     */
    private String categoryName;

    /**
     * 订单详情
     */
    private ProductOrder productOrder;

    /**
     * 支付的金额
     */
    private Double totalPrice;

    /**
     * 订单项信息
     */
    List<ProductOrderItem> productOrderItemList;


}
