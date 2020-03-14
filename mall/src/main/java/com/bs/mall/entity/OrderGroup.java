package com.bs.mall.entity;

import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * author:xs
 * date:2020/3/12 20:32
 * description:辅助后台图表生成，数据库无对应的表
 */
@Data
public class OrderGroup {
    private Date productOrder_pay_date/*订单组的支付日期*/;
    private Integer productOrder_count/*订单组的统计个数*/;
    private Byte productOrder_status/*订单组的订单状态*/;

    public String getProductOrder_pay_date() {//不是重载的话，会把@Data生成的覆盖掉
        if (productOrder_pay_date != null) {
            SimpleDateFormat time = new SimpleDateFormat("MM/dd", Locale.UK);
            return time.format(productOrder_pay_date);
        }        return null;
    }

}
