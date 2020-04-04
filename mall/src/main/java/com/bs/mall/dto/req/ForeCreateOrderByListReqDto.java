package com.bs.mall.dto.req;

import lombok.Data;

import java.util.List;

@Data
public class ForeCreateOrderByListReqDto {
    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 用户地址id
     */
    private Integer userAddressId;

    /**
     * 订单项id和用户留言的list
     */
    List<ForeCreateOrderByListSimpleReqDto> createOrderByListSimpleReqDtos;
}
