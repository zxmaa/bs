package com.bs.mall.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bs.mall.dao.pojo.ProductOrderItem;
import com.bs.mall.util.PageUtil;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 订单项Mapper接口
 */
@Repository
@Mapper
public interface ProductOrderItemMapper extends BaseMapper<ProductOrderItem> {
    List<com.bs.mall.entity.ProductOrderItem> select(@Param("pageUtil") PageUtil pageUtil);/*所有的*/

    List<com.bs.mall.entity.ProductOrderItem> selectByUserId(@Param("user_id") Integer user_id, @Param("pageUtil") PageUtil pageUtil);
    List<com.bs.mall.entity.ProductOrderItem> selectByOrderId(@Param("order_id") Integer order_id, @Param("pageUtil") PageUtil pageUtil);

}
