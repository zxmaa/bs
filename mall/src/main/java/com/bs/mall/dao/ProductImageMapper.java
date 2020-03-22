package com.bs.mall.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bs.mall.dao.pojo.ProductImage;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


/**
 * 产品图片Mapper接口
 */
@Repository
@Mapper
public interface ProductImageMapper extends BaseMapper<ProductImage> {
}
