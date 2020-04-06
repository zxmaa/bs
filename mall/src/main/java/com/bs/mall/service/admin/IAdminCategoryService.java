package com.bs.mall.service.admin;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.Map;

public interface IAdminCategoryService {
    String goToPage(HttpSession session, Map<String, Object> map);

    String goToDetailsPage(HttpSession session, Map<String, Object> map, Integer cid/* 分类ID */);

    String goToAddPage(HttpSession session, Map<String, Object> map);

    String addCategory(String category_name/* 分类名称 */,String category_image_src/* 分类图片路径 */);

    String updateCategory(String category_name/* 分类名称 */,String category_image_src/* 分类图片路径 */,Integer category_id/* 分类ID */);

    String getCategoryBySearch(String category_name/* 分类名称 */,Integer index/* 页数 */,Integer count/* 行数 */) throws UnsupportedEncodingException;

    String uploadCategoryImage(MultipartFile file, HttpSession session);
}
