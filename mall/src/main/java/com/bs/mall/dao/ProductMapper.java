package com.bs.mall.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bs.mall.dao.pojo.Product;
import com.bs.mall.dto.req.QueryProductListReqDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 产品Mapper接口
 */
@Mapper
public interface ProductMapper extends BaseMapper<Product> {

    List<Product> queryProductList(QueryProductListReqDto queryProductListReqDto);
}
