package com.bs.mall.controller.fore;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bs.mall.controller.BaseController;
import com.bs.mall.dao.pojo.Category;
import com.bs.mall.dao.pojo.Product;
import com.bs.mall.dao.pojo.ProductOrder;
import com.bs.mall.dao.pojo.ProductOrderItem;
import com.bs.mall.dto.ForeUserDto;
import com.bs.mall.dto.req.*;
import com.bs.mall.dto.res.*;
import com.bs.mall.service.fore.*;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
public class ForeOrderController  extends BaseController {
    @Autowired
    private IUserService userService;
    @Autowired
    private IProductOrderItemService productOrderItemService;
    @Autowired
    private IProductService productService;
    @Autowired
    private IUserAddressService userAddressService;
    @Autowired
    private IProductOrderService productOrderService;
    @Autowired
    private ICategoryService categoryService;

    @RequestMapping(value = "/order",method = RequestMethod.GET)
    public String goToPageSimple(){
        return "redirect:/order/0/10";
    }

    /**
     * 我的订单（分页）
     * @param session
     * @param model
     * @param status
     * @param pageNumber
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/order/{pageNumber}/{pageSize}",method = RequestMethod.GET)
    public String goToOrderPage(HttpSession session,Model model, @RequestParam(required = false) Integer status,
                                @PathVariable("pageNumber") Integer pageNumber/* 页数 */,
                                @PathVariable("pageSize") Integer pageSize/* 行数*/){
        Object o = checkUser(session);
        if(null == o){
            return "redirect:/login";
        }
        ForeUserDto user = userService.findUserByUsereName((String) o);
        Integer userId = user.getUserId();

        ForeOrderShowReqDto foreOrderShowReqDto = new ForeOrderShowReqDto();
        foreOrderShowReqDto.setUserId(userId);
        foreOrderShowReqDto.setStatus(status);
        foreOrderShowReqDto.setPageNumber(pageNumber);
        foreOrderShowReqDto.setPageSize(pageSize);
        PageInfo<ForeOrderShowResDto> orderShow = productOrderService.getOrderShow(foreOrderShowReqDto);

        List<Category> categoryList = categoryService.getAllCategory();

        model.addAttribute("status",status);
        model.addAttribute("orderShow",orderShow);
        model.addAttribute("categoryList",categoryList);

        logger.info("转到前台-订单列表页");
        return "user/orderListPage";

    }

    /**
     * 用于点击“立即购买”后---跳转到前台订单建立页(productBuyPage.jsp)
     * @param productId
     * @param productNumber
     * @param session
     * @return
     */
    @RequestMapping(value = "/order/create/{productId}",method = RequestMethod.GET)
    public String goToOrderConfirmPage(@PathVariable("productId") Integer productId,
                                       @RequestParam(required = false, defaultValue = "1") Integer productNumber,HttpSession session,Model model){
      /*  Object o = checkUser(session);
        if(null == o){
            return "redirect:/login";
        }
        ForeUserDto user = userService.findUserByUsereName((String) o);
        Integer userId = user.getUserId();

        Product product = productService.getProductById(productId);
        if (product == null) {
            return "redirect:/";
        }
        //用户所有常用地址：若为null:则用户无常用地址，让用户去添加
        List<ForeUserAddressResDto> allUserAddress = userAddressService.getAllUserAddress(userId);
        //封装订单项对象（注意：此时，并未向数据库存入）
        ForeShowOrderItemResDto orderItem = productOrderItemService.showOrderItemWithBuyNow(userId, productId, productNumber);
        List<ForeShowOrderItemResDto> orderItems = new ArrayList<>();
        orderItems.add(orderItem);
        //结算的总金额
        Double orderTotalMoney = orderItem.getProductOrderItem().getProductOrderItemPrice();

        model.addAttribute("allUserAddress",allUserAddress);
        model.addAttribute("orderItems",orderItems);
        model.addAttribute("orderTotalMoney",orderTotalMoney);*/

        logger.info("转到前台--订单建立页");
        return "user/productBuyPage";


    }
    /**
     * 用于购物车结算时，订单的建立（即将该订单的数据存入数据库）
     *数据库中订单数据存放后，--跳转到前台订单建立页(productBuyPage.jsp)
     * @param session
     * @param orderItemList 订单项id的list
     * @return
     */
    @RequestMapping("/order/create/byCart")
    public String goToOrderConfirmPageByCart(@RequestParam(required = false) Integer[] orderItemList,HttpSession session,Model model){
        Object o = checkUser(session);
        if(null == o){
            return "redirect:/login";
        }
        ForeUserDto user = userService.findUserByUsereName((String) o);
        Integer userId = user.getUserId();
        if (orderItemList == null || orderItemList.length == 0) {
            logger.warn("用户订单项数组不存在，回到购物车页");
            return "redirect:/cart";
        }
        for (Integer orderItemId : orderItemList) {
            ProductOrderItem productOrderItem = productOrderItemService.selectOrderItemById(orderItemId);
            if(productOrderItem.getProductOrderId() != null){
                logger.warn("用户订单项不属于购物车，回到购物车页");
                return "redirect:/cart";
            }
            if(!productOrderItem.getUserId().equals(userId)){
                logger.warn("用户订单项与用户不匹配，回到购物车页");
                return "redirect:/cart";
            }
        }
        logger.info("验证通过，获取订单项的详细信息");
        //用户所有常用地址：list的长度为0:则用户无常用地址，让用户去添加
        List<ForeUserAddressResDto> allUserAddress = userAddressService.getAllUserAddress(userId);
        //用户结算的订单项详情
        List<ForeShowOrderItemResDto> orderItems = productOrderItemService.getCalculateCart(orderItemList);
        //结算的总金额
        Double orderTotalMoney = productOrderItemService.calculateOrderItemCartMoney(orderItemList);

        model.addAttribute("allUserAddress",allUserAddress);
        model.addAttribute("orderItems",orderItems);
        model.addAttribute("orderTotalMoney",orderTotalMoney);

        logger.info("转到前台--订单建立页");
        return "user/productBuyPage";

    }


    /**
     * 转到前台订单支付页
     * @param session
     * @param orderCode 订单编码
     * @return
     */
    @RequestMapping(value = "/order/pay/{orderCode}", method = RequestMethod.GET)
    public String goToOrderPayPage(HttpSession session,@PathVariable("orderCode") String orderCode,Model model){
        logger.info("检查用户是否登录");
        Object o = checkUser(session);
        if(null == o){
            return "redirect:/login";
        }
        ForeUserDto user = userService.findUserByUsereName((String) o);
        Integer userId = user.getUserId();

        logger.info("------验证订单信息------");
        ProductOrder order = productOrderService.getProductOrderByOrderCode(orderCode);
        if(order == null){
            logger.warn("订单不存在，返回订单列表页");
            return "redirect:/order/0/10";
        }
        if(order.getProductOrderStatus() != 0){
            logger.warn("订单状态不正确，返回订单列表页");
            return "redirect:/order/0/10";
        }
        if(!order.getUserId().equals(userId)){
            logger.warn("用户与订单信息不一致，返回订单列表页");
            return "redirect:/order/0/10";
        }
        ForePayResDto payPageInfo = productOrderService.getPayPageInfoByOrderCode(orderCode);

        model.addAttribute("payPageInfo",payPageInfo);

        return "user/productPayPage";
    }

    /**
     * 转到前台订单确认收货页
     * （即点击确认收货按钮，需要跳转到确认页，核对信息后，在确认收货）
     * @param model
     * @param session
     * @param orderCode
     * @return
     */
    @RequestMapping(value = "order/confirm/{orderCode}", method = RequestMethod.GET)
    public String goToOrderConfirmPage(Model model,HttpSession session,@PathVariable("orderCode") String orderCode){
        logger.info("检查用户是否登录");
        Object o = checkUser(session);
        if(null == o){
            return "redirect:/login";
        }
        ForeUserDto user = userService.findUserByUsereName((String) o);
        Integer userId = user.getUserId();

        logger.info("------验证订单信息------");
        ProductOrder order = productOrderService.getProductOrderByOrderCode(orderCode);
        if(order == null){
            logger.warn("订单不存在，返回订单列表页");
            return "redirect:/order/0/10";
        }
        if(order.getProductOrderStatus() != 1){
            logger.warn("订单状态不正确，返回订单列表页");
            return "redirect:/order/0/10";
        }
        if(!order.getUserId().equals(userId)){
            logger.warn("用户与订单信息不一致，返回订单列表页");
            return "redirect:/order/0/10";
        }
        ForeConfirmReceiptResDto confirmReceiptInfo = productOrderService.getConfirmReceiptInfo(orderCode);
        model.addAttribute("confirmReceiptInfo",confirmReceiptInfo);

        logger.info("转到前台-订单确认页");
        return "user/orderConfirmPage";

    }


    /**
     * 转到前台--订单支付成功页
     * @param session
     * @param model
     * @param orderCode
     * @return
     */
    @RequestMapping(value = "order/pay/success/{orderCode}", method = RequestMethod.GET)
    public String goToOrderPaySuccessPage(HttpSession session,  Model model,
                                          @PathVariable("orderCode") String orderCode){
        logger.info("检查用户是否登录");
        Object o = checkUser(session);
        if(null == o){
            return "redirect:/login";
        }
        ForeUserDto user = userService.findUserByUsereName((String) o);
        Integer userId = user.getUserId();

        logger.info("------验证订单信息------");
        ProductOrder order = productOrderService.getProductOrderByOrderCode(orderCode);
        if(order == null){
            logger.warn("订单不存在，返回订单列表页");
            return "redirect:/order/0/10";
        }
        if(order.getProductOrderStatus() != 1){
            logger.warn("订单状态不正确，返回订单列表页");
            return "redirect:/order/0/10";
        }
        if(!order.getUserId().equals(userId)){
            logger.warn("用户与订单信息不一致，返回订单列表页");
            return "redirect:/order/0/10";
        }
        ForePaySuccessResDto paySuccessInfo = productOrderService.getPaySuccessInfoByOrderCode(orderCode);
        model.addAttribute("paySuccessInfo",paySuccessInfo);

        logger.info("转到前台-订单支付成功页");
        return "user/productPaySuccessPage";
    }

    /**
     * 转到前台-订单完成页（即确认收货后，跳转的页面）
     * @param session
     * @param model
     * @param orderCode
     * @return
     */
    @RequestMapping(value = "order/success/{orderCode}", method = RequestMethod.GET)
    public String goToOrderSuccessPage( HttpSession session,Model model,
                                       @PathVariable("orderCode") String orderCode){
        logger.info("检查用户是否登录");
        Object o = checkUser(session);
        if(null == o){
            return "redirect:/login";
        }
        ForeUserDto user = userService.findUserByUsereName((String) o);
        Integer userId = user.getUserId();

        logger.info("------验证订单信息------");
        ProductOrder order = productOrderService.getProductOrderByOrderCode(orderCode);
        if(order == null){
            logger.warn("订单不存在，返回订单列表页");
            return "redirect:/order/0/10";
        }
        if(order.getProductOrderStatus() != 3){
            logger.warn("订单状态不正确，返回订单列表页");
            return "redirect:/order/0/10";
        }
        if(!order.getUserId().equals(userId)){
            logger.warn("用户与订单信息不一致，返回订单列表页");
            return "redirect:/order/0/10";
        }
        //orderSuccessInfo.product != null 才显示
        ForeOrderSuceessResDto orderSuccessInfo = productOrderService.getOrderSuccessInfo(orderCode);
        model.addAttribute("orderSuccessInfo",orderSuccessInfo);

        logger.info("转到前台-订单完成页");
        return "user/orderSuccessPage";

    }
    /**
     * 更新订单信息为已支付，待发货-ajax
     * 若更改成功，让前端跳到"/order/pay/success/" + order_code（订单支付成功页）
     *
     * 注：前端需更改：当message值使，弹窗提示message的内容后，在跳转
     * @param session
     * @param orderCode
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/order/pay/{orderCode}",method = RequestMethod.PUT)
    public String orderPay(HttpSession session,@PathVariable("orderCode") String orderCode){
        JSONObject object = new JSONObject();
        logger.info("检查用户是否登录");
        Object o = checkUser(session);
        if(null == o){
            object.put("success", false);
            object.put("url", "/login");
            return object.toJSONString();
        }
        ForeUserDto user = userService.findUserByUsereName((String) o);
        Integer userId = user.getUserId();

        logger.info("------验证订单信息------");
        ProductOrder order = productOrderService.getProductOrderByOrderCode(orderCode);
        if(order == null){
            logger.warn("订单不存在，返回订单列表页");
            object.put("success", false);
            object.put("url", "/order/0/10");
            return object.toJSONString();
        }
        if(order.getProductOrderStatus() != 0){
            logger.warn("订单状态不正确，返回订单列表页");
            object.put("success", false);
            object.put("url", "/order/0/10");
            return object.toJSONString();
        }
        if(!order.getUserId().equals(userId)){
            logger.warn("用户与订单信息不一致，返回订单列表页");
            object.put("success", false);
            object.put("url", "/order/0/10");
            return object.toJSONString();
        }
        //支付后，更改产品和订单相关信息
        productOrderService.payOrder(orderCode);

        object.put("success", true);
        object.put("url", "/order/pay/success/" + orderCode);


        return object.toJSONString();

    }

    /**
     *更新订单信息为已发货，待确认
     * (模拟用于当前端，点击提醒发货时，将状态改为已发货，待确认)
     * @param session
     * @param orderCode
     * @return
     */
    @RequestMapping(value = "order/delivery/{orderCode}", method = RequestMethod.GET)
    public String orderDelivery(HttpSession session, @PathVariable("orderCode") String orderCode){
        logger.info("检查用户是否登录");
        Object o = checkUser(session);
        if(null == o){
            return "redirect:/login";
        }
        ForeUserDto user = userService.findUserByUsereName((String) o);
        Integer userId = user.getUserId();

        logger.info("------验证订单信息------");
        ProductOrder order = productOrderService.getProductOrderByOrderCode(orderCode);
        if(order == null){
            logger.warn("订单不存在，返回订单列表页");
            return "redirect:/order/0/10";
        }
        if(order.getProductOrderStatus() != 1){
            logger.warn("订单状态不正确，返回订单列表页");
            return "redirect:/order/0/10";
        }
        if(!order.getUserId().equals(userId)){
            logger.warn("用户与订单信息不一致，返回订单列表页");
            return "redirect:/order/0/10";
        }
        //将订单信息更改为，已发货
        productOrderService.updateOrderStatus(orderCode,2);

        return "redirect:/order/0/10";
    }

    /**
     * 更新订单信息为交易成功-ajax
     * （即点击确认收货后：为交易成功）
     * @param session
     * @param orderCode
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "order/success/{orderCode}", method = RequestMethod.PUT)
    public String orderSuccess(HttpSession session,@PathVariable("orderCode") String orderCode){
        JSONObject object = new JSONObject();
        logger.info("检查用户是否登录");
        Object o = checkUser(session);
        if(null == o){
            object.put("success", false);
            object.put("url", "/login");
            return object.toJSONString();
        }
        ForeUserDto user = userService.findUserByUsereName((String) o);
        Integer userId = user.getUserId();

        logger.info("------验证订单信息------");
        ProductOrder order = productOrderService.getProductOrderByOrderCode(orderCode);
        if(order == null){
            logger.warn("订单不存在，返回订单列表页");
            object.put("success", false);
            object.put("url", "/order/0/10");
            return object.toJSONString();
        }
        if(order.getProductOrderStatus() != 2){
            logger.warn("订单状态不正确，返回订单列表页");
            object.put("success", false);
            object.put("url", "/order/0/10");
            return object.toJSONString();
        }
        if(!order.getUserId().equals(userId)){
            logger.warn("用户与订单信息不一致，返回订单列表页");
            object.put("success", false);
            object.put("url", "/order/0/10");
            return object.toJSONString();
        }
        logger.info("更新订单信息,为已确认收货（交易成功）");
        productOrderService.updateOrderStatus(orderCode,3);

        object.put("success", true);
        return object.toJSONString();
    }


    /**
     * 更新订单信息为交易关闭-ajax
     * (即未付款的订单，可以取消订单)
     * @param session
     * @param orderCode
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "order/close/{orderCode}", method = RequestMethod.PUT)
    public String orderClose(HttpSession session, @PathVariable("orderCode") String orderCode){
        JSONObject object = new JSONObject();
        logger.info("检查用户是否登录");
        Object o = checkUser(session);
        if(null == o){
            object.put("success", false);
            object.put("url", "/login");
            return object.toJSONString();
        }
        ForeUserDto user = userService.findUserByUsereName((String) o);
        Integer userId = user.getUserId();

        logger.info("------验证订单信息------");
        ProductOrder order = productOrderService.getProductOrderByOrderCode(orderCode);
        if(order == null){
            logger.warn("订单不存在，返回订单列表页");
            object.put("success", false);
            object.put("url", "/order/0/10");
            return object.toJSONString();
        }
        if(order.getProductOrderStatus() != 0){
            logger.warn("订单状态不正确，返回订单列表页");
            object.put("success", false);
            object.put("url", "/order/0/10");
            return object.toJSONString();
        }
        if(!order.getUserId().equals(userId)){
            logger.warn("用户与订单信息不一致，返回订单列表页");
            object.put("success", false);
            object.put("url", "/order/0/10");
            return object.toJSONString();
        }
        logger.info("更新订单信息");
        productOrderService.updateOrderStatus(orderCode,4);

        object.put("success", true);
        return object.toJSONString();
    }

    /**
     * 转到前台--购物车页
     * @param session
     * @return
     */
    @RequestMapping("/cart")
    public String goToCartPage(HttpSession session, Model model){
        Object o = checkUser(session);
        if(null == o){
            return "redirect:/login";
        }
        ForeUserDto user = userService.findUserByUsereName((String) o);
        Integer userId = user.getUserId();

        //用户购物车商品总数
        Integer orderItemNumber = productOrderItemService.getOrderItemCartNumber(userId);
        //用户购物车详情
        List<ForeShowOrderItemResDto> orderItemList = productOrderItemService.selectShowOrderItemCart(userId);
        model.addAttribute("orderItemNumber",orderItemNumber);
        model.addAttribute("orderItemList",orderItemList);

        logger.info("转到前台-购物车页");
        return "user/productBuyCarPage";
    }


    /**
     * 更新购物车订单数量-ajax
     * 购物车点击结算时，先到这个方法，若返回success后，前端在跳转到"/bycart"（购物车订单建立）
     *
     * 前端更改：返回false:前端弹窗提示，返回的message的提示信息
     * @param orderItemMap map（订单项id,数量）
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/orderItem",method = RequestMethod.PUT)
    public String updateOrderItem(@RequestParam String orderItemMap,HttpSession session){
        JSONObject object = new JSONObject();
        Object o = checkUser(session);
        if(null == o){
            return "redirect:/login";
        }
        ForeUserDto user = userService.findUserByUsereName((String) o);
        Integer userId = user.getUserId();
        JSONObject orderItemString = JSON.parseObject(orderItemMap);
        //返回订单项id的set集合
        Set<String> orderItemIDSet = orderItemString.keySet();
        List<ProductOrderItem> list = new ArrayList<>();
        ProductOrderItem temp;
        for (String key : orderItemIDSet) {
            temp = new ProductOrderItem();
            temp.setProductOrderItemId(Integer.valueOf(key));
            temp.setProductOrderItemNumber(Integer.valueOf(orderItemString.getString(key)));
            list.add(temp);
        }

        if(orderItemIDSet.size()>0){
            //初步验证
            for (ProductOrderItem orderItem : list) {
                ProductOrderItem productOrderItem = productOrderItemService.selectOrderItemById(orderItem.getProductOrderItemId());
                if (productOrderItem == null || !productOrderItem.getUserId().equals(userId) ) {
                    logger.warn("订单项为空或用户状态不一致！");
                    object.put("success", false);
                    object.put("message","购物车商品结算异常，请稍候再试！");
                    return object.toJSONString();
                }
                if (productOrderItem.getProductOrderId() != null) {
                    logger.warn("用户订单项不属于购物车，回到购物车页");
                    return "redirect:/cart";
                }

            }
            //修购购物车数量
            String result = productOrderItemService.updateCart(list);
            Object[] orderItemIDArray = orderItemIDSet.toArray();
            if(null == result){
                object.put("success", true);
                object.put("orderItemIDArray", orderItemIDArray);
                return object.toJSONString();
            }else{
                logger.warn("库存不足，请修改购买数量");
                object.put("success", false);
                String message = result + "的库存不足，请修改购买数量！";
                object.put("message",message);
                return object.toJSONString();
            }

        }else{
            logger.warn("无订单项可以处理");
            object.put("success", false);
            object.put("message","没有可以结算的商品，请刷新页面重试！");
            return object.toJSONString();
        }


    }

    /**
     * 创建订单项-添加至购物车-ajax
     * @param productId
     * @param productNumber
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "orderItem/create/{productId}",method =  RequestMethod.POST)
    public String createOrderItem(@PathVariable("productId") Integer productId,
                                  @RequestParam(required = false, defaultValue = "1") Integer productNumber,
                                  HttpSession session){
        JSONObject object = new JSONObject();
        logger.info("检查用户是否登录");
        Object o = checkUser(session);
        if(o == null){
            object.put("url","/login");
            object.put("success",false);
            return object.toJSONString();
        }
        
        ForeUserDto user = userService.findUserByUsereName((String) o);
        Integer userId = user.getUserId();
        ForeAddOrderItemCartReqDto orderItemCartReqDto = new ForeAddOrderItemCartReqDto();
        orderItemCartReqDto.setProductId(productId);
        orderItemCartReqDto.setProductNumber(productNumber);
        orderItemCartReqDto.setUserId(userId);
        Boolean flag = productOrderItemService.addOrderItemWithCart(orderItemCartReqDto);
        if(flag){
            object.put("success",true);
            return object.toJSONString();
        }
        object.put("success",false);
        return object.toJSONString();
    }

    /**
     * 删除订单项-购物车-ajax
     * @param orderItemId 订单项的id
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="/orderItem/{orderItemId}",method = RequestMethod.DELETE)
    public String deleteOrderItem(@PathVariable("orderItemId") Integer orderItemId, HttpSession session){
        JSONObject object = new JSONObject();
        logger.info("检查用户是否登录");
        Object o = checkUser(session);
        if(o == null){
            object.put("url","/login");
            object.put("success",false);
            return object.toJSONString();
        }
        Boolean result = productOrderItemService.deleteByOrderItemId(orderItemId);
        if(result){
            object.put("success",true);
        }else{
            object.put("success",false);
        }

        return object.toString();
    }

    /**
     * 创建新订单-单订单项-ajax(用于立即购买的提交订单)
     * @param session
     * @param userAddressId
     * @param orderItemProductId
     * @param orderItemNumber
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/order", method = RequestMethod.POST)
    public String createOrderByOne(HttpSession session,
                                   @RequestParam Integer userAddressId,
                                   @RequestParam Integer orderItemProductId,
                                   @RequestParam Integer orderItemNumber,
                                   @RequestParam String userMessage
                                   ){
        JSONObject object = new JSONObject();
        logger.info("检查用户是否登录");
        Object o = checkUser(session);
        if(o == null){
            object.put("url","/login");
            object.put("success",false);
            return object.toJSONString();
        }
        Product product = productService.getProductById(orderItemProductId);
        if(product == null){
            object.put("success", false);
            object.put("url", "/");
            return object.toJSONString();
        }

        ForeUserDto user = userService.findUserByUsereName((String) o);
        ForeCreateOrderByOneReqDto createOrderByOneReqDto = new ForeCreateOrderByOneReqDto();
        createOrderByOneReqDto.setUserId(user.getUserId());
        createOrderByOneReqDto.setUserAddressId(userAddressId);
        createOrderByOneReqDto.setProductId(orderItemProductId);
        createOrderByOneReqDto.setProductNum(orderItemNumber);
        createOrderByOneReqDto.setUserMessage(userMessage);

        ForeCreateOrderResDto createOrderResDto = productOrderService.createOrderByOne(createOrderByOneReqDto);


        if(createOrderResDto.getFlag()){
            object.put("success", true);
            object.put("url", "/order/pay/" + createOrderResDto.getProductOrderCode());
        }else{//返回库存不足的产品名
            object.put("success", false);
            object.put("productName",createOrderResDto.getProductName());
        }
        return object.toJSONString();
    }


    /**
     * 创建新订单-多订单项-ajax
     * 用于购物车提交订单
     * @param session
     * @param userAddressId
     * @param orderItemJSON 存放订单项id以及对应的买家留言
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/order/list",method = RequestMethod.POST)
    public String createOrderByList(HttpSession session,
                                    @RequestParam Integer userAddressId,
                                    @RequestParam String orderItemJSON){
        JSONObject object = new JSONObject();
        Object o = checkUser(session);
        if(o == null){
            object.put("url","/login");
            object.put("success",false);
            return object.toJSONString();
        }
        ForeUserDto user = userService.findUserByUsereName((String) o);
        JSONObject orderItemMap = JSONObject.parseObject(orderItemJSON);
        Set<String> orderItemIds = orderItemMap.keySet();

        ForeCreateOrderByListReqDto createOrderByListReqDto = new ForeCreateOrderByListReqDto();
        createOrderByListReqDto.setUserId(user.getUserId());
        List<ForeCreateOrderByListSimpleReqDto> list = new ArrayList<>();
        ForeCreateOrderByListSimpleReqDto temp;
        if(orderItemIds.size()>0){
            for (String orderItemId : orderItemIds) {
                ProductOrderItem productOrderItem = productOrderItemService.selectOrderItemById(Integer.parseInt(orderItemId));
                if(productOrderItem == null || productOrderItem.getUserId().equals(user.getUserId())){
                    logger.warn("订单项为空或用户状态不一致！");
                    object.put("success", false);
                    object.put("url", "/cart");
                    return object.toJSONString();
                }
                if(productOrderItem.getProductOrderId() != null){
                    logger.warn("用户订单项不属于购物车，回到购物车页");
                    object.put("success", false);
                    object.put("url", "/cart");
                    return object.toJSONString();
                }
                temp= new ForeCreateOrderByListSimpleReqDto();
                temp.setOrderItemId(Integer.parseInt(orderItemId));
                temp.setUserMessage(orderItemMap.getString(orderItemId));
                list.add(temp);
            }
        } else {
            object.put("success", false);
            object.put("url", "/cart");
            return object.toJSONString();
        }

        //生成订单
        ForeCreateOrderResDto createOrderResDto = productOrderService.createOrderByList(createOrderByListReqDto);
        if(createOrderResDto.getFlag()){
            object.put("success", true);
            object.put("url", "/order/pay/" + createOrderResDto.getProductOrderCode());
        }else{//返回库存不足的产品名
            object.put("success", false);
            object.put("productName",createOrderResDto.getProductName());
        }



        return object.toJSONString();

    }
}
