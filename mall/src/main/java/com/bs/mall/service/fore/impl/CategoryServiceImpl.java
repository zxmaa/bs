package com.bs.mall.service.fore.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bs.mall.dao.CategoryMapper;
import com.bs.mall.dao.ProductMapper;
import com.bs.mall.dao.pojo.Category;
import com.bs.mall.dao.pojo.Product;
import com.bs.mall.dao.pojo.ProductImage;
import com.bs.mall.dto.ForeProductSimpleDto;
import com.bs.mall.dto.res.ForeCategoryAndProductResDto;
import com.bs.mall.service.fore.ICategoryService;
import com.bs.mall.service.fore.IProductImageService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

;

/**
 * 类型的service实现类
 */
@Service
public class CategoryServiceImpl implements ICategoryService {
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private IProductImageService productImageService;

    //=====================user========================================================

    /***
     * 得到所有的category
     * @return
     */
    @Override
    public List<Category> getAllCategory() {
        List<Category> categories = categoryMapper.selectList(new QueryWrapper<Category>());
        return categories;
    }

    /**
     * 得到每个category下的产品的部分信息:用于首页
     * @return
     */
    @Override
    public List<ForeCategoryAndProductResDto> getCategoryAndProduct() {
        List<ForeCategoryAndProductResDto> result = new ArrayList<>();

        //得到所有的类型
        List<Category> categories = categoryMapper.selectList(new QueryWrapper<Category>());

        //得到所有的出售的产品
        QueryWrapper<Product> wrapper = new QueryWrapper<>();
        wrapper.ne("product_isEnabled",1); //1：停售
        List<Product> products = productMapper.selectList(wrapper);
        ForeCategoryAndProductResDto temp = null;
        List<ForeProductSimpleDto> foreProductSimpleDtos = null;
        for (Category category : categories) {//遍历每个类型
            temp = new ForeCategoryAndProductResDto();
            BeanUtils.copyProperties(category,temp);
            //筛选出该类型下的产品:最多选8个
            List<Product> productTemp = products.stream().filter(product -> product.getProductCategoryId().equals(category.getCategoryId())).limit(8).collect(Collectors.toList());
            foreProductSimpleDtos = new ArrayList<>();
            for (Product product : productTemp) {
                ForeProductSimpleDto foreProductSimpleDto = new ForeProductSimpleDto();
                BeanUtils.copyProperties(product, foreProductSimpleDto);

                //得到每个产品的一张概述图
                List<ProductImage> images = productImageService.getImagesByType(product.getProductId(), 0);
                if(images != null){
                    foreProductSimpleDto.setProductImageSrc(images.get(0).getProductImageSrc());
                }
                foreProductSimpleDtos.add(foreProductSimpleDto);
            }
            temp.setForeProductSimpleDtos(foreProductSimpleDtos);
            result.add(temp);
        }

        return result;
    }

    /**
     * 根据categoryId返回产品信息
     * @param categoryId
     * @return
     */
    @Override
    public List<ForeProductSimpleDto> getProductByCategoryId(Integer categoryId) {
        QueryWrapper<Product> wrapper = new QueryWrapper<>();
        wrapper.eq("product_category_id",categoryId)
                .ne("product_isEnabled",1); //排除停售中的

        List<Product> products = productMapper.selectList(wrapper);
        List<Product> products1 = products.stream().limit(20).collect(Collectors.toList());
        List<ForeProductSimpleDto> result = new ArrayList<>();
        ForeProductSimpleDto temp = null;
        for (Product product : products1) {
            temp = new ForeProductSimpleDto();
            BeanUtils.copyProperties(product,temp);
            result.add(temp);
        }
        return result;
    }

    /**
     * 根据categoryId得该category的详细信息
     * @param categoryId
     * @return
     */
    @Override
    public Category getCategoryById(Integer categoryId) {
        return categoryMapper.selectById(categoryId);
    }
}
