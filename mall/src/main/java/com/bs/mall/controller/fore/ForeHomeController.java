package com.bs.mall.controller.fore;

import com.alibaba.fastjson.JSONObject;
import com.bs.mall.controller.BaseController;
import com.bs.mall.dao.pojo.Category;
import com.bs.mall.dao.pojo.Product;
import com.bs.mall.dto.ForeProductSimpleDto;
import com.bs.mall.dto.res.ForeCategoryAndProductResDto;
import com.bs.mall.service.fore.ICategoryService;
import com.bs.mall.service.fore.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
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

    /**
     * 转到前端的错误页
     * @return
     */
    public String goToErrorPage(){
        return  "user/errorPage";
}

    /**
     * ajax：用于主页的导航页页：选中分页显示对应的信息
     * @param categoryId
     * @return
     */
    @ResponseBody
    @RequestMapping("/product/nav/{categoryId}")
    public String getProductTitleByNav(@PathVariable("categoryId")Integer categoryId){
        logger.info("主页导航页");
        JSONObject object = new JSONObject();
        if (categoryId == null) {
            object.put("success", false);
            return object.toJSONString();
        }
        List<ForeProductSimpleDto> productList = categoryService.getProductByCategoryId(categoryId);

        List<List<ForeProductSimpleDto>> complexProductList = new ArrayList<>();
        List<ForeProductSimpleDto> products = new ArrayList<>();
        for (int i = 0; i < productList.size(); i++) {
            //如果临时集合中产品数达到5个，加入到产品二维集合中，并重新实例化临时集合
            if (i % 5 == 0) {
                complexProductList.add(products);
                products = new ArrayList<>(5);
            }
            products.add(productList.get(i));
        }
        complexProductList.add(products);


        object.put("success", true);
        object.put("complexProductList", complexProductList);
        return object.toJSONString();
    }



}
