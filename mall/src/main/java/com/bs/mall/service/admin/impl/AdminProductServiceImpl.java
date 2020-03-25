package com.bs.mall.service.admin.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bs.mall.dao.*;
import com.bs.mall.dao.pojo.*;
import com.bs.mall.service.admin.BaseService;
import com.bs.mall.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * author:xs
 * date:2020/3/21 21:56
 * description:产品展示service
 */
@Service("adminProductServiceImpl")
public class AdminProductServiceImpl extends BaseService{
    @Autowired
    CategoryMapper categoryMapper;
    @Autowired
    ProductMapper productMapper;
    @Autowired
    ProductImageMapper productImageMapper;
    @Autowired
    PropertyMapper propertyMapper;
    @Autowired
    PropertyValueMapper propertyValueMapper;
    @Autowired
    LastIDMapper lastIDMapper;

    public String goToPage(HttpSession session, Map<String, Object> map){
        logger.info("获取产品分类列表");
        QueryWrapper<Category> wrapper=new QueryWrapper<>();
        List<Category> categoryList=categoryMapper.selectList(wrapper);
        map.put("categoryList", categoryList);
        logger.info("获取前10条产品列表");
        PageUtil pageUtil = new PageUtil(0, 10);
        List<com.bs.mall.entity.Product> productList=productMapper.select(null, null,null, pageUtil);
        map.put("productList", productList);
        logger.info("获取产品总数量");
        Integer productCount=productMapper.selectTotal(null, null);
        map.put("productCount", productCount);
        logger.info("获取分页信息");
        pageUtil.setTotal(productCount);
        map.put("pageUtil", pageUtil);

        logger.info("转到后台管理-产品页-ajax方式");
        return "admin/productManagePage";
    }

    //转到后台管理-产品详情页-ajax
    public String goToDetailsPage(HttpSession session, Map<String, Object> map, Integer pid/* 产品ID */){
        logger.info("获取product_id为{}的产品信息",pid);
        com.bs.mall.entity.Product product=productMapper.selectOne(pid);
        Integer product_id =product.getProductId();

        logger.info("获取单个产品预览图片信息");
        List<com.bs.mall.entity.ProductImage> singleProductImageList=productImageMapper.select(product_id, (byte) 0, null);
        product.setDetailProductImageList(singleProductImageList);
        map.put("product", product);


        logger.info("获取产品详情图片信息");
        List<com.bs.mall.entity.ProductImage> detailsProductImageList=productImageMapper.select(product_id, (byte) 1, null);
        product.setDetailProductImageList(detailsProductImageList);

        logger.info("获取产品详情-属性值信息");
        com.bs.mall.entity.PropertyValue propertyValue=new com.bs.mall.entity.PropertyValue();
        propertyValue.setPropertyValueProduct(product);
        List<com.bs.mall.entity.PropertyValue> propertyValueList=propertyValueMapper.select(propertyValue, null);

        logger.info("获取产品详情-分类信息对应的属性列表");
        com.bs.mall.entity.Property property=new com.bs.mall.entity.Property();
        property.setPropertyCategory(product.getProductCategory());
        List<com.bs.mall.entity.Property> propertyList=propertyMapper.select(property, null);

        logger.info("属性列表和属性值列表信息合并");
        for (com.bs.mall.entity.Property property1 : propertyList){
            for (com.bs.mall.entity.PropertyValue propertyValue1 : propertyValueList){
                if (property1.getPropertyId().equals(propertyValue1.getPropertyValueProperty().getPropertyId())){
                    List<com.bs.mall.entity.PropertyValue> property_value_item = new ArrayList<>(1);
                    property_value_item.add(propertyValue1);
                    property1.setPropertyValueList(property_value_item);
                    break;
                }
            }
        }

        map.put("propertyList",propertyList);
        logger.info("获取分类列表");
        List<Category> categoryList=categoryMapper.select(null, null);
        map.put("categoryList",categoryList);

        logger.info("转到后台管理-产品详情页-ajax方式");
        return "admin/include/productDetails";

    }
}
