package com.bs.mall.controller.fore;

import com.bs.mall.controller.BaseController;
import com.bs.mall.dao.pojo.Product;
import com.bs.mall.dto.req.ForeProductGuessReqDto;
import com.bs.mall.dto.req.ForeReviewReqDto;
import com.bs.mall.dto.res.*;
import com.bs.mall.service.fore.IProductService;
import com.bs.mall.service.fore.IUserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Map;

/**
 * 产品详情页的controller
 */
@Controller
public class ForeProductDetailsController extends BaseController {


    @Autowired
    private IProductService productService;

    /**
     * 转到产品详情页
     * @param pid 产品id
     * @param model
     * @return
     */
    @RequestMapping("/product/{pid}")
    public String goToProductDetailPage( @PathVariable("pid") String pid,Model model) {
        Integer productId = Integer.parseInt(pid);

        Product product = productService.getProductById(productId);
        if(null == product){
            //重定向
            return "redirect:/404";
        }

        ForeProductDetailsResDto productDetails = productService.getProductDetails(productId);
        //底层会存储到request域对象中
        //一系列基本信息
        model.addAttribute("productDetails",productDetails);
        //属性列表
        model.addAttribute("propertyList",productDetails.getPropertyValue());
        //猜你喜欢
        model.addAttribute("guessLikeList",productDetails.getProductGuess());
        //评论
        model.addAttribute("reviewRes",productDetails.getReviewResDto());

        logger.info("转发到产品详情页");
        return "user/productDetailsPage";
    }


    /**
     *ajax异步请求：根据产品id，返回评论
     * @param pid 产品id
     * @param pageNum 当前页
     * @param pageSize 每页大小
     * @return
     */
  /*  @Deprecated
    @ResponseBody
    @RequestMapping(value = "/product/review/{pid}",method = RequestMethod.GET)
    public ForeReviewResDto getProductReiewList(@PathVariable("pid")String pid,
                                                @RequestParam Integer pageNum,
                                                @RequestParam Integer pageSize){
        Integer productId = Integer.parseInt(pid);

        ForeReviewReqDto reviewReqDto = new ForeReviewReqDto();
        reviewReqDto.setProductId(productId);
        reviewReqDto.setPageSize(pageSize);
        reviewReqDto.setPageNum(pageNum);
        ForeReviewResDto reviewList = productService.getReview(reviewReqDto);

        return reviewList;
    }
*/
    /**
     * ajax异步请求：根据id返回产品属性及其值
     * @param pid
     * @return
     */
    @Deprecated
    @ResponseBody
    @RequestMapping("/property/{pid}")
    public ForeProductPropertyResDto getProductProperty(@PathVariable("pid")String pid){
        Integer productId = Integer.parseInt(pid);

        ForeProductPropertyResDto propertyList = productService.getProductProperty(productId);
        return propertyList;
    }

    /**
     *
     * 根据当前产品的categoryId：猜你喜欢
     *  @param cid 产品id
     *  @param pageNum 当前页
     *  @param pageSize 每页大小
     * @return
     */
    @ResponseBody
    @RequestMapping("/guess/{cid}")
    public PageInfo getProductGuessLike(@PathVariable("cid")String cid,
                                                      @RequestParam Integer pageNum,
                                                      @RequestParam Integer pageSize){
        Integer categoryId = Integer.parseInt(cid);
        ForeProductGuessReqDto productGuessReqDto = new ForeProductGuessReqDto();
        productGuessReqDto.setCategoryId(categoryId);
        productGuessReqDto.setPageNum(pageNum);
        productGuessReqDto.setPageSize(pageSize);
        PageInfo<ForeProductGuessResDto> guessLikeList = productService.getProductGuessLike(productGuessReqDto);
        return guessLikeList;

    }


}
