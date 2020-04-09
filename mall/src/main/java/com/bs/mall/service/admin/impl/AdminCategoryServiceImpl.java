package com.bs.mall.service.admin.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bs.mall.dao.CategoryMapper;
import com.bs.mall.dao.LastIDMapper;
import com.bs.mall.dao.PropertyMapper;
import com.bs.mall.entity.Category;
import com.bs.mall.entity.Property;
import com.bs.mall.service.admin.BaseService;
import com.bs.mall.service.admin.IAdminCategoryService;
import com.bs.mall.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * author:xs
 * date:2020/4/5 14:13
 * description:类别Service
 */
@Service("adminCategoryService")
public class AdminCategoryServiceImpl extends BaseService implements IAdminCategoryService {
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private LastIDMapper lastIDMapper;
    @Autowired
    private PropertyMapper propertyMapper;

    //转到后台管理-产品类型页-ajax
    @Override
    public String goToPage(HttpSession session, Map<String, Object> map) {
        logger.info("获取前10条产品类型列表");
        PageUtil pageUtil = new PageUtil(0, 10);
        List<Category> categoryList=categoryMapper.select(null, pageUtil);
        map.put("categoryList", categoryList);
        logger.info("获取产品类型总数量");
        Integer categoryCount=categoryMapper.selectTotal(null);
        map.put("categoryCount", categoryCount);
        logger.info("获取分页信息");
        pageUtil.setTotal(categoryCount);
        map.put("pageUtil", pageUtil);

        logger.info("转到后台管理-分类页-ajax方式");
        return "admin/categoryManagePage";
    }

    //转到后台管理-产品类型详情页-ajax
    @Transactional
    @Override
    public String goToDetailsPage(HttpSession session, Map<String, Object> map, Integer cid) {
        logger.info("获取category_id为{}的分类信息", cid);
        Category category=categoryMapper.selectOne(cid);
        logger.info("获取分类子信息-属性列表");
        Property property=new Property();
        property.setPropertyCategory(category);
        category.setPropertyList(propertyMapper.select(property, null));
        map.put("category", category);
        logger.info("转到后台管理-分类详情页-ajax方式");

        return "admin/include/categoryDetails";
    }

    //转到后台管理-产品类型添加页-ajax
    @Override
    public String goToAddPage(HttpSession session, Map<String, Object> map) {
        logger.info("转到后台管理-分类添加页-ajax方式");
        return "admin/include/categoryDetails";
    }

    //添加产品类型信息-ajax
    @Transactional
    @Override
    public String addCategory(String category_name, String category_image_src) {
        JSONObject jsonObject = new JSONObject();//作返回值
        logger.info("整合分类信息");
        Category category=new Category();
        category.setCategoryName(category_name);
        category.setCategoryImageSrc(category_image_src.substring(category_image_src.lastIndexOf("/") + 1));
        logger.info("添加分类信息");
        boolean bool=categoryMapper.insertOne(category)>0;
        if (bool){
            Integer category_id=lastIDMapper.selectLastID();
            logger.info("添加成功！,新增分类的ID值为：{}", category_id);
            jsonObject.put("success", true);
            jsonObject.put("category_id", category_id);
        }else {
            jsonObject.put("success", false);
            logger.warn("添加失败！事务回滚");
            throw new RuntimeException();
        }

        return jsonObject.toJSONString();
    }

    //更新产品类型信息-ajax
    @Transactional
    @Override
    public String updateCategory(String category_name, String category_image_src, Integer category_id) {
        JSONObject jsonObject = new JSONObject();
        logger.info("整合分类信息");
        Category category=new Category();
        category.setCategoryName(category_name);
        category.setCategoryId(category_id);
        category.setCategoryImageSrc(category_image_src.substring(category_image_src.lastIndexOf("/") + 1));
        logger.info("更新分类信息，分类ID值为：{}", category_id);
        boolean bool=categoryMapper.updateOne(category)>0;
        if (bool){
            logger.info("更新成功！");
            jsonObject.put("success", true);
            jsonObject.put("category_id", category_id);
        }else {
            jsonObject.put("success", false);
            logger.info("更新失败！事务回滚");
            throw new RuntimeException();
        }

        return jsonObject.toJSONString();
    }

    //按条件查询产品类型-ajax
    @Override
    public String getCategoryBySearch(String category_name, Integer index, Integer count) throws UnsupportedEncodingException {
        //移除不必要条件
        if (category_name != null) {
            //如果为非空字符串则解决中文乱码：URLDecoder.decode(String,"UTF-8");
            category_name = "".equals(category_name) ? null : URLDecoder.decode(category_name, "UTF-8");
        }

        JSONObject object = new JSONObject();//返回值
        logger.info("按条件获取第{}页的{}条分类", index + 1, count);
        PageUtil pageUtil = new PageUtil(index, count);
        List<Category> categoryList=categoryMapper.select(category_name, pageUtil);
        object.put("categoryList", JSONArray.parseArray(JSON.toJSONString(categoryList)));
        logger.info("按条件获取分类总数量");
        Integer categoryCount=categoryMapper.selectTotal(category_name);
        object.put("categoryCount", categoryCount);
        logger.info("获取分页信息");
        pageUtil.setTotal(categoryCount);
        object.put("totalPage", pageUtil.getTotalPage());
        object.put("pageUtil", pageUtil);

        return object.toJSONString();
    }

    // 上传产品类型图片-ajax
    @Override
    public String uploadCategoryImage(MultipartFile file, HttpSession session) {
        String originalFileName = file.getOriginalFilename();
        logger.info("获取图片原始文件名:  {}", originalFileName);
        String extension = originalFileName.substring(originalFileName.lastIndexOf('.'));
        String fileName = UUID.randomUUID() + extension;
        String filePath = session.getServletContext().getRealPath("/") + "res/image/item/categoryPicture/" + fileName;

        logger.info("文件上传路径：{}", filePath);
        JSONObject object = new JSONObject();
        try {
            logger.info("文件上传中...");
            file.transferTo(new File(filePath));
            logger.info("文件上传完成");
            object.put("success", true);
            object.put("fileName", fileName);
        } catch (IOException e) {
            logger.warn("文件上传失败!");
            e.printStackTrace();
            object.put("success", false);
        }

        return object.toJSONString();
    }
}
