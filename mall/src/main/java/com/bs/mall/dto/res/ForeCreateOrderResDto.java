package com.bs.mall.dto.res;

import lombok.Data;

@Data
public class ForeCreateOrderResDto {
    /**
     * true:生成订单成功
     * false:生成订单失败
     */
    private Boolean flag;
    /**
     * 生成订单成功，设置订单编码值
     */
    private String ProductOrderCode;

    /**
     * 生成订单失败，存放库存不足的产品的名称
     */
    private String ProductName;
}
