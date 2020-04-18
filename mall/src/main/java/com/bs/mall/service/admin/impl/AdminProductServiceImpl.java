package com.bs.mall.service.admin.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bs.mall.dao.*;
import com.bs.mall.dao.pojo.*;
import com.bs.mall.service.admin.BaseService;
import com.bs.mall.util.OrderUtil;
import com.bs.mall.util.PageUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.*;

/**
 * author:xs
 * date:2020/3/21 21:56
 * description:产品展示service
 */
@Service("adminProductServiceImpl")
public class AdminProductServiceImpl extends BaseService{
    @Autowired
    CategoryMapper categoryMapper;
    @Autowired
    ProductMapper productMapper;
    @Autowired
    ProductImageMapper productImageMapper;
    @Autowired
    PropertyMapper propertyMapper;
    @Autowired
    PropertyValueMapper propertyValueMapper;
    @Autowired
    LastIDMapper lastIDMapper;

    public String goToPage(HttpSession session, Map<String, Object> map){
        logger.info("获取产品分类列表");
        QueryWrapper<Category> wrapper=new QueryWrapper<>();
        List<Category> categoryList=categoryMapper.selectList(wrapper);
        map.put("categoryList", categoryList);
        logger.info("获取前10条产品列表");
        PageUtil pageUtil = new PageUtil(0, 10);
        List<com.bs.mall.entity.Product> productList=productMapper.select(null, null,null, pageUtil);
        map.put("productList", productList);
        logger.info("获取产品总数量");
        Integer productCount=productMapper.selectTotal(null, null);
        map.put("productCount", productCount);
        logger.info("获取分页信息");
        pageUtil.setTotal(productCount);
        map.put("pageUtil", pageUtil);

        logger.info("转到后台管理-产品页-ajax方式");
        return "admin/productManagePage";
    }

    //转到后台管理-产品详情页-ajax
    public String goToDetailsPage(HttpSession session, Map<String, Object> map, Integer pid/* 产品ID */){
        logger.info("获取product_id为{}的产品信息",pid);
        com.bs.mall.entity.Product product=productMapper.selectOne(pid);
        Integer product_id =product.getProductId();

        logger.info("获取单个产品预览图片信息");
        List<com.bs.mall.entity.ProductImage> singleProductImageList=productImageMapper.select(product_id, (byte) 0, null);
        product.setSingleProductImageList(singleProductImageList);

        logger.info("获取产品详情图片信息");
        List<com.bs.mall.entity.ProductImage> detailsProductImageList=productImageMapper.select(product_id, (byte) 1, null);
        product.setDetailProductImageList(detailsProductImageList);
        map.put("product", product);

        logger.info("获取产品详情-属性值信息");
        com.bs.mall.entity.PropertyValue propertyValue=new com.bs.mall.entity.PropertyValue();
        propertyValue.setPropertyValueProduct(product);
        List<com.bs.mall.entity.PropertyValue> propertyValueList=propertyValueMapper.select(propertyValue, null);

        logger.info("获取产品详情-分类信息对应的属性列表");
        com.bs.mall.entity.Property property=new com.bs.mall.entity.Property();
        property.setPropertyCategory(product.getProductCategory());
        List<com.bs.mall.entity.Property> propertyList=propertyMapper.select(property, null);

        logger.info("属性列表和属性值列表信息合并");
        for (com.bs.mall.entity.Property property1 : propertyList){
            for (com.bs.mall.entity.PropertyValue propertyValue1 : propertyValueList){
                if (property1.getPropertyId().equals(propertyValue1.getPropertyValueProperty().getPropertyId())){
                    List<com.bs.mall.entity.PropertyValue> property_value_item = new ArrayList<>(1);
                    property_value_item.add(propertyValue1);
                    property1.setPropertyValueList(property_value_item);
                    break;
                }
            }
        }

        map.put("propertyList",propertyList);
        logger.info("获取分类列表");
        List<com.bs.mall.entity.Category> categoryList=categoryMapper.select(null, null);
        map.put("categoryList",categoryList);

        logger.info("转到后台管理-产品详情页-ajax方式");
        return "admin/include/productDetails";

    }

