package com.bs.mall.dao.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

/**
 * 评论
 */
@Data
public class Review {
    /**
     * 评论id
     */
    @TableId(type = IdType.AUTO)
    private Integer reviewId;

    /**
     * 评论内容
     */
    private String reviewContent;

    /**
     * 评论时间
     */
    private Date reviewCreatedate;

    /**
     * 评论对应用户id
     */
    private Integer userId;

    /**
     * 评论对应产品id
     */
    private Integer productId;

    /**
     * 评论对应订单项id
     */
    private Integer productOrderItemId;
}
