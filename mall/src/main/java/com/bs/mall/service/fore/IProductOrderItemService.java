package com.bs.mall.service.fore;

import com.bs.mall.dao.pojo.ProductOrderItem;
import com.bs.mall.dto.req.ForeAddOrderItemCartReqDto;
import com.bs.mall.dto.res.ForeShowOrderItemResDto;

import java.util.List;

public interface IProductOrderItemService {

    /**
     * 根据orderItemId删除订单项
     * @param orderItemId
     */
    Boolean deleteByOrderItemId(Integer orderItemId);

    /**
     * 根据id得到订单项
     * @param orderItemId
     * @return
     */
    ProductOrderItem selectOrderItemById(Integer orderItemId);

    /**
     * 根据userId,得到该用户的购物车的订单项
     * @param userId
     * @return
     */
    List<ProductOrderItem> selectOrderItemCartByUserId(Integer userId);


    /**
     * 增加订单项:用于添加购物车
     * 返回false：添加失败
     * 返回true:添加成功
     * @param orderItemCartReqDto
     */
    Boolean addOrderItemWithCart(ForeAddOrderItemCartReqDto orderItemCartReqDto);

    /**
     * 获取该用户购物车的商品总数
     * @param userId
     * @return
     */
    Integer getOrderItemCartNumber(Integer userId);

    /**
     * 获取用户购物车订单项详情：用于显示购物车页
     * @param userId
     * @return
     */
    List<ForeShowOrderItemResDto> selectShowOrderItemCart(Integer userId);


    /**
     * 修改购物车数据：用于购物车结算时，修改购物车商品的数量
     * 返回：有值：返回产品名（即该产品库存库存不足）
     * null:修改成功
     * @param list
     * @return
     */
    String updateCart(List<ProductOrderItem> list);

    /**
     * 根据orderItemList，得到购物车结算的订单项详情
     * @param orderItemList 订单项id的list
     * @return
     */
    List<ForeShowOrderItemResDto> getCalculateCart(Integer [] orderItemList);

    /**
     * 根据orderItemList，得到购物车结算的订单项的总价
     * @param orderItemList
     * @return
     */
    Double calculateOrderItemCartMoney(Integer [] orderItemList);


    /**
     * 用于，点击立即购买后，得到跳转到订单生成页的展示信息
     * @param userId
     * @param productId
     * @param productNum
     * @return
     */
    ForeShowOrderItemResDto showOrderItemWithBuyNow(Integer userId,Integer productId,Integer productNum);


    /**
     * 添加订单项
     * @param productOrderItem
     */
    void addOrderItem(ProductOrderItem productOrderItem);

    /**
     * 根据订单项id,修改订单项
     * @param productOrderItem
     */
    void updateOrderItem(ProductOrderItem productOrderItem);

    /**
     * 根据订单id，返回订单项
     * @param orderId
     * @return
     */
    List<ProductOrderItem> getOrderItemsByOrderId(Integer orderId);
}
