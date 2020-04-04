package com.bs.mall.dto.req;

import lombok.Data;

@Data
public class ForeOrderShowReqDto {
    /**
     * 用户id
     */
    private Integer userId;

    /**
     *
     * 订单状态（为空，则查询所有）
     *      *      * 0：待付款
     *      *      * 1：已支付,待发货
     *      *      * 2:已发货，待确认收货
     *      *      * 3：已确认收货（交易成功）
     *      *      * 4：交易关闭（即取消订单）
     */
    private Integer status;

    /**
     * 页数
     */
    private Integer pageNumber;

    /**
     * 每页大小
     */
    private Integer pageSize;
}
