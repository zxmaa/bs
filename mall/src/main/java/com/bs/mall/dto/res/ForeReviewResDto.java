package com.bs.mall.dto.res;

import com.github.pagehelper.PageInfo;
import lombok.Data;

/**
 * 评论响应值
 */
@Data
public class ForeReviewResDto {
    /**
     * 该商品的评论总数
     */
    private Integer count;

    /**
     * 评论详情
     */
    PageInfo<ForeReviewSimpleResDto> review;
}
