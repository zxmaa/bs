package com.bs.mall.dto.req;

import lombok.Data;

@Data
public class ForeAddOrderItemCartReqDto {
    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 产品id
     */
    private Integer productId;

    /**
     * 参品数量
     */
    private Integer productNumber;
}