    //转到产品添加页
    public String goToAddPage(HttpSession session,Map<String, Object> map){
        logger.info("获取分类列表");
        List<com.bs.mall.entity.Category> categoryList=categoryMapper.select(null, null);
        map.put("categoryList", categoryList);

        logger.info("获取第一个分类信息对应的属性列表");
        com.bs.mall.entity.Property property=new com.bs.mall.entity.Property();
        property.setPropertyCategory(categoryList.get(0));
        List<com.bs.mall.entity.Property> propertyList=propertyMapper.select(property, null);
        map.put("propertyList", propertyList);

        logger.info("转到后台管理-产品添加页-ajax方式");
        return "admin/include/productDetails";
    }

    //添加产品的信息
    public String addProduct( String product_name/* 产品名称 */,
                              String product_title/* 产品标题 */,
                              Integer product_category_id/* 产品类型ID */,
                              Double product_sale_price/* 产品促销价 */,
                              Double product_price/* 产品原价 */,
                              Byte product_isEnabled/* 产品状态 */,
                              String propertyJson/* 产品属性JSON */,
                              String[] productSingleImageList/*产品预览图片名称数组*/,
                              String[] productDetailsImageList/*产品详情图片名称数组*/) {

        JSONObject jsonObject = new JSONObject();
        logger.info("整合产品信息");
        com.bs.mall.entity.Product product=new com.bs.mall.entity.Product();
        product.setProductName(product_name);
        product.setProductTitle(product_title);
        com.bs.mall.entity.Category category=new com.bs.mall.entity.Category();
        category.setCategoryId(product_category_id);
        product.setProductCategory(category);
        product.setProductSalePrice(product_sale_price);
        product.setProductPrice(product_price);
        product.setProductIsEnabled(product_isEnabled);
        product.setProductCreateDate(new Date());

        logger.info("添加产品信息");
        boolean bool=productMapper.insertOne(product)>0;
        if (!bool) {
            logger.warn("产品添加失败！事务回滚");
            jsonObject.put("success", false);
            throw new RuntimeException();
        }

        int product_id=lastIDMapper.selectLastID();
        logger.info("添加成功！,新增产品的ID值为：{}", product_id);

        JSONObject tempPropertyJSON= JSON.parseObject(propertyJson);
        Set<String> propertyIdSet = tempPropertyJSON.keySet();//只获取key
        if (propertyIdSet.size()>0){
            logger.info("整合产品子信息-产品属性");
            List<com.bs.mall.entity.PropertyValue> propertyValueList = new ArrayList<>(5);
            for (String key : propertyIdSet){
                String value=tempPropertyJSON.getString(key);
                com.bs.mall.entity.PropertyValue propertyValue=new com.bs.mall.entity.PropertyValue();
                propertyValue.setPropertyValueValue(value);

                com.bs.mall.entity.Property tempProperty=new com.bs.mall.entity.Property();
                tempProperty.setPropertyId(Integer.parseInt(key));

                com.bs.mall.entity.Product tempProduct=new com.bs.mall.entity.Product();
                tempProduct.setProductId(product_id);

                propertyValue.setPropertyValueProperty(tempProperty);
                propertyValue.setPropertyValueProduct(tempProduct);

                propertyValueList.add(propertyValue);
            }

            logger.info("共有{}条产品属性数据", propertyValueList.size());
            bool=propertyValueMapper.insertList(propertyValueList)>0;
            if (bool){
                logger.info("产品属性添加成功！");
            }else {
                logger.warn("产品属性添加失败！事务回滚");
                jsonObject.put("success", false);
                throw new RuntimeException();
            }
        }
        if (productSingleImageList != null && productSingleImageList.length > 0) {
            logger.info("整合产品子信息-产品预览图片");
            List<com.bs.mall.entity.ProductImage> productImageList=new ArrayList<>(5);
            for (String imageName : productSingleImageList){
                com.bs.mall.entity.ProductImage productImage=new com.bs.mall.entity.ProductImage();
                productImage.setProductImageType((byte) 0);
                productImage.setProductImageSrc(imageName.substring(imageName.lastIndexOf("/") + 1));
                com.bs.mall.entity.Product tempProduct=new com.bs.mall.entity.Product();
                tempProduct.setProductId(product_id);
                productImage.setProductImageProduct(tempProduct);
                productImageList.add(productImage);
            }
            logger.info("共有{}条产品预览图片数据", productImageList.size());

            bool=productImageMapper.insertList(productImageList)>0;
            if (bool){
                logger.info("产品预览图片添加成功！");
            }else {
                logger.warn("产品预览图片添加失败！事务回滚");
                jsonObject.put("success", false);
                throw new RuntimeException();
            }
        }

        if (productDetailsImageList != null && productDetailsImageList.length > 0){
            logger.info("整合产品子信息-产品详情图片");
            List<com.bs.mall.entity.ProductImage> productImageList = new ArrayList<>(5);
            for (String imageName : productSingleImageList){
                com.bs.mall.entity.ProductImage productImage=new com.bs.mall.entity.ProductImage();
                productImage.setProductImageType((byte) 1);
                productImage.setProductImageSrc(imageName.substring(imageName.lastIndexOf("/") + 1));
                com.bs.mall.entity.Product tempProduct=new com.bs.mall.entity.Product();
                tempProduct.setProductId(product_id);
                productImage.setProductImageProduct(tempProduct);
                productImageList.add(productImage);
            }
            logger.info("共有{}条产品详情图片数据", productImageList.size());

            bool=productImageMapper.insertList(productImageList)>0;
            if (bool){
                logger.info("产品详情图片添加成功！");
            }else {
                logger.warn("产品详情图片添加失败！事务回滚");
                jsonObject.put("success", false);
                throw new RuntimeException();
            }
        }
        logger.info("产品信息及其子信息添加成功！");
        jsonObject.put("success", true);
        jsonObject.put("product_id", product_id);

        return jsonObject.toJSONString();
    }

