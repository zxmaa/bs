package com.bs.mall.service.fore.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bs.mall.dao.ReviewMapper;
import com.bs.mall.dao.pojo.Product;
import com.bs.mall.dao.pojo.ProductOrderItem;
import com.bs.mall.dao.pojo.Review;
import com.bs.mall.dto.req.ForeAddReviewReqDto;
import com.bs.mall.service.fore.IProductOrderItemService;
import com.bs.mall.service.fore.IProductService;
import com.bs.mall.service.fore.IReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class ReviewServiceImpl implements IReviewService {

    @Autowired
    private ReviewMapper reviewMapper;
    @Autowired
    private IProductOrderItemService orderItemService;
    @Autowired
    private IProductService productService;
    /**
     * 根据orderItemId，获取该订单项的评论数
     * @param orderItemId
     * @return
     */
    @Override
    public Integer getTotalByOrderItemId(Integer orderItemId) {
        QueryWrapper<Review> wrapper = new QueryWrapper<>();
        wrapper.eq("product_order_item_id",orderItemId);
        Integer count = reviewMapper.selectCount(wrapper);
        return count;
    }

    /**
     * 添加评论
     * @param addReviewReqDto
     */
    @Override
    @Transactional(rollbackFor=Exception.class)
    public void addReview(ForeAddReviewReqDto addReviewReqDto) {
        ProductOrderItem productOrderItem = orderItemService.selectOrderItemById(addReviewReqDto.getOrderItemId());
        Product product = productService.getProductById(productOrderItem.getProductId());
        //添加评论
        Review review = new Review();
        review.setReviewContent(addReviewReqDto.getReviewContent());
        review.setReviewCreatedate(new Date());
        review.setUserId(productOrderItem.getUserId());
        review.setProductId(productOrderItem.getProductId());
        review.setProductOrderItemId(productOrderItem.getProductOrderItemId());
        reviewMapper.insert(review);

        //更新产品评论信息
        Product updateProduct = new Product();
        product.setProductId(product.getProductId());
        product.setProductReviewCount(product.getProductReviewCount()+1);
        productService.updateProduct(product);

        return;

    }


}
