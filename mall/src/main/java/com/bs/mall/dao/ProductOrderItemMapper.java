package com.bs.mall.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bs.mall.dao.pojo.ProductOrderItem;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 订单项Mapper接口
 */
@Repository
@Mapper
public interface ProductOrderItemMapper extends BaseMapper<ProductOrderItem> {
}
