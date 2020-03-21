package com.bs.mall.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bs.mall.dao.pojo.ProductOrder;
import com.bs.mall.util.OrderGroup;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * 订单Mapper接口
 */
@Repository
@Mapper
public interface ProductOrderMapper extends BaseMapper<ProductOrder> {
    Integer selectTotal(@Param("productOrder") ProductOrder productOrder, @Param("productOrder_status_array") Byte[] productOrder_status_array);
    List<OrderGroup> getTotalByDate(@Param("beginDate") Date beginDate, @Param("endDate") Date endDate);

}
