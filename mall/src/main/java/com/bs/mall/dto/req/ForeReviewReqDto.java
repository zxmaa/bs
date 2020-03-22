package com.bs.mall.dto.req;

import lombok.Data;

@Data
public class ForeReviewReqDto {
    /**
     * 产品编号
     */
    private Integer productId;
    /**
     * 当前页数
     */
    private Integer pageNum;

    /**
     * 每页大小
     */
    private Integer pageSize;
}
