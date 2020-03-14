package com.bs.mall.entity;

import lombok.Data;

import java.util.Date;

/**
 * author:xs
 * date:2020/3/12 16:20
 * description:用户评论类
 */
@Data
public class Review {
    private Integer review_id/*评论ID*/;
    private String review_content/*评论内容*/;
    private Date review_createDate/*评论时间*/;
    private User review_user/*评论对应用户*/;
    private Product review_product/*评论对应产品*/;
    private ProductOrderItem review_orderItem/*评论对应订单项*/;

    public Review() {
    }

    public Review(Integer review_id, String review_content, User review_user, Product review_product) {
        this.review_id = review_id;
        this.review_content = review_content;
        this.review_user = review_user;
        this.review_product = review_product;
    }

    public Review(Integer review_id, String review_content, Date review_createDate, User review_user, Product review_product) {
        this.review_id = review_id;
        this.review_content = review_content;
        this.review_createDate = review_createDate;
        this.review_user = review_user;
        this.review_product = review_product;
    }
}

