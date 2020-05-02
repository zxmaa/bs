package com.bs.mall.service.fore.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bs.mall.dao.CategoryMapper;
import com.bs.mall.dao.ProductImageMapper;
import com.bs.mall.dao.ProductMapper;
import com.bs.mall.dao.ReviewMapper;
import com.bs.mall.dao.pojo.Category;
import com.bs.mall.dao.pojo.Product;
import com.bs.mall.dao.pojo.ProductImage;
import com.bs.mall.dto.ForeProductSimpleDto;
import com.bs.mall.dto.req.ForeProductGuessReqDto;
import com.bs.mall.dto.req.ForeQueryProductListReqDto;
import com.bs.mall.dto.req.ForeReviewReqDto;
import com.bs.mall.dto.res.*;
import com.bs.mall.service.fore.ICategoryService;
import com.bs.mall.service.fore.IProductService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
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
    public PageInfo<ForeProductListResDto> queryProductList(ForeQueryProductListReqDto foreQueryProductListReqDto) {
        if(null == foreQueryProductListReqDto.getPageNum()){
            //默认第一页
            foreQueryProductListReqDto.setPageNum(1);
        }
        if(null == foreQueryProductListReqDto.getPageSize()){
            //默认每页20个产品
            foreQueryProductListReqDto.setPageSize(20);
        }
        if(null == foreQueryProductListReqDto.getIsDesc()){
            //未传值，默认降序
            foreQueryProductListReqDto.setIsDesc(true);
        }
        PageHelper.startPage(foreQueryProductListReqDto.getPageNum(), foreQueryProductListReqDto.getPageSize());
        List<Product> products = productMapper.queryProductListFore(foreQueryProductListReqDto);
        List<ForeProductListResDto> result = new ArrayList<>();

        ForeProductListResDto temp;
        for (Product product : products) {
            temp = new ForeProductListResDto();
            BeanUtils.copyProperties(product,temp);
            //得到产品的类型名
            Category category = categoryService.getCategoryById(product.getProductCategoryId());
            temp.setCategoryName(category.getCategoryName());
            //得到预览图片
            QueryWrapper<ProductImage> wrapper1 = new QueryWrapper<>();
            wrapper1.eq("product_image_product_id",product.getProductId());
            wrapper1.eq("product_image_type",0);
            List<ProductImage> previewImages = productImageMapper.selectList(wrapper1);
            temp.setPreviewPicture(previewImages);
            result.add(temp);
        }


        PageInfo pageInfo = new PageInfo<Product>(products);
        pageInfo.setList(result);
        return pageInfo;
    }

    /**
     * 猜你喜欢列表
     * @param foreProductGuessReqDto
     * @return
     */
    @Override
    public List<ForeProductGuessResDto> getProductGuessLike(ForeProductGuessReqDto foreProductGuessReqDto) {

        QueryWrapper<Product>queryWrapper  = new QueryWrapper<>();
        queryWrapper.eq("product_category_id", foreProductGuessReqDto.getCategoryId());
        queryWrapper.ne("product_isEnabled",1);
        List<Product> productList = productMapper.selectList(queryWrapper);
        //打乱顺序
        Collections.shuffle(productList);

        //返回三个
        List<Product> products = null;
        if(productList != null && productList.size()>3){
            products = productList.stream().limit(3).collect(Collectors.toList());
        }else if(productList != null){
            for (Product product : productList) {
                Product temp = new Product();
                BeanUtils.copyProperties(product,temp);
                products.add(product);
            }
        }

        List<ForeProductGuessResDto> productGuess = new ArrayList<>();
        ForeProductGuessResDto temp ;
        for (Product product : products) {
            temp = new ForeProductGuessResDto();
            temp.setProductId(product.getProductId());
            temp.setProductSalePrice(product.getProductSalePrice());

            QueryWrapper<ProductImage> wrapper = new QueryWrapper<>();
            wrapper.eq("product_image_product_id",product.getProductId());
            wrapper.eq("product_image_type",0);
            List<ProductImage> productImages = productImageMapper.selectList(wrapper);
            if(null == productImages){
                temp.setProductImageSrc(null);
            }else {
                temp.setProductImageSrc(productImages.get(0).getProductImageSrc());
            }
            productGuess.add(temp);

        }


        return productGuess;
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
     * @param productId
     * @return
     */
    @Override
    public ForeReviewResDto getReview(Integer productId) {
        ForeReviewResDto foreReviewResDto = new ForeReviewResDto();

        Product product = productMapper.selectById(productId);
        foreReviewResDto.setCount(product.getProductReviewCount());
        //该商品无评论时
        if(product.getProductReviewCount()==0){
            return foreReviewResDto;
        }

        List<ForeReviewSimpleResDto> reviewSimple = productMapper.getReviewFore(productId);
        foreReviewResDto.setReview(reviewSimple);
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

        //该产品的类型
        Category category = categoryService.getCategoryById(product.getProductCategoryId());
        result.setCategory(category);

        //返回类型名的前三个
        List<Category> allCategory = categoryService.getAllCategory();
        result.setCategories(allCategory.stream().limit(3).collect(Collectors.toList()));

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
        List<ForeProductGuessResDto> guessLikes = getProductGuessLike(productGuess);
        result.setProductGuess(guessLikes);

        //商品属性值
        ForeProductPropertyResDto productProperty = getProductProperty(productId);
        result.setPropertyValue(productProperty);

        //评论
        ForeReviewResDto review = getReview(productId);
        result.setReviewResDto(review);

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

    @Override
    public Product getProductById(Integer productId) {
        Product product = productMapper.selectById(productId);
        return product;
    }

    /**
     * 得到促销产品
     * @return
     */
    @Override
    public List<Product> getPromotionProduct() {
        QueryWrapper<Product> wrapper = new QueryWrapper<>();
        wrapper.eq("product_isEnabled",2);
        List<Product> products = productMapper.selectList(wrapper);
        //选出6个促销产品
        List<Product> result = products.stream().limit(6).collect(Collectors.toList());
        return result;
    }

    /**
     * 根据id修改产品
     * @param product
     */
    @Override
    public void updateProduct(Product product) {
        productMapper.updateById(product);
    }


}
