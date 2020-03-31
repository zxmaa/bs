package com.bs.mall.dto.res;


import lombok.Data;

import java.util.Date;

@Data
public class ForeReviewSimpleResDto {
    /**
     * 用户登录名
     */
    private String userName;

    /**
     * 评论内容
     */
    private String reviewContent;

    /**
     * 评论时间
     */
    private Date reviewCreatedate;
}
