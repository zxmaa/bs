package com.bs.mall.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bs.mall.dao.pojo.ProductImage;
import com.bs.mall.util.PageUtil;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * 产品图片Mapper接口
 */
@Repository
@Mapper
public interface ProductImageMapper extends BaseMapper<ProductImage> {
    List<com.bs.mall.entity.ProductImage> select(@Param("product_id") Integer product_id, @Param("product_image_type") Byte product_image_type, @Param("pageUtil") PageUtil pageUtil);
    Integer insertList(@Param("productImageList") List<com.bs.mall.entity.ProductImage> productImageList);
    com.bs.mall.entity.ProductImage selectOne(@Param("productImageId") Integer productImageId);
    Integer deleteList(@Param("productImage_id_list") Integer[] productImage_id_list);

}
