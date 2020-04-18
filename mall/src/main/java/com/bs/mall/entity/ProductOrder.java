package com.bs.mall.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
    private Address productOrderAddress;



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
    private  Byte productOrderStatus;

    /**
     * 订单对应用户id
     */
    private Integer userId;

    private User productOrderUser/*订单对应用户*/;
    private List<ProductOrderItem> productOrderItemList/*订单项集合*/;
    private String productOrderDetailAddress/*订单详细地址*/;

    public String formatTime(Date date) {
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(date);
    }
}
