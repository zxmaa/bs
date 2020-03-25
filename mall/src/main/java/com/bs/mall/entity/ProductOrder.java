package com.bs.mall.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

/**
 * 订单
 */
@Data
public class ProductOrder {
    /**
     * 订单id
     */
    @TableId(type = IdType.AUTO)
    private Integer productOrderId;

    /**
     *  订单流水号
     */
    private String productOrderCode;

    /**
     * 订单地址
     */
    private Integer userAddressId;



    /**
     * 订单邮政编码
     */
    private String productOrderPost;

    /**
     * 订单收货人名称
     */
    private String productOrderReceiver;

    /**
     * 订单收货人号码
     */
    private String productOrderMobile;

    /**
     * 订单支付日期
     */
    private Date productOrderPayDate;

    /**
     * 订单发货日期
     */
    private Date productOrderDeliveryDate;

    /**
     * 订单确认日期
     */
    private Date productOrderConfirmDate;

    /**
     * 订单状态
     */
    private  Integer productOrderStatus;

    /**
     * 订单对应用户id
     */
    private Integer userId;

}
