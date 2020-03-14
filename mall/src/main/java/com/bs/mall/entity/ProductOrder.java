package com.bs.mall.entity;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * author:xs
 * date:2020/3/12 16:07
 * description:订单实体类
 */
@Data
public class ProductOrder {
    private Integer productOrder_id/*订单ID*/;
    private String productOrder_code/*订单流水号*/;
    private Address productOrder_address/*订单地址*/;
    private String productOrder_detail_address/*订单详细地址*/;
    private String productOrder_post/*订单邮政编码*/;
    private String productOrder_receiver/*订单收货人名称*/;
    private String productOrder_mobile/*订单收货人号码*/;
    private Date productOrder_pay_date/*订单支付日期*/;
    private Date productOrder_delivery_date/*订单发货日期*/;
    private Date productOrder_confirm_date/*订单确认日期*/;
    private Byte productOrder_status/*订单状态*/;
    private User productOrder_user/*订单对应用户*/;
    private List<ProductOrderItem> productOrderItemList/*订单项集合*/;

    public ProductOrder() {
    }

    public ProductOrder(Integer productOrder_id, String productOrder_code, Address productOrder_address, String productOrder_detail_address, String productOrder_post, String productOrder_receiver, String productOrder_mobile, Date productOrder_pay_date, Byte productOrder_status, User productOrder_user) {
        this.productOrder_id = productOrder_id;
        this.productOrder_code = productOrder_code;
        this.productOrder_address = productOrder_address;
        this.productOrder_detail_address = productOrder_detail_address;
        this.productOrder_post = productOrder_post;
        this.productOrder_receiver = productOrder_receiver;
        this.productOrder_mobile = productOrder_mobile;
        this.productOrder_pay_date = productOrder_pay_date;
        this.productOrder_status = productOrder_status;
        this.productOrder_user = productOrder_user;
    }

    public ProductOrder(Integer productOrder_id, String productOrder_code, Address productOrder_address, String productOrder_detail_address, String productOrder_post, String productOrder_receiver, String productOrder_mobile, Date productOrder_pay_date, Date productOrder_delivery_date, Date productOrder_confirm_date, Byte productOrder_status, User productOrder_user, List<ProductOrderItem> productOrderItemList) {
        this.productOrder_id = productOrder_id;
        this.productOrder_code = productOrder_code;
        this.productOrder_address = productOrder_address;
        this.productOrder_detail_address = productOrder_detail_address;
        this.productOrder_post = productOrder_post;
        this.productOrder_receiver = productOrder_receiver;
        this.productOrder_mobile = productOrder_mobile;
        this.productOrder_pay_date = productOrder_pay_date;
        this.productOrder_delivery_date = productOrder_delivery_date;
        this.productOrder_confirm_date = productOrder_confirm_date;
        this.productOrder_status = productOrder_status;
        this.productOrder_user = productOrder_user;
        this.productOrderItemList = productOrderItemList;
    }
}
