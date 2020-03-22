package com.bs.mall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bs.mall.dao.CategoryMapper;
import com.bs.mall.dao.ProductMapper;
import com.bs.mall.dao.pojo.Category;
import com.bs.mall.dao.pojo.Product;
import com.bs.mall.dto.ProductSimpleDto;
import com.bs.mall.dto.res.CategoryAndProductResDto;
import com.bs.mall.service.ICategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 类型的service实现类
 */
@Service
public class CategoryServiceImpl implements ICategoryService {
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private ProductMapper productMapper;

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
    public List<CategoryAndProductResDto> getCategoryAndProduct() {
        List<CategoryAndProductResDto> result = new ArrayList<>();

        //得到所有的类型
        List<Category> categories = categoryMapper.selectList(new QueryWrapper<Category>());

        //得到所有的出售的产品
        QueryWrapper<Product> wrapper = new QueryWrapper<>();
        wrapper.ne("product_isEnabled",1); //1：停售
        List<Product> products = productMapper.selectList(wrapper);
        CategoryAndProductResDto temp = null;
        List<ProductSimpleDto> productSimpleDtos = null;
        for (Category category : categories) {//遍历每个类型
            temp = new CategoryAndProductResDto();
            BeanUtils.copyProperties(category,temp);
            //筛选出该类型下的产品
            List<Product> productTemp = products.stream().filter(product -> product.getProductCategoryId().equals(category.getCategoryId())).limit(8).collect(Collectors.toList());
            productSimpleDtos = new ArrayList<>();
            for (Product product : productTemp) {
                ProductSimpleDto productSimpleDto = new ProductSimpleDto();
                BeanUtils.copyProperties(product,productSimpleDto);
                productSimpleDtos.add(productSimpleDto);
            }
            temp.setProductSimpleDtos(productSimpleDtos);
            result.add(temp);
        }

        return result;


        //======================admin===================================================
    }
}
