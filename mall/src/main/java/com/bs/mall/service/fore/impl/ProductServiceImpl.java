package com.bs.mall.service.fore.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bs.mall.dao.CategoryMapper;
import com.bs.mall.dao.ProductImageMapper;
import com.bs.mall.dao.ProductMapper;
import com.bs.mall.dao.ReviewMapper;
import com.bs.mall.dao.pojo.Category;
import com.bs.mall.dao.pojo.Product;
import com.bs.mall.dao.pojo.ProductImage;
import com.bs.mall.dto.req.ForeProductGuessReqDto;
import com.bs.mall.dto.req.ForeQueryProductListReqDto;
import com.bs.mall.dto.req.ForeReviewReqDto;
import com.bs.mall.dto.res.*;
import com.bs.mall.service.fore.ICategoryService;
import com.bs.mall.service.fore.IProductService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class ProductServiceImpl implements IProductService {
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private ProductImageMapper productImageMapper;
    @Autowired
    private ReviewMapper reviewMapper;
    @Autowired
    private ICategoryService categoryService;
    @Autowired
    private CategoryMapper categoryMapper;

    //=====================user========================================================
    /**
     * 根据商品类型，或是搜索框输入的商品类型/商品名字模糊查询
     * @param foreQueryProductListReqDto
     * @return
     */
    @Override
    public PageInfo<Product> queryProductList(ForeQueryProductListReqDto foreQueryProductListReqDto) {
        if(null == foreQueryProductListReqDto.getPageNum()){
            //默认第一页
            foreQueryProductListReqDto.setPageNum(1);
        }
        if(null == foreQueryProductListReqDto.getPageSize()){
            //默认每页20个产品
            foreQueryProductListReqDto.setPageSize(20);
        }
        PageHelper.startPage(foreQueryProductListReqDto.getPageNum(), foreQueryProductListReqDto.getPageSize());
        List<Product> products = productMapper.queryProductListFore(foreQueryProductListReqDto);
        PageInfo<Product> pageInfo = new PageInfo<Product>(products);
        return pageInfo;
    }

    /**
     * 猜你喜欢列表
     * @param foreProductGuessReqDto
     * @return
     */
    @Override
    public PageInfo<ForeProductGuessResDto> getProductGuessLike(ForeProductGuessReqDto foreProductGuessReqDto) {
        if(null == foreProductGuessReqDto.getPageNum()){
            //默认第一页
            foreProductGuessReqDto.setPageNum(1);
        }
        if(null == foreProductGuessReqDto.getPageSize()){
            //默认每页3个产品
            foreProductGuessReqDto.setPageSize(3);
        }
        PageHelper.startPage(foreProductGuessReqDto.getPageNum(), foreProductGuessReqDto.getPageSize());
        QueryWrapper<Product>queryWrapper  = new QueryWrapper<>();
        queryWrapper.eq("product_category_id", foreProductGuessReqDto.getCategoryId());
        queryWrapper.ne("product_isEnabled",1); //排除停售的
        List<Product> products = productMapper.selectList(queryWrapper);

        List<ForeProductGuessResDto> productGuess = new ArrayList<>();
        ForeProductGuessResDto temp ;
        for (Product product : products) {
            temp = new ForeProductGuessResDto();
            temp.setProductId(product.getProductId());

            QueryWrapper<ProductImage> wrapper = new QueryWrapper<>();
            wrapper.eq("product_image_product_id",product.getProductId());
            wrapper.eq("product_image_type",0);
            List<ProductImage> productImages = productImageMapper.selectList(wrapper);

            temp.setProductImageSrc(productImages.get(0).getProductImageSrc());
            productGuess.add(temp);

        }

        PageInfo<ForeProductGuessResDto>  pageInfo = new PageInfo<>(productGuess);
        return pageInfo;
    }


    /**
     * 根据productId得到属性列表
     * @param productId
     * @return
     */
    @Override
    public ForeProductPropertyResDto getProductProperty(Integer productId) {
        ForeProductPropertyResDto foreProductPropertyResDto = new ForeProductPropertyResDto();

        Product product = productMapper.selectById(productId);
        foreProductPropertyResDto.setProductName(product.getProductName());

        List<ForePropertyValueResDto> property = productMapper.getPropertyValueByProductIdFore(productId);
        foreProductPropertyResDto.setProperty(property);

        return foreProductPropertyResDto;
    }

    /**
     * 根据productId得到评论详情以及评论总数
     * @param foreReviewReqDto
     * @return
     */
    @Override
    public ForeReviewResDto getReview(ForeReviewReqDto foreReviewReqDto) {
        ForeReviewResDto foreReviewResDto = new ForeReviewResDto();

        Product product = productMapper.selectById(foreReviewReqDto.getProductId());
        foreReviewResDto.setCount(product.getProductReviewCount());
        //该商品无评论时
        if(product.getProductReviewCount()==0){
            return foreReviewResDto;
        }

        if(null == foreReviewReqDto.getPageNum()){
            //默认第一页
            foreReviewReqDto.setPageNum(1);
        }
        if(null == foreReviewReqDto.getPageSize()){
            //默认每页5个评论
            foreReviewReqDto.setPageSize(3);
        }

        PageHelper.startPage(foreReviewReqDto.getPageNum(), foreReviewReqDto.getPageSize());
        List<ForeReviewSimpleResDto> reviewSimple = productMapper.getReviewFore(foreReviewReqDto.getProductId());
        PageInfo<ForeReviewSimpleResDto> review = new PageInfo<>(reviewSimple);
        foreReviewResDto.setReview(review);
        return foreReviewResDto;
    }

    /**
     * 根据productId的到产品的详细信息
     * @param productId
     * @return
     */
    @Override
    public ForeProductDetailsResDto getProductDetails(Integer productId) {
        ForeProductDetailsResDto result = new ForeProductDetailsResDto();

        //产品基本信息
        Product product = productMapper.selectById(productId);
        result.setProduct(product);

        //该产品的类型名
        String categoryName = getCategoryNameByProductId(productId);
        result.setCategoryName(categoryName);

        //三个类型名
        List<Category> allCategory = categoryService.getAllCategory();
        //选出类型的前三个
        List<Category> categories = allCategory.stream().limit(3).collect(Collectors.toList());
        result.setCategories(categories);

        //预览图片
        QueryWrapper<ProductImage> wrapper1 = new QueryWrapper<>();
        wrapper1.eq("product_image_product_id",productId);
        wrapper1.eq("product_image_type",0);
        List<ProductImage> previewImages = productImageMapper.selectList(wrapper1);
        result.setPreviewPicture(previewImages);

        //详情图片
        QueryWrapper<ProductImage> wrapper2 = new QueryWrapper<>();
        wrapper2.eq("product_image_product_id",productId);
        wrapper2.eq("product_image_type",1);
        List<ProductImage> detailsImages = productImageMapper.selectList(wrapper2);
        result.setDetailPicture(detailsImages);

        //猜你喜欢
        ForeProductGuessReqDto productGuess = new ForeProductGuessReqDto();
        productGuess.setCategoryId(product.getProductCategoryId());
        PageInfo<ForeProductGuessResDto> guessLikes = getProductGuessLike(productGuess);
        result.setProductGuess(guessLikes);

        //商品属性值
        ForeProductPropertyResDto productProperty = getProductProperty(productId);
        result.setPropertyValue(productProperty);

        return result;
    }

    /**
     * 根据productId得到类型名称
     * @param productId
     * @return
     */
    @Override
    public String getCategoryNameByProductId(Integer productId) {
        Product product = productMapper.selectById(productId);
        Category category = categoryMapper.selectById(product.getProductCategoryId());
        return category.getCategoryName();
    }


}
