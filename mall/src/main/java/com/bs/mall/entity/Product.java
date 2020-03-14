package com.bs.mall.entity;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * author:xs
 * date:2020/3/13 12:14
 * description:产品类
 */
@Data
public class Product {
    private Integer product_id/*产品ID*/;
    private String product_name/*产品名称*/;
    private String product_title/*产品标题*/;
    private Double product_price/*产品原价格*/;
    private Double product_sale_price/*产品促销价格*/;
    private Date product_create_date/*产品创建日期*/;
    private Category product_category/*产品对应类型*/;
    private Byte product_isEnabled/*产品状态*/;
    private Integer product_sale_count/*销量数*/;
    private Integer product_review_count/*评价数*/;
    private List<PropertyValue> propertyValueList/*产品属性值集合*/;
    private List<ProductImage> singleProductImageList/*产品预览图片集合*/;
    private List<ProductImage> detailProductImageList/*产品详细图片集合*/;
    private List<Review> reviewList/*产品评论集合*/;
    private List<ProductOrderItem> productOrderItemList/*产品订单项集合*/;

    public Product(){

    }

    public Product(Integer product_id, String product_name, Double product_sale_price, Category product_category, Byte product_isEnabled) {
        this.product_id = product_id;
        this.product_name = product_name;
        this.product_sale_price = product_sale_price;
        this.product_category = product_category;
        this.product_isEnabled = product_isEnabled;
    }

    public Product(Integer product_id, String product_name, String product_title, Double product_price, Double product_sale_price, Date product_create_date, Category product_category, Byte product_isEnabled, List<PropertyValue> propertyValueList, List<ProductImage> singleProductImageList, List<ProductImage> detailProductImageList, List<Review> reviewList, List<ProductOrderItem> productOrderItemList,Integer product_sale_count,Integer product_review_count) {
        this.product_id = product_id;
        this.product_name = product_name;
        this.product_title = product_title;
        this.product_price = product_price;
        this.product_sale_price = product_sale_price;
        this.product_create_date = product_create_date;
        this.product_category = product_category;
        this.product_isEnabled = product_isEnabled;
        this.propertyValueList = propertyValueList;
        this.singleProductImageList = singleProductImageList;
        this.detailProductImageList = detailProductImageList;
        this.reviewList = reviewList;
        this.productOrderItemList = productOrderItemList;
        this.product_sale_count = product_sale_count;
        this.product_review_count = product_review_count;
    }

}
