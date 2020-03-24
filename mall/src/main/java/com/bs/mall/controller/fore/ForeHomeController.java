package com.bs.mall.controller.fore;

import com.bs.mall.controller.BaseController;
import com.bs.mall.dao.pojo.Category;
import com.bs.mall.dao.pojo.Product;
import com.bs.mall.dto.res.ForeCategoryAndProductResDto;
import com.bs.mall.service.fore.ICategoryService;
import com.bs.mall.service.fore.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 主页的controller
 */
@Controller
public class ForeHomeController extends BaseController {
    @Autowired
    private IProductService productService;
    @Autowired
    private ICategoryService categoryService;

    /**
     * 转发到前台主页
     * @param model
     * @return
     */
    @RequestMapping("/")
    public String goToHomePage(Model model){
        //所有的分类
        List<Category> categoryList = categoryService.getAllCategory();
        //产品展示：用于每个分类下显示的产品
        List<ForeCategoryAndProductResDto> productList = categoryService.getCategoryAndProduct();
        //促销产品：用于中间滑动窗口
        List<Product> promotionProductList = productService.getPromotionProduct();
        model.addAttribute("categoryList",categoryList);
        model.addAttribute("productList",productList);
        model.addAttribute("promotionProductList",promotionProductList);
        //转发到主页
        return "user/homePage";
    }

}
