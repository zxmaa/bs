package com.bs.mall.util;

import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * author:xs
 * date:2020/3/21 16:24
 * description:该类用于辅助后台图表的生成，亦不属于实体类
 */

public class OrderGroup {
    private Date product_order_pay_date/*订单组的支付日期*/;

    private Integer product_order_count/*订单组的统计个数*/;

    private Byte product_order_status/*订单组的订单状态*/;

//    public String getProductOrder_pay_date() {
//        if (product_order_pay_date != null) {
//            SimpleDateFormat time = new SimpleDateFormat("MM/dd", Locale.UK);
//            return time.format(product_order_pay_date);
//        }
//        return null;
//    }


    public String getProduct_order_pay_date() {
        if (product_order_pay_date != null) {
            SimpleDateFormat time = new SimpleDateFormat("MM/dd", Locale.UK);
            return time.format(product_order_pay_date);
        }
        return null;
    }

    public void setProduct_order_pay_date(Date product_order_pay_date) {
        this.product_order_pay_date = product_order_pay_date;
    }

    public Integer getProduct_order_count() {
        return product_order_count;
    }

    public void setProduct_order_count(Integer product_order_count) {
        this.product_order_count = product_order_count;
    }

    public Byte getProduct_order_status() {
        return product_order_status;
    }

    public void setProduct_order_status(Byte product_order_status) {
        this.product_order_status = product_order_status;
    }

    @Override
    public String toString() {
        return "OrderGroup{" +
                "product_order_pay_date=" + product_order_pay_date +
                ", product_order_count=" + product_order_count +
                ", product_order_status=" + product_order_status +
                '}';
    }
}
