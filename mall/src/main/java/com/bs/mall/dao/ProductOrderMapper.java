package com.bs.mall.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bs.mall.dao.pojo.Product;
import com.bs.mall.dao.pojo.ProductOrder;
import com.bs.mall.dto.req.ForeOrderShowReqDto;
import com.bs.mall.util.OrderGroup;
import com.bs.mall.util.OrderUtil;
import com.bs.mall.util.PageUtil;
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
    Integer selectTotal(@Param("productOrder") com.bs.mall.entity.ProductOrder productOrder, @Param("productOrder_status_array") Byte[] productOrder_status_array);
    List<OrderGroup> getTotalByDate(@Param("beginDate") Date beginDate, @Param("endDate") Date endDate);
    List<com.bs.mall.entity.ProductOrder> select(@Param("productOrder") com.bs.mall.entity.ProductOrder productOrder, @Param("product_order_status_array") Byte[] productOrder_status_array, @Param("orderUtil") OrderUtil orderUtil, @Param("pageUtil") PageUtil pageUtil);
    com.bs.mall.entity.ProductOrder selectOneProductOrder(@Param("productOrderId") Integer productOrderId);
    Integer updateOne(@Param("productOrder") com.bs.mall.entity.ProductOrder productOrder);


    //=========================fore===========================

    /**
     * 根据条件，得到显示的订单详情
     * @param orderShowReqDto
     * @return
     */
    List<ProductOrder>  getOrderShowFore(ForeOrderShowReqDto orderShowReqDto);
}
