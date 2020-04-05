package com.bs.mall.dto.req;

import lombok.Data;

@Data
public class ForeCreateOrderByListSimpleReqDto {
    /**
     * 订单项id
     */
    private Integer orderItemId;
    /**
     * 用户留言
     */
    private String userMessage;
}
