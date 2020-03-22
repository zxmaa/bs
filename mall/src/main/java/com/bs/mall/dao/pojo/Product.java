package com.bs.mall.dao.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 *产品
 */
@Data
public class Product {
    /**
     * 产品ID
     */
    @TableId(type = IdType.AUTO)
    private Integer productId;

    /**
     * 产品名称
     */
    private String  productName;

    /**
     * 产品标题
     */
    private String productTitle;

    /**
     * 产品原价格
     */
    private Double productPrice;

    /**
     * 产品促销价格
     */
    private Double productSalePrice;

    /**
     * 产品创建日期
     */
    private Date productCreateDate;

    /**
     * 产品对应类型的id
     */
    private Integer productCategoryId;

    /**
     * 产品状态:销售中0 停售中1 促销中2
     */
    @TableField("product_isEnable")
    private Integer productIsEnabled;

    /**
     * 销量数
     */
    private Integer productSaleCount;

    /**
     * 评价数
     */
    private Integer productReviewCount;


    /**
     * 下面这部分是统计产品中复合属性的集合，表中无对应字段
     */
    private List<PropertyValue> propertyValueList/*产品属性值集合*/;
    private List<ProductImage> singleProductImageList/*产品预览图片集合*/;
    private List<ProductImage> detailProductImageList/*产品详细图片集合*/;
    private List<Review> reviewList/*产品评论集合*/;
    private List<ProductOrderItem> productOrderItemList/*产品订单项集合*/;


}
