package com.bs.mall.service.fore;

import com.bs.mall.dao.pojo.ProductImage;

import java.util.List;

public interface IProductImageService {

    /**
     * 得到图片
     * type:0 概述图
     *      1 详情图
     *      2 该产品的所有图片
     * @param type
     * @return
     */
    List<ProductImage> getImagesByType(Integer productId,Integer type);
}
