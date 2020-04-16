package com.bs.mall.service.fore.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bs.mall.dao.ProductImageMapper;
import com.bs.mall.dao.pojo.ProductImage;
import com.bs.mall.service.fore.IProductImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductImageServiceImpl implements IProductImageService {
    @Autowired
    private ProductImageMapper productImageMapper;
    /**
     * 得到图片
     * @param productId
     * @param type
     * @return
     */
    @Override
    public List<ProductImage> getImagesByType(Integer productId, Integer type) {
        QueryWrapper<ProductImage> wrapper = new QueryWrapper<>();
        List<ProductImage> productImages = null;
        if(type.equals(2)){
            wrapper.eq("product_image_product_id",productId);
            productImages = productImageMapper.selectList(wrapper);
        }else{
            wrapper.eq("product_image_product_id",productId)
                    .eq("product_image_type",type);
           productImages = productImageMapper.selectList(wrapper);
        }
        return productImages;
    }
}
