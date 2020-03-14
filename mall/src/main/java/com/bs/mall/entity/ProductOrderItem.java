package com.bs.mall.entity;


import lombok.Data;

/**
 * author:xs
 * date:2020/3/12 17:10
 * description:订单单项
 */
@Data
public class ProductOrderItem {
    private Integer productOrderItem_id/*订单项ID*/;
    private Short productOrderItem_number/*订单项产品数量*/;
    private Double productOrderItem_price/*订单项产品总价格*/;
    private String productOrderItem_userMessage/*订单项备注*/;
    private Product productOrderItem_product/*订单项对应产品*/;
    private ProductOrder productOrderItem_order/*订单项对应订单*/;
    private User productOrderItem_user/*订单项对应用户*/;
    private Boolean isReview;//用户是否评价

    public ProductOrderItem() {
    }

    public ProductOrderItem(Integer productOrderItem_id, Short productOrderItem_number, Double productOrderItem_price, Product productOrderItem_product, User productOrderItem_user, String productOrderItem_userMessage) {
        this.productOrderItem_id = productOrderItem_id;
        this.productOrderItem_number = productOrderItem_number;
        this.productOrderItem_price = productOrderItem_price;
        this.productOrderItem_product = productOrderItem_product;
        this.productOrderItem_user = productOrderItem_user;
        this.productOrderItem_userMessage = productOrderItem_userMessage;
    }

    public ProductOrderItem(Integer productOrderItem_id, Short productOrderItem_number, Double productOrderItem_price, Product productOrderItem_product, ProductOrder productOrderItem_order, User productOrderItem_user) {
        this.productOrderItem_id = productOrderItem_id;
        this.productOrderItem_number = productOrderItem_number;
        this.productOrderItem_price = productOrderItem_price;
        this.productOrderItem_product = productOrderItem_product;
        this.productOrderItem_order = productOrderItem_order;
        this.productOrderItem_user = productOrderItem_user;
    }
}
