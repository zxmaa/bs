package com.bs.mall.controller.Admin;

import com.bs.mall.service.admin.IAdminCategoryService;
import com.bs.mall.service.admin.impl.AdminCategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * author:xs
 * date:2020/4/5 14:02
 * description:类别控制器
 */
@Controller
public class AdminCategoryController {
    @Autowired
    IAdminCategoryService adminCategoryService;

    //转到后台管理-产品类型页-ajax
    @RequestMapping(value = "admin/category", method = RequestMethod.GET)
    public String goToPage(HttpSession session, Map<String, Object> map) {
        return adminCategoryService.goToPage(session, map);
    }

    //转到后台管理-产品类型详情页-ajax
    @RequestMapping(value = "admin/category/{cid}", method = RequestMethod.GET)
    public String goToDetailsPage(HttpSession session, Map<String, Object> map, @PathVariable Integer cid/* 分类ID */) {
        return adminCategoryService.goToPage(session, map);
    }

    //转到后台管理-产品类型添加页-ajax
    @RequestMapping(value = "admin/category/new", method = RequestMethod.GET)
    public String goToAddPage(HttpSession session, Map<String, Object> map) {
        return adminCategoryService.goToAddPage(session, map);
    }

    //添加产品类型信息-ajax
    @ResponseBody
    @RequestMapping(value = "admin/category", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    public String addCategory(@RequestParam String category_name/* 分类名称 */,
                              @RequestParam String category_image_src/* 分类图片路径 */) {

        return adminCategoryService.addCategory(category_name, category_image_src);
    }

    //更新产品类型信息-ajax
    @ResponseBody
    @RequestMapping(value = "admin/category/{category_id}", method = RequestMethod.PUT, produces = "application/json;charset=utf-8")
    public String updateCategory(@RequestParam String category_name/* 分类名称 */,
                                 @RequestParam String category_image_src/* 分类图片路径 */,
                                 @PathVariable("category_id") Integer category_id/* 分类ID */) {

        return adminCategoryService.updateCategory(category_name, category_image_src, category_id);
    }

    //按条件查询产品类型-ajax
    @ResponseBody
    @RequestMapping(value = "admin/category/{index}/{count}", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    public String getCategoryBySearch(@RequestParam(required = false) String category_name/* 分类名称 */,
                                      @PathVariable Integer index/* 页数 */,
                                      @PathVariable Integer count/* 行数 */) throws UnsupportedEncodingException {
        return adminCategoryService.getCategoryBySearch(category_name, index, count);
    }

    @ResponseBody
    @RequestMapping(value = "admin/uploadCategoryImage", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    public String uploadCategoryImage(@RequestParam MultipartFile file, HttpSession session) {
        return adminCategoryService.uploadCategoryImage(file, session);
    }
}
