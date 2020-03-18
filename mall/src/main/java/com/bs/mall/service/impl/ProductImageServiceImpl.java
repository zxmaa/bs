package com.bs.mall.service.impl;

import com.bs.mall.dao.ProductImageMapper;
import com.bs.mall.entity.ProductImage;
import com.bs.mall.service.ProductImageService;
import com.bs.mall.util.PageUtil;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * author:xs
 * date:2020/3/17 16:13
 * description:产品图片service实现类
 */
public class ProductImageServiceImpl implements ProductImageService {
    private ProductImageMapper productImageMapper;

    public void setProductImageMapper(ProductImageMapper productImageMapper) {
        this.productImageMapper = productImageMapper;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public boolean add(ProductImage productImage) {
        return productImageMapper.insertOne(productImage)>0;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public boolean addList(List<ProductImage> productImageList) {
        return productImageMapper.insertList(productImageList) > 0;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public boolean update(ProductImage productImage) {
        return productImageMapper.updateOne(productImage)>0;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public boolean deleteList(Integer[] productImage_id_list) {
        return productImageMapper.deleteList(productImage_id_list)>0;
    }

    @Override
    public List<ProductImage> getList(Integer product_id, Byte productImage_type, PageUtil pageUtil) {
        return productImageMapper.select(product_id,productImage_type,pageUtil);
    }

    @Override
    public ProductImage get(Integer productImage_id) {
        return productImageMapper.selectOne(productImage_id);
    }

    @Override
    public Integer getTotal(Integer product_id, Byte productImage_type) {
        return productImageMapper.selectTotal(product_id,productImage_type);
    }
}
