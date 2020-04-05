package com.bs.mall.service.fore.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bs.mall.dao.ProductImageMapper;
import com.bs.mall.dao.ProductOrderItemMapper;
import com.bs.mall.dao.pojo.Product;
import com.bs.mall.dao.pojo.ProductImage;
import com.bs.mall.dao.pojo.ProductOrderItem;
import com.bs.mall.dto.req.ForeAddOrderItemCartReqDto;
import com.bs.mall.dto.res.ForeShowOrderItemResDto;
import com.bs.mall.service.fore.IProductOrderItemService;
import com.bs.mall.service.fore.IProductService;
import org.omg.PortableInterceptor.ObjectReferenceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductOrderItemServiceImpl implements IProductOrderItemService {
    @Autowired
    private ProductOrderItemMapper productOrderItemMapper;
    @Autowired
    private IProductService productService;
    @Autowired
    private ProductImageMapper productImageMapper;

    /**
     * 根据orderItemId删除订单项
     * @param orderItemId
     */
    @Override
    public Boolean deleteByOrderItemId(Integer orderItemId) {
        ProductOrderItem productOrderItem = productOrderItemMapper.selectById(orderItemId);
       if(productOrderItem == null){
           return false;
       }
        QueryWrapper<ProductOrderItem> wrapper = new QueryWrapper<>();
        wrapper.eq("product_order_item_id",orderItemId);
        productOrderItemMapper.delete(wrapper);
        return true;
    }

    /**
     * 根据id得到订单项
     * @param orderItemId
     * @return
     */
    @Override
    public ProductOrderItem selectOrderItemById(Integer orderItemId) {
        ProductOrderItem productOrderItem = productOrderItemMapper.selectById(orderItemId);
        return productOrderItem;
    }

    /**
     * 根据userId,得到该用户的购物车的订单项
     * @param userId
     * @return
     */
    @Override
    public List<ProductOrderItem> selectOrderItemCartByUserId(Integer userId) {
        QueryWrapper<ProductOrderItem> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",userId)
                .eq("product_order_id",null);
        List<ProductOrderItem> result = productOrderItemMapper.selectList(wrapper);
        return result;
    }


    /**
     * 增加订单项:用于添加购物车
     * @param orderItemCartReqDto
     */
    @Override
    public Boolean addOrderItemWithCart(ForeAddOrderItemCartReqDto orderItemCartReqDto) {
        //得到该用户购物车中，已有的数据
        List<ProductOrderItem> productOrderItems = selectOrderItemCartByUserId(orderItemCartReqDto.getUserId());

        Product product = productService.getProductById(orderItemCartReqDto.getProductId());
        if(null == product){
            return false;
        }

        ProductOrderItem temp;
        for (ProductOrderItem productOrderItem : productOrderItems) {
            //若用户的购物车中存在该产品,则将购物车的产品数量增加
            if (productOrderItem.getProductId().equals(orderItemCartReqDto.getProductId())){
                temp = new ProductOrderItem();
                temp.setProductOrderItemId(productOrderItem.getProductOrderItemId());
                Integer number = productOrderItem.getProductOrderItemNumber()+orderItemCartReqDto.getProductNumber();
                temp.setProductOrderItemNumber(number);
                temp.setProductOrderItemPrice(number*product.getProductSalePrice());
                productOrderItemMapper.updateById(temp);
                return true;
            }
        }
        //若该产品品，不存在购物车中，则新建订单项
        temp = new ProductOrderItem();
        temp.setProductId(orderItemCartReqDto.getProductId());
        temp.setProductOrderItemNumber(orderItemCartReqDto.getProductNumber());
        temp.setProductOrderItemPrice(orderItemCartReqDto.getProductNumber()*product.getProductSalePrice());
        temp.setUserId(orderItemCartReqDto.getUserId());
        productOrderItemMapper.insert(temp);
        return  true;
    }

    /**
     * 获取该用户购物车的商品总数
     * @param userId
     * @return
     */
    @Override
    public Integer getOrderItemCartNumber(Integer userId) {
        QueryWrapper<ProductOrderItem> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",userId)
                .eq("product_order_id",null);
        Integer count = productOrderItemMapper.selectCount(wrapper);
        return count;
    }

    /**
     * 获取用户购物车订单项详情：用于显示购物车页
     * @param userId
     * @return
     */
    @Override
    public List<ForeShowOrderItemResDto> selectShowOrderItemCart(Integer userId) {
        List<ForeShowOrderItemResDto> result= new ArrayList<>();

        List<ProductOrderItem> productOrderItems = selectOrderItemCartByUserId(userId);
        if(null == productOrderItems){
            return null;
        }

        ForeShowOrderItemResDto temp;
        for (ProductOrderItem productOrderItem : productOrderItems) {
            temp = new ForeShowOrderItemResDto();
            String categoryName = productService.getCategoryNameByProductId(productOrderItem.getProductId());
            Product product = productService.getProductById(productOrderItem.getProductId());
            QueryWrapper<ProductImage> wrapper = new QueryWrapper<>();
            wrapper.eq("product_image_product_id",productOrderItem.getProductId())
                    .eq("product_image_type",0);
            List<ProductImage> productImages = productImageMapper.selectList(wrapper);
            //显示的商品总价，应该根据最新的产品价格来定
            productOrderItem.setProductOrderItemPrice(product.getProductSalePrice()*productOrderItem.getProductOrderItemNumber());
            temp.setProductOrderItem(productOrderItem);
            temp.setCategoryName(categoryName);
            temp.setProduct(product);
            temp.setProductImage(productImages.get(0));
            result.add(temp);
        }
        return result;
    }

    /**
     * 修改购物车数据：用于购物车结算时，修改购物车商品的数量
     *      返回：有值：返回产品名（即该产品库存库存不足）
     *      null:修改成功
     * @param list
     * @return
     */
    @Override
    @Transactional(rollbackFor=Exception.class)
    public String updateCart(List<ProductOrderItem> list) {
        //判断购买的数量是否超过库存
        for (ProductOrderItem productOrderItem : list) {
            ProductOrderItem orderItem = productOrderItemMapper.selectById(productOrderItem.getProductOrderItemId());
            Product product = productService.getProductById(orderItem.getProductId());
            //当超过了库存量
            if(product.getProductQuantity()<productOrderItem.getProductOrderItemNumber()){
                return product.getProductName();
            }
        }
        //修改数据库
        ProductOrderItem temp;
        for (ProductOrderItem productOrderItem : list){
            ProductOrderItem orderItem = productOrderItemMapper.selectById(productOrderItem.getProductOrderItemId());
            Product product = productService.getProductById(orderItem.getProductId());
            temp = new ProductOrderItem();
            temp.setProductOrderItemId(productOrderItem.getProductOrderItemId());
            temp.setProductOrderItemNumber(productOrderItem.getProductOrderItemNumber());
            temp.setProductOrderItemPrice(product.getProductSalePrice()*productOrderItem.getProductOrderItemNumber());
            productOrderItemMapper.updateById(temp);
        }
        return null;
    }

    /**
     * 根据orderItemList，得到购物车结算的订单项详情
     * @param orderItemList 订单项id的list
     * @return
     */
    @Override
    public List<ForeShowOrderItemResDto> getCalculateCart(Integer[] orderItemList) {
        List<ForeShowOrderItemResDto> result = new ArrayList<>();
        ForeShowOrderItemResDto temp;
        for (Integer orderItemId : orderItemList) {
            temp = new ForeShowOrderItemResDto();

            ProductOrderItem productOrderItem = productOrderItemMapper.selectById(orderItemId);
            //得到类型名称
            String categoryName = productService.getCategoryNameByProductId(productOrderItem.getProductId());
            //得到产品详情
            Product product = productService.getProductById(productOrderItem.getProductId());
            //得到概述图
            QueryWrapper<ProductImage> wrapper = new QueryWrapper<>();
            wrapper.eq("product_image_product_id",productOrderItem.getProductId())
                    .eq("product_image_type",0);
            List<ProductImage> productImages = productImageMapper.selectList(wrapper);

            temp.setProductOrderItem(productOrderItem);
            temp.setCategoryName(categoryName);
            temp.setProduct(product);
            temp.setProductImage(productImages.get(0));
            result.add(temp);
        }
        return result;
    }

    /**
     * 根据orderItemList，得到购物车结算的订单项的总价
     * @param orderItemList
     * @return
     */
    @Override
    public Double calculateOrerItemCartMaoney(Integer[] orderItemList) {
        Double result = 0.0;
        for (Integer orderItemId : orderItemList) {
            ProductOrderItem productOrderItem = productOrderItemMapper.selectById(orderItemId);
            Product product = productService.getProductById(productOrderItem.getProductId());
            result += product.getProductSalePrice()*productOrderItem.getProductOrderItemNumber();
        }

        return result;
    }

    /**
     * 用于，点击立即购买后，得到跳转到订单生成页的展示信息
     * @param userId
     * @param productId
     * @param productNum
     * @return
     */
    @Override
    public ForeShowOrderItemResDto showOrderItemWithBuyNow(Integer userId, Integer productId, Integer productNum) {
        ForeShowOrderItemResDto result = new ForeShowOrderItemResDto();

        //得到产品详情
        Product product = productService.getProductById(productId);
        //设置产品名称
        String categoryName = productService.getCategoryNameByProductId(productId);
        //得到一张概述图
        QueryWrapper<ProductImage> wrapper = new QueryWrapper<>();
        wrapper.eq("product_image_product_id",productId)
                .eq("product_image_type",0);
        List<ProductImage> productImages = productImageMapper.selectList(wrapper);
        //设置订单项信息
        ProductOrderItem productOrderItem = new ProductOrderItem();
        productOrderItem.setProductOrderItemNumber(productNum);
        productOrderItem.setProductOrderItemPrice(productNum*product.getProductSalePrice());

        result.setProduct(product);
        result.setCategoryName(categoryName);
        result.setProductImage(productImages.get(0));
        result.setProductOrderItem(productOrderItem);

        return result;
    }

    /**
     * 添加订单项
     * @param productOrderItem
     */
    @Override
    public void addOrderItem(ProductOrderItem productOrderItem) {
        productOrderItemMapper.insert(productOrderItem);
    }

    /**
     * 修改订单项
     * @param productOrderItem
     */
    @Override
    public void updateOrderItem(ProductOrderItem productOrderItem) {
        productOrderItemMapper.updateById(productOrderItem);
    }

    /**
     * 根据订单id，返回订单项
     * @param orderId
     * @return
     */
    @Override
    public List<ProductOrderItem> getOrderItemsByOrderId(Integer orderId) {
        QueryWrapper<ProductOrderItem> wrapper = new QueryWrapper<>();
        wrapper.eq("product_order_id",orderId);

        return productOrderItemMapper.selectList(wrapper);
    }


}
