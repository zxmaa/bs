package com.bs.mall.entity;

import lombok.Data;

/**
 * author:xs
 * date:2020/3/13 20:53
 * description:产品图片
 */
@Data
public class ProductImage {
    private Integer productImage_id/*产品图片ID*/;
    private Byte productImage_type/*产品图片类型*/;
    private String productImage_src/*产品图片路径*/;
    private Product productImage_product/*产品图片对应产品*/;

    public ProductImage(){

    }

    public ProductImage(Integer productImage_id, Byte productImage_type, String productImage_src, Product productImage_product) {
        this.productImage_id = productImage_id;
        this.productImage_type = productImage_type;
        this.productImage_src = productImage_src;
        this.productImage_product = productImage_product;
    }

}
