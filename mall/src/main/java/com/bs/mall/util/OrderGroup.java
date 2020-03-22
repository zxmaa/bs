package com.bs.mall.util;

import lombok.Data;

import java.util.Date;

/**
 * author:xs
 * date:2020/3/21 16:24
 * description:该类用于辅助后台图表的生成，亦不属于实体类
 */
@Data
public class OrderGroup {
    private Date productOrder_pay_date/*订单组的支付日期*/;

    private Integer productOrder_count/*订单组的统计个数*/;

    private Byte productOrder_status/*订单组的订单状态*/;
}
