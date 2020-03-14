package com.bs.mall.entity;

import java.util.List;

/**
 * author:xs
 * date:2020/3/13 20:17
 * description:产品类型实体类
 */
public class Category {
    private Integer category_id/*类型ID*/;
    private String category_name/*类型名称*/;
    private String category_image_src/*类型图片路径*/;
    private List<Property> propertyList/*属性列表*/;
    private List<Product> productList/*产品集合*/;
    private List<List<Product>> complexProductList/*产品二维集合*/;

    public Category(){

    }

    public Category(Integer category_id, String category_name, String category_image_src) {
        this.category_id = category_id;
        this.category_name = category_name;
        this.category_image_src = category_image_src;
    }
}
