package com.bs.mall.dao.pojo;

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
     * 订单项对应订单id:若是购物车的物品，值为：null
     * 若是直接购买/或从购物车提交的订单：值为：订单号
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

    /**
     * 下单的时候，才设置该值，即下单时，商品的价钱（因为不同时候，商品的价钱会有改变，以下单的时候为准）
     *
     * 下单后，显示的产品单价：显示这个
     *
     * 下单前的单价：都从product中读取
     */
    private Double productSinglePrice;

}