    //更新商品信息
    public String updateProduct(String product_name/* 产品名称 */,
                                String product_title/* 产品标题 */,
                                Integer product_category_id/* 产品类型ID */,
                                Double product_sale_price/* 产品促销价 */,
                                Double product_price/* 产品原价 */,
                                Byte product_isEnabled/* 产品状态 */,
                                String propertyAddJson/* 产品添加属性JSON */,
                                String propertyUpdateJson/* 产品更新属性JSON */,
                                Integer[] propertyDeleteList/* 产品删除属性ID数组 */,
                                String[] productSingleImageList/*产品预览图片名称数组*/,
                                String[] productDetailsImageList/*产品详情图片名称数组*/,
                                Integer product_id/* 产品ID */) {

        JSONObject jsonObject = new JSONObject();//作返回的结果
        logger.info("整合产品信息");
        com.bs.mall.entity.Product product=new com.bs.mall.entity.Product();
        product.setProductId(product_id);
        product.setProductName(product_name);
        product.setProductTitle(product_title);
        com.bs.mall.entity.Category category=new com.bs.mall.entity.Category();
        category.setCategoryId(product_category_id);
        product.setProductCategory(category);
        product.setProductSalePrice(product_sale_price);
        product.setProductPrice(product_price);
        product.setProductIsEnabled(product_isEnabled);
        product.setProductCreateDate(new Date());

        logger.info("更新产品信息，产品ID值为：{}", product_id);
        boolean bool=productMapper.updateOne(product)>0;

        if (!bool){
            logger.info("产品信息更新失败！事务回滚");
            jsonObject.put("success", false);
            throw new RuntimeException();
        }
        logger.info("产品信息更新成功！");

        Set<String> propertyIdSet;
        if (!StringUtils.isBlank(propertyAddJson)){
            JSONObject tempPropertyAddJson=JSON.parseObject(propertyAddJson);
            propertyIdSet=tempPropertyAddJson.keySet();
            if (propertyIdSet.size()>0){
                logger.info("整合产品子信息-需要添加的产品属性");
                List<com.bs.mall.entity.PropertyValue> propertyValueList=new ArrayList<>(5);
                for (String key : propertyIdSet){
                    String value = tempPropertyAddJson.getString(key);
                    com.bs.mall.entity.PropertyValue propertyValue = new com.bs.mall.entity.PropertyValue();
                    propertyValue.setPropertyValueValue(value);

                    com.bs.mall.entity.Property tempProperty=new com.bs.mall.entity.Property();
                    tempProperty.setPropertyId(Integer.parseInt(key));

                    propertyValue.setPropertyValueProperty(tempProperty);
                    propertyValue.setPropertyValueProduct(product);

                    propertyValueList.add(propertyValue);
                }
                logger.info("共有{}条需要添加的产品属性数据", propertyValueList.size());
                bool=propertyValueMapper.insertList(propertyValueList)>0;

                if (bool){
                    logger.info("产品属性添加成功！");
                }else {
                    logger.warn("产品属性添加失败！事务回滚");
                    jsonObject.put("success", false);
                    throw new RuntimeException();
                }
            }

        }

        if (!StringUtils.isBlank(propertyUpdateJson)){
            JSONObject tempPropertyUpdateJson = JSON.parseObject(propertyUpdateJson);
            propertyIdSet = tempPropertyUpdateJson.keySet();
            if (propertyIdSet.size()>0){
                logger.info("整合产品子信息-需要更新的产品属性");
                List<com.bs.mall.entity.PropertyValue> propertyValueList = new ArrayList<>(5);
                for (String key : propertyIdSet){
                    String value=tempPropertyUpdateJson.getString(key);
                    com.bs.mall.entity.PropertyValue propertyValue=new com.bs.mall.entity.PropertyValue();
                    propertyValue.setPropertyValueValue(value);
                    propertyValue.setPropertyValueId(Integer.parseInt(key));

                    propertyValueList.add(propertyValue);
                }
                logger.info("共有{}条需要更新的产品属性数据", propertyValueList.size());
                for (int i=0; i<propertyValueList.size();i++){
                    logger.info("正在更新第{}条，共{}条", i + 1, propertyValueList.size());
                    bool=propertyValueMapper.updateOne(propertyValueList.get(i))>0;
                    if (bool){
                        logger.info("产品属性更新成功！");
                    } else {
                        logger.warn("产品属性更新失败！事务回滚");
                        jsonObject.put("success", false);
                        throw new RuntimeException();
                    }
                }
            }
        }


        if (propertyDeleteList != null && propertyDeleteList.length > 0) {
            logger.info("整合产品子信息-需要删除的产品属性");
            logger.info("共有{}条需要删除的产品属性数据", propertyDeleteList.length);
            bool=propertyValueMapper.deleteList(propertyDeleteList)>0;

            if (bool){
                logger.info("产品属性删除成功！");
            }else {
                logger.warn("产品属性删除失败！事务回滚");
                jsonObject.put("success", false);
                throw new RuntimeException();
            }
        }

        if (productSingleImageList != null && productSingleImageList.length > 0){
            logger.info("整合产品子信息-产品预览图片");
            List<com.bs.mall.entity.ProductImage> productImageList = new ArrayList<>(5);
            for (String imageName : productSingleImageList){
                com.bs.mall.entity.ProductImage productImage=new com.bs.mall.entity.ProductImage();
                productImage.setProductImageType((byte) 0);
                productImage.setProductImageSrc(imageName.substring(imageName.lastIndexOf("/") + 1));
                productImage.setProductImageProduct(product);

                productImageList.add(productImage);
            }
            logger.info("共有{}条产品预览图片数据", productImageList.size());
            bool=productImageMapper.insertList(productImageList)>0;
            if (bool){
                logger.info("产品预览图片添加成功！");
            }else {
                logger.warn("产品预览图片添加失败！事务回滚");
                jsonObject.put("success", false);
                throw new RuntimeException();
            }
        }

        if (productDetailsImageList != null && productDetailsImageList.length > 0){
            logger.info("整合产品子信息-产品详情图片");
            List<com.bs.mall.entity.ProductImage> productImageList = new ArrayList<>(5);
            for (String imageName : productDetailsImageList){
                com.bs.mall.entity.ProductImage productImage=new com.bs.mall.entity.ProductImage();
                productImage.setProductImageType((byte) 1);
                productImage.setProductImageSrc(imageName.substring(imageName.lastIndexOf("/") + 1));
                productImage.setProductImageProduct(product);

                productImageList.add(productImage);
            }
            logger.info("共有{}条产品详情图片数据", productImageList.size());
            bool=productImageMapper.insertList(productImageList)>0;
            if (bool){
                logger.info("产品详情图片添加成功！");
            }else {
                logger.warn("产品详情图片添加失败！事务回滚");
                jsonObject.put("success", false);
                throw new RuntimeException();
            }
        }

        jsonObject.put("success", true);
        jsonObject.put("product_id", product_id);

        return jsonObject.toJSONString();
    }

