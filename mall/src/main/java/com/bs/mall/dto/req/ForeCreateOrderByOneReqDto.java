package com.bs.mall.dto.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 创建订单的请求dto(用于立即购买的订单创建)
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ForeCreateOrderByOneReqDto {

    /**
     * 用户id
     */
    private  Integer userId;

    /**
     * 用户地址id
     */
    private Integer userAddressId;
    /**
     * 产品id
     */
    private Integer productId;
    /**
     * 产品数量
     */
    Integer productNum;

    /**
     * 买家留言
     */
     String userMessage;
}
