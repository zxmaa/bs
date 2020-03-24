package com.bs.mall.service.fore;

import com.bs.mall.dao.pojo.Product;
import com.bs.mall.dto.req.ForeProductGuessReqDto;
import com.bs.mall.dto.req.ForeQueryProductListReqDto;
import com.bs.mall.dto.req.ForeReviewReqDto;
import com.bs.mall.dto.res.ForeProductDetailsResDto;
import com.bs.mall.dto.res.ForeProductGuessResDto;
import com.bs.mall.dto.res.ForeProductPropertyResDto;
import com.bs.mall.dto.res.ForeReviewResDto;
import com.github.pagehelper.PageInfo;

import java.util.List;


public interface IProductService {


    /**
     *   根据商品类型，或是搜索框输入的商品类型/商品名字模糊查询
     *
     * @param foreQueryProductListReqDto
     * @return
     */
    PageInfo<Product> queryProductList(ForeQueryProductListReqDto foreQueryProductListReqDto);


    /**
     * 猜你喜欢列表:根据categoryid得到
     * @param foreProductGuessReqDto
     * @return
     */
    PageInfo<ForeProductGuessResDto> getProductGuessLike(ForeProductGuessReqDto foreProductGuessReqDto);

    /**
     * 根据productId得到属性列表
     * @param productId
     * @return
     */
     ForeProductPropertyResDto getProductProperty(Integer productId);

    /**
     * 根据productId得到评论详情以及评论总数
     * @param foreReviewReqDto
     * @return
     */
    ForeReviewResDto getReview(ForeReviewReqDto foreReviewReqDto);

    /**
     * 根据productId的到产品的详细信息
     * @param productId
     * @return
     */
    ForeProductDetailsResDto getProductDetails(Integer productId);

    /**
     * 根据productId得到类型名称
     * @param productId
     * @return
     */
    String getCategoryNameByProductId(Integer productId);

    /**
     * 根据产品id得到产品
     */
    Product getProductById(Integer productId);

    /**
     * 得到促销产品
     * @return
     */
    List<Product> getPromotionProduct();
}
