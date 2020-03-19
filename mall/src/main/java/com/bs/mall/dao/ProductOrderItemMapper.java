package com.bs.mall.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bs.mall.dao.pojo.ProductOrderItem;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单项Mapper接口
 */
@Mapper
public interface ProductOrderItemMapper extends BaseMapper<ProductOrderItem> {
}
