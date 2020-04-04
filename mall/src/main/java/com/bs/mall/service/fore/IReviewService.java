package com.bs.mall.service.fore;

import com.bs.mall.dto.req.ForeAddReviewReqDto;

public interface IReviewService {

    /**
     * 根据orderItemId，获取该订单项的评论数
     * @param orderItemId
     * @return
     */
   Integer getTotalByOrderItemId(Integer orderItemId);

    /**
     * 添加评论
     * @param addReviewReqDto
     */
   void addReview(ForeAddReviewReqDto addReviewReqDto);
}