    //按条件查询产品-ajax
    public String getProductBySearch(String product_name/* 产品名称 */,
                                     Integer category_id/* 产品类型ID */,
                                     Double product_sale_price/* 产品促销价 */,
                                     Double product_price/* 产品原价 */,
                                     Byte[] product_isEnabled_array/* 产品状态数组 */,
                                     String orderBy/* 排序字段 */,
                                     Boolean isDesc/* 是否倒序 */,
                                     Integer index/* 页数 */,
                                     Integer count/* 行数 */) throws UnsupportedEncodingException {
        //移除不必要条件
        if (product_isEnabled_array != null && (product_isEnabled_array.length <= 0 || product_isEnabled_array.length >=3)) {
            product_isEnabled_array = null;
        }
        if (category_id != null && category_id == 0) {
            category_id = null;
        }
        if (product_name != null) {
            //如果为非空字符串则解决中文乱码：URLDecoder.decode(String,"UTF-8");
            product_name = "".equals(product_name) ? null : URLDecoder.decode(product_name, "UTF-8");
        }
        if (orderBy != null && "".equals(orderBy)) {
            orderBy = null;
        }

        //封装查询条件
        com.bs.mall.entity.Product product=new com.bs.mall.entity.Product();
        product.setProductName(product_name);
        com.bs.mall.entity.Category category=new com.bs.mall.entity.Category();
        category.setCategoryId(category_id);
        product.setProductCategory(category);
        product.setProductPrice(product_price);
        product.setProductSalePrice(product_sale_price);

        OrderUtil orderUtil = null;
        if (orderBy != null) {
            logger.info("根据{}排序，是否倒序:{}",orderBy,isDesc);
            orderUtil = new OrderUtil(orderBy, isDesc);
        }

        JSONObject object = new JSONObject();//返回值
        logger.info("按条件获取第{}页的{}条产品", index + 1, count);
        PageUtil pageUtil = new PageUtil(index, count);
        List<com.bs.mall.entity.Product> productList=productMapper.select(product, product_isEnabled_array, orderUtil, pageUtil);
        //先把对象数组转为JSONString，再把对象数组用JSONArray转
        object.put("productList", JSONArray.parseArray(JSON.toJSONString(productList)));
        logger.info("按条件获取产品总数量");

        Integer productCount=productMapper.selectTotal(product, product_isEnabled_array);
        object.put("productCount", productCount);
        logger.info("获取分页信息");
        pageUtil.setTotal(productCount);
        object.put("totalPage", pageUtil.getTotalPage());
        object.put("pageUtil", pageUtil);

        return object.toJSONString();
    }

