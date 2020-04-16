package com.bs.mall.fore.service;

import com.bs.mall.dao.pojo.Category;
import com.bs.mall.dao.pojo.Product;
import com.bs.mall.dao.pojo.ProductImage;
import com.bs.mall.dto.req.ForeProductGuessReqDto;
import com.bs.mall.dto.req.ForeQueryProductListReqDto;
import com.bs.mall.dto.req.ForeReviewReqDto;
import com.bs.mall.dto.res.*;
import com.bs.mall.service.fore.IProductService;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IProductServiceTest {
    @Autowired
    private IProductService productService;

    @Test
    public void queryProductList(){
        ForeQueryProductListReqDto queryProductListReqDto = new ForeQueryProductListReqDto();

        String[] searchValueSplit = "女装 男装".split(" ");
        queryProductListReqDto.setSearchValueSplit(searchValueSplit);
        //queryProductListReqDto.setCategoryId(1);
        queryProductListReqDto.setPageNum(3);
        queryProductListReqDto.setPageSize(10);
        queryProductListReqDto.setOrderBy("product_sale_price");
        queryProductListReqDto.setIsDesc(false);
        PageInfo<Product> productPageInfo = productService.queryProductList(queryProductListReqDto);
        System.out.println(productPageInfo);
        for (Product o : productPageInfo.getList()) {
            System.out.println(o);
        }
    }

    @Test
    public void getProductGuessLikeTest(){
        ForeProductGuessReqDto foreProductGuessReqDto = new ForeProductGuessReqDto();
        foreProductGuessReqDto.setCategoryId(1);
        foreProductGuessReqDto.setPageNum(2);
        foreProductGuessReqDto.setPageSize(5);
        PageInfo<ForeProductGuessResDto> productGuessLike = productService.getProductGuessLike(foreProductGuessReqDto);
        System.out.println(productGuessLike);
        System.out.println(productGuessLike.getPageNum());
        for (ForeProductGuessResDto foreProductGuessResDto : productGuessLike.getList()) {
            System.out.println(foreProductGuessResDto);
        }
    }

    @Test
    public void getProductPropertyTest(){
        ForeProductPropertyResDto productProperty = productService.getProductProperty(1);
        System.out.println(productProperty);
        for (ForePropertyValueResDto forePropertyValueResDto : productProperty.getProperty()) {
            System.out.println(forePropertyValueResDto);
        }
    }

    /*@Test
    public void getReviewTest(){
        ForeReviewReqDto foreReviewReqDto = new ForeReviewReqDto();
        foreReviewReqDto.setProductId(1);
        foreReviewReqDto.setPageNum(2);
        foreReviewReqDto.setPageSize(5);
        ForeReviewResDto review =  productService.getReview(foreReviewReqDto);
        System.out.println(review);
        for (ForeReviewSimpleResDto foreReviewSimpleResDto : review.getReview().getList()) {
            System.out.println(foreReviewSimpleResDto);
        }
    }*/

    @Test
    public void getCategoryNameByProductIdTest(){
        String categoryNameByProductId = productService.getCategoryNameByProductId(1);
        System.out.println(categoryNameByProductId);
    }

    @Test
    public void getProductByIdTest(){
        Product productById = productService.getProductById(10000);
        System.out.println(productById);
    }

    @Test
    public void getPromotionProductTest(){
        List<Product> promotionProduct = productService.getPromotionProduct();
        for (Product product : promotionProduct) {
            System.out.println(product);
        }
    }

    @Test
    public void getProductDetailsTest(){
        ForeProductDetailsResDto productDetails = productService.getProductDetails(1);
        System.out.println(productDetails);

        for (Category category : productDetails.getCategories()) {
            System.out.println("========category===============");
            System.out.println(category);
        }

        for (ProductImage productImage : productDetails.getDetailPicture()) {
            System.out.println("========productImage===============");
            System.out.println(productImage);
        }

        for (ProductImage productImage1 : productDetails.getPreviewPicture()) {
            System.out.println("========productImage1===============");
            System.out.println(productImage1);
        }

        for (ForeProductGuessResDto foreProductGuessResDto : productDetails.getProductGuess().getList()) {
            System.out.println("========foreProductGuessResDto===============");
            System.out.println(foreProductGuessResDto);
        }

        System.out.println("========getPropertyValue===============");
        System.out.println(productDetails.getPropertyValue());
    }
}
