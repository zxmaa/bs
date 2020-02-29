package com.bs.mall.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bs.mall.dao.pojo.Product;
import org.apache.ibatis.annotations.Mapper;

/**
 * 产品Mapper接口
 */
@Mapper
public interface ProductMapper extends BaseMapper<Product> {
}
