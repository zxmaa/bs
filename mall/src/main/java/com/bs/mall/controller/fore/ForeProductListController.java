package com.bs.mall.controller.fore;

import com.bs.mall.controller.BaseController;
import com.bs.mall.dao.pojo.Category;
import com.bs.mall.dao.pojo.Product;
import com.bs.mall.dto.req.ForeQueryProductListReqDto;
import com.bs.mall.service.fore.ICategoryService;
import com.bs.mall.service.fore.IProductService;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public class ForeProductListController  extends BaseController {
    @Autowired
    private IProductService productService;

    @Autowired
    private ICategoryService categoryService;

    /**
     * 转到产品搜索列表页
     * 根据类型或产品名称进行模糊搜索，得到一系列的产品
     * @param model
     * @return
     */
    @RequestMapping("/product")
    public String goToProductListPage(Model model, Integer categoryId, String productName){

        if(null == categoryId && StringUtils.isBlank(productName)){
            logger.info("重定向到首页");
            return "redirect:/";
        }
        //关键词数组
        String[] productNameSplit = null;
        //存搜索框传入的值
        String searchValue = null;
        //存categoryId的值
        Integer searchType = null;

        //若是根据category查出列表
        if(categoryId != null){
            searchType = categoryId;
        }
        //若传入的是搜索框的内容
        if(!StringUtils.isBlank(productName)){
            //以空格分隔
            productNameSplit = productName.split("\\s+");
            searchValue = productName;
        }
        ForeQueryProductListReqDto queryProductListReqDto = new ForeQueryProductListReqDto();
        queryProductListReqDto.setCategoryId(categoryId);
        queryProductListReqDto.setSearchValueSplit(productNameSplit);
        PageInfo<Product> productPageInfo = productService.queryProductList(queryProductListReqDto);

        List<Category> allCategory = categoryService.getAllCategory();

        model.addAttribute("productPageInfo",productPageInfo);
        model.addAttribute("allCategory",allCategory);
        model.addAttribute("searchValue",searchValue);
        model.addAttribute("searchType",searchType);

        logger.info("转到前台产品搜索列表");
        return "user/productListPage";

    }


    /**
     * 按排序字段进行分页查询
     * @param model
     * @param pageNumber
     * @param pageSize
     * @param categoryId
     * @param productName
     * @param isDesc
     * @return
     */
    @RequestMapping(value = "product/{pageNumber}/{pageSize}")
    public String searchProduct(Model model,
                                @PathVariable("pageNumber")Integer pageNumber,
                                @PathVariable("pageSize")Integer pageSize,
                                Integer categoryId,String productName,
                                String orderBy,
                                @RequestParam(required = false, defaultValue = "true") Boolean isDesc){
        //关键词数组
        String[] productNameSplit = null;
        //存搜索框传入的值
        String searchValue = null;
        //存categoryId的值
        Integer searchType = null;

        //若是根据category查出列表
        if(categoryId != null){
            searchType = categoryId;
        }
        //若传入的是搜索框的内容
        if(!StringUtils.isBlank(productName)){
            //以空格分隔
            productNameSplit = productName.split("\\s+");
            searchValue = productName;
        }
        ForeQueryProductListReqDto queryProductListReqDto = new ForeQueryProductListReqDto();
        queryProductListReqDto.setCategoryId(categoryId);
        queryProductListReqDto.setSearchValueSplit(productNameSplit);
        queryProductListReqDto.setIsDesc(isDesc);
        queryProductListReqDto.setOrderBy(orderBy);
        queryProductListReqDto.setPageNum(pageNumber);
        queryProductListReqDto.setPageSize(pageSize);
        PageInfo<Product> productPageInfo = productService.queryProductList(queryProductListReqDto);

        List<Category> allCategory = categoryService.getAllCategory();

        model.addAttribute("productPageInfo",productPageInfo);
        model.addAttribute("allCategory",allCategory);
        model.addAttribute("searchValue",searchValue);
        model.addAttribute("searchType",searchType);
        model.addAttribute("orderBy", orderBy);
        model.addAttribute("isDesc", isDesc);

        logger.info("转到前台产品搜索列表");
        return "user/productListPage";
    }



}
