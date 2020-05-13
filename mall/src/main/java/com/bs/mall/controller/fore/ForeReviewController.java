package com.bs.mall.controller.fore;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bs.mall.controller.BaseController;
import com.bs.mall.dao.pojo.ProductOrder;
import com.bs.mall.dao.pojo.ProductOrderItem;
import com.bs.mall.dto.ForeUserDto;
import com.bs.mall.dto.req.ForeAddReviewReqDto;
import com.bs.mall.dto.req.ForeReviewReqDto;
import com.bs.mall.dto.res.ForeWriteReviewResDto;
import com.bs.mall.service.fore.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class ForeReviewController extends BaseController {
    @Autowired
    private IUserService userService;
    @Autowired
    private IProductOrderItemService productOrderItemService;
    @Autowired
    private IProductOrderService productOrderService;
    @Autowired
    private IReviewService reviewService;

    /**
     * 转到前台--评论添加页
     * @param model
     * @param orderItemId
     * @return
     */
    @RequestMapping(value = "/review/{orderItemId}",method = RequestMethod.GET)
    public String goToReviewPage(HttpSession session,Model model, @PathVariable("orderItemId") Integer orderItemId){
        logger.info("检查用户是否登录");
        Object o = checkUser(session);
        if(null == o){
            return "redirect:/login";
        }
        ForeUserDto user = userService.findUserByUsereName((String) o);
        Integer userId = user.getUserId();

        logger.info("------获取订单项信息------");
        ProductOrderItem orderItem = productOrderItemService.selectOrderItemById(orderItemId);
        if(orderItem == null){
            logger.warn("订单项不存在，返回订单页");
            return "redirect:/order/1/10";
        }
        if(!orderItem.getUserId().equals(userId)){
            logger.warn("订单项与用户不匹配，返回订单页");
            return "redirect:/order/1/10";
        }
        if(orderItem.getProductOrderId() == null){
            logger.warn("订单项状态有误，返回订单页");
            return "redirect:/order/1/10";
        }
        ProductOrder productOrder = productOrderService.getProductOrderById(orderItem.getProductOrderId());
        if(productOrder == null || productOrder.getProductOrderStatus() !=3){
            logger.warn("订单项状态有误，返回订单页");
            return "redirect:/order/1/10";
        }
        if(reviewService.getTotalByOrderItemId(orderItemId)>0){
            logger.warn("订单项所属商品已被评价，返回订单页");
            return "redirect:/order/1/10";
        }
        ForeWriteReviewResDto reviewPageInfo = productOrderService.getWriteReviewInfo(orderItemId);
        model.addAttribute("reviewPageInfo",reviewPageInfo);

        logger.info("转到前台-评论添加页");
        return "user/addReview";
    }

    /**
     * 添加一条评论
     * @param session
     * @param model
     * @param orderItemId
     * @param reviewConent
     * @return
     */
    @RequestMapping(value = "/review",method = RequestMethod.POST)
    public String addReview(HttpSession session, Model model,
                            @RequestParam Integer orderItemId,
                            @RequestParam String reviewConent){
        logger.info("检查用户是否登录");
        Object o = checkUser(session);
        if(null == o){
            return "redirect:/login";
        }
        ForeUserDto user = userService.findUserByUsereName((String) o);
        Integer userId = user.getUserId();

        logger.info("------获取订单项信息------");
        ProductOrderItem orderItem = productOrderItemService.selectOrderItemById(orderItemId);
        if(orderItem == null){
            logger.warn("订单项不存在，返回订单页");
            return "redirect:/order/1/10";
        }
        if(!orderItem.getUserId().equals(userId)){
            logger.warn("订单项与用户不匹配，返回订单页");
            return "redirect:/order/1/10";
        }
        if(orderItem.getProductOrderId() == null){
            logger.warn("订单项状态有误，返回订单页");
            return "redirect:/order/1/10";
        }
        ProductOrder productOrder = productOrderService.getProductOrderById(orderItem.getProductOrderId());
        if(productOrder == null || productOrder.getProductOrderStatus() !=3){
            logger.warn("订单项状态有误，返回订单页");
            return "redirect:/order/1/10";
        }
        if(reviewService.getTotalByOrderItemId(orderItemId)>0){
            logger.warn("订单项所属商品已被评价，返回订单页");
            return "redirect:/order/1/10";
        }
        ForeAddReviewReqDto foreAddReviewReqDto = new ForeAddReviewReqDto();
        foreAddReviewReqDto.setOrderItemId(orderItemId);
        foreAddReviewReqDto.setReviewContent(reviewConent);

        reviewService.addReview(foreAddReviewReqDto);
        return "redirect:/product/" + orderItem.getProductId();
    }


}
