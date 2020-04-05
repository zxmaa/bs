package com.bs.mall.controller.Admin;

import com.bs.mall.service.admin.impl.AdminProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * author:xs
 * date:2020/3/21 21:24
 * description:产品控制器
 */
@Controller
public class ProductController {
    @Autowired
    AdminProductServiceImpl productService;

    @RequestMapping(value = "admin/product",method = RequestMethod.GET)
    public String goToPage(HttpSession session, Map<String, Object> map) {
        return productService.goToPage(session, map);
    }

    //转到后台管理-产品详情页-ajax
    @RequestMapping(value="admin/product/{pid}",method = RequestMethod.GET)
    public String goToDetailsPage(HttpSession session, Map<String, Object> map, @PathVariable Integer pid/* 产品ID */) {
        return productService.goToDetailsPage(session, map, pid);
    }

    //转到后台管理-产品添加页-ajax
    @RequestMapping(value = "admin/product/new",method = RequestMethod.GET)
    public String goToAddPage(HttpSession session,Map<String, Object> map){
        return productService.goToAddPage(session, map);
    }

    //添加产品信息-ajax.
    @ResponseBody
    @RequestMapping(value = "admin/product", method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    public String addProduct(@RequestParam String product_name/* 产品名称 */,
                             @RequestParam String product_title/* 产品标题 */,
                             @RequestParam Integer product_category_id/* 产品类型ID */,
                             @RequestParam Double product_sale_price/* 产品促销价 */,
                             @RequestParam Double product_price/* 产品原价 */,
                             @RequestParam Byte product_isEnabled/* 产品状态 */,
                             @RequestParam String propertyJson/* 产品属性JSON */,
                             @RequestParam(required = false) String[] productSingleImageList/*产品预览图片名称数组*/,
                             @RequestParam(required = false) String[] productDetailsImageList/*产品详情图片名称数组*/) {
        return productService.addProduct(product_name, product_title, product_category_id, product_sale_price, product_price,
                product_isEnabled, propertyJson, productSingleImageList, productDetailsImageList);
    }

    //更新产品信息-ajax
    @ResponseBody
    @RequestMapping(value = "admin/product/{product_id}", method = RequestMethod.PUT, produces = "application/json;charset=utf-8")
    public String updateProduct(@RequestParam String product_name/* 产品名称 */,
                                @RequestParam String product_title/* 产品标题 */,
                                @RequestParam Integer product_category_id/* 产品类型ID */,
                                @RequestParam Double product_sale_price/* 产品促销价 */,
                                @RequestParam Double product_price/* 产品原价 */,
                                @RequestParam Byte product_isEnabled/* 产品状态 */,
                                @RequestParam String propertyAddJson/* 产品添加属性JSON */,
                                @RequestParam String propertyUpdateJson/* 产品更新属性JSON */,
                                @RequestParam(required = false) Integer[] propertyDeleteList/* 产品删除属性ID数组 */,
                                @RequestParam(required = false) String[] productSingleImageList/*产品预览图片名称数组*/,
                                @RequestParam(required = false) String[] productDetailsImageList/*产品详情图片名称数组*/,
                                @PathVariable("product_id") Integer product_id/* 产品ID */){

        return productService.updateProduct(product_name, product_title, product_category_id, product_sale_price, product_price,
        product_isEnabled, propertyAddJson, propertyUpdateJson,propertyDeleteList, productSingleImageList,productDetailsImageList, product_id);
    }

    //按条件查询产品-ajax
    @ResponseBody
    @RequestMapping(value = "admin/product/{index}/{count}", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    public String getProductBySearch(@RequestParam(required = false) String product_name/* 产品名称 */,
                                     @RequestParam(required = false) Integer category_id/* 产品类型ID */,
                                     @RequestParam(required = false) Double product_sale_price/* 产品促销价 */,
                                     @RequestParam(required = false) Double product_price/* 产品原价 */,
                                     @RequestParam(required = false) Byte[] product_isEnabled_array/* 产品状态数组 */,
                                     @RequestParam(required = false) String orderBy/* 排序字段 */,
                                     @RequestParam(required = false,defaultValue = "true") Boolean isDesc/* 是否倒序 */,
                                     @PathVariable Integer index/* 页数 */,
                                     @PathVariable Integer count/* 行数 */) throws UnsupportedEncodingException {

        return productService.getProductBySearch(product_name, category_id, product_sale_price, product_price, product_isEnabled_array,orderBy, isDesc, index, count);
    }

    //按类型ID查询属性-ajax
    @ResponseBody
    @RequestMapping(value = "admin/property/type/{property_category_id}", method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    public String getPropertyByCategoryId(@PathVariable Integer property_category_id/* 属性所属类型ID*/){

        return productService.getPropertyByCategoryId(property_category_id);
    }

    //按ID删除产品图片并返回最新结果-ajax
    @ResponseBody
    @RequestMapping(value = "admin/productImage/{productImage_id}",method = RequestMethod.DELETE,produces = "application/json;charset=utf-8")
    public String deleteProductImageById(@PathVariable Integer productImage_id/* 产品图片ID */){

        return productService.deleteProductImageById(productImage_id);
    }

    //上传产品图片-ajax
    @ResponseBody
    @RequestMapping(value = "admin/uploadProductImage", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    public String uploadProductImage(@RequestParam MultipartFile file, @RequestParam String imageType, HttpSession session) {

        return productService.uploadProductImage(file, imageType, session);
    }

}
