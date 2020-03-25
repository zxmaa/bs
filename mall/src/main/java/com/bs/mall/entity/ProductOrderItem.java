package com.bs.mall.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;


/**
 * 订单项
 */
@Data
public class ProductOrderItem {
    /**
     * 订单项id
     */
    @TableId(type = IdType.AUTO)
    private Integer productOrderItemId;
    /**
     * 订单项产品数量
     */
    private Integer productOrderItemNumber;
    /**
     * 订单项产品总价格
     */
    private Double productOrderItemPrice;
    /**
     * 订单项对应产品id
     */
    private Integer productId;
    /**
     * 订单项对应订单id
     */
    private Integer productOrderId;
    /**
     * 订单项对应用户id
     */
    private Integer userId;
    /**
     * 订单项备注
     */
    private String productOrderItemUserMessage;

}
