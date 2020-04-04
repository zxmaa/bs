package com.bs.mall.service.fore;

import com.bs.mall.dao.pojo.ProductOrder;
import com.bs.mall.dto.req.ForeCreateOrderByListReqDto;
import com.bs.mall.dto.req.ForeCreateOrderByOneReqDto;
import com.bs.mall.dto.req.ForeOrderShowReqDto;
import com.bs.mall.dto.res.*;
import com.github.pagehelper.PageInfo;

public interface IProductOrderService {

    /**
     * 生成订单：用于立即购买时，生成订单
     * @param createOrderByOneReqDto
     * @return 返回订单的code
     */
   String  createOrderByOne(ForeCreateOrderByOneReqDto createOrderByOneReqDto);

    /**
     * 生成订单：用于购物车购买生成订单
     * @param createOrderByListReqDto
     * @return
     */
    String createOrderByList(ForeCreateOrderByListReqDto createOrderByListReqDto);

    /**
     * 根据orderCode,得到订单
     * @param orderCode
     * @return
     */
    ProductOrder getProductOrderByOrderCode(String orderCode);

    /**
     * 根据orderCode，返回支付页面，需要显示的信息
     * @param orderCode
     * @return
     */
    ForePayResDto getPayPageInfoByOrderCode(String orderCode);

    /**
     * 点击确认支付的操作（顶单状态改为已支付）
     * 若某个产品库存不足，则返回该产品的名称
     * 成功：返回null
     * @param orderCode
     */
    String payOrder(String orderCode);

    /**
     * 根据orderCode，得到支付成功显示的信息
     * @param orderCode
     * @return
     */
    ForePaySuccessResDto getPaySuccessInfoByOrderCode(String orderCode);

    /**
     * 更改订单状态
     *    0：待付款
     *    1：已支付,待发货
     *    2:已发货
     *    3：已确认收货（即交易成功）
     *    4：交易关闭（即取消订单）
     * @param orderCode
     */
    void updateOrderStatus(String orderCode,Integer orderStatus);



    /**
     * 得到前端确认收货时，跳转到确认收货页，需要显示的信息
    * @param orderCode
    * @return
     */
   ForeConfirmReceiptResDto getConfirmReceiptInfo(String orderCode);

  /**
    * 订单完成页需显示的内容
    * （注：返回值中：若该订单只有一个订单项，且该订单项没有评价过产品，这些属性才设置值
    *  其余这些属性均设为null）
    *
    * @param orderCode
    * @return
   */
  ForeOrderSuceessResDto getOrderSuccessInfo(String orderCode);

 /**
  * 获取我的订单显示信息
  * @param orderShowReqDto
  * @return
  */
  PageInfo<ForeOrderShowResDto> getOrderShow(ForeOrderShowReqDto orderShowReqDto);

    /**
     * 根据orderId，得到订单信息
     * @param orderId
     * @return
     */
  ProductOrder getProductOrderById(Integer orderId);

    /**
     * 根据订单项id,得到需要填写评价页，需要的信息
     * @param orderItemId
     * @return
     */
  ForeWriteReviewResDto getWriteReviewInfo(Integer orderItemId);


}
