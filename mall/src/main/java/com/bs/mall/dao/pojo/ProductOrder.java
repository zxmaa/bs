package com.bs.mall.dao.pojo;

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
    private String productOrderAddress;

    /**
     * 订单详细地址
     */
    private String productOrderDetailAddress;

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
     * 拍下商品的时间
     */
    private Date productOrderReserveDate;

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
     * 0：待付款
     * 1：已支付,待发货
     * 2:已发货，待确认收货
     * 3：已确认收货（交易成功）
     * 4：交易关闭（即取消订单）
     */
    private  Integer productOrderStatus;

    /**
     * 订单对应用户id
     */
    private Integer userId;

}