    //按类型ID查询属性-ajax
    public String getPropertyByCategoryId( Integer property_category_id/* 属性所属类型ID*/){
        //封装查询条件
        com.bs.mall.entity.Category category=new com.bs.mall.entity.Category();
        category.setCategoryId(property_category_id);

        JSONObject object = new JSONObject();//返回值
        logger.info("按类型获取属性列表，类型ID：{}",property_category_id);
        com.bs.mall.entity.Property property=new com.bs.mall.entity.Property();
        property.setPropertyCategory(category);
        List<com.bs.mall.entity.Property> propertyList=propertyMapper.select(property, null);
        object.put("propertyList", JSONArray.parseArray(JSON.toJSONString(propertyList)));

        return object.toJSONString();
    }

    //按ID删除产品图片并返回最新结果-ajax
    public String deleteProductImageById(Integer productImage_id/* 产品图片ID */){
        JSONObject object = new JSONObject();//返回值
        logger.info("获取productImage_id为{}的产品图片信息",productImage_id);
        com.bs.mall.entity.ProductImage productImage=new com.bs.mall.entity.ProductImage();
        productImage=productImageMapper.selectOne(productImage_id);

        logger.info("删除产品图片");
        boolean bool=productImageMapper.deleteList(new Integer[]{productImage_id})>0;
        if (bool){
            logger.info("删除图片成功！");
            object.put("success", true);
        }else {
            logger.warn("删除图片失败！事务回滚");
            object.put("success", false);
            throw new RuntimeException();
        }
        return object.toJSONString();
    }

    //上传产品图片-ajax
    public String uploadProductImage(MultipartFile file, String imageType, HttpSession session) {
        String originalFileName = file.getOriginalFilename();
        logger.info("获取图片原始文件名：{}", originalFileName);
        String extension = originalFileName.substring(originalFileName.lastIndexOf('.'));
        String filePath;
        String fileName = UUID.randomUUID() + extension;
        if ("single".equals(imageType)){
            filePath = session.getServletContext().getRealPath("/") + "res/img/item/productSinglePicture/" + fileName;
        }else {
            filePath = session.getServletContext().getRealPath("/") + "res/img/item/productDetailsPicture/" + fileName;
        }
        logger.info("文件上传路径：{}", filePath);
        JSONObject object = new JSONObject();//返回值
        try {
            logger.info("文件上传中...");
            file.transferTo(new File(filePath));
            logger.info("文件上传完成");
            object.put("success", true);
            object.put("fileName", fileName);
        } catch (IOException e) {
            logger.warn("文件上传失败！");
            e.printStackTrace();
            object.put("success", false);
        }
        return object.toJSONString();
    }

}
