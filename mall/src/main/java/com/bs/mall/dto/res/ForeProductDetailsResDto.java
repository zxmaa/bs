package com.bs.mall.dto.res;

import com.bs.mall.dao.pojo.Category;
import com.bs.mall.dao.pojo.Product;
import com.bs.mall.dao.pojo.ProductImage;
import com.github.pagehelper.PageInfo;
import lombok.Data;

import java.util.List;

/**
 * 产品详情页显示信息
 */
@Data
public class ForeProductDetailsResDto {
    /**
     * 产品的基本信息
     */
    Product product;

    /**
     * 该产品的所属的类型名：用于显示头部的：xx旗舰店
     */
    String categoryName;

    /**
     * 类型：用于搜索框下面
     */
    List<Category> categories;

    /**
     * 预览图片
     */
    List<ProductImage> previewPicture;

    /**
     * 详情图片
     */
    List<ProductImage> detailPicture;

    /**
     * 猜你喜欢
     */
    PageInfo<ForeProductGuessResDto> productGuess;

    /**
     * 产品属性值
     */
    ForeProductPropertyResDto propertyValue;


}
