package com.bs.mall.dto.req;

import lombok.Data;

/**
 * 添加评论请求
 */
@Data
public class ForeAddReviewReqDto {


    /**
     * 订单项id
     */
    private Integer orderItemId;

    /**
     * 评论内容
     */
    private String reviewContent;
}
