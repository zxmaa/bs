package com.bs.mall.service;

import com.bs.mall.entity.OrderGroup;
import com.bs.mall.entity.ProductOrder;
import com.bs.mall.util.OrderUtil;
import com.bs.mall.util.PageUtil;

import java.util.Date;
import java.util.List;

public interface ProductOrderService {
    boolean add(ProductOrder productOrder);
    boolean update(ProductOrder productOrder);
    boolean deleteList(Integer[] productOrder_id_list);

    List<ProductOrder> getList(ProductOrder productOrder, Byte[] productOrder_status_array, OrderUtil orderUtil, PageUtil pageUtil);
    List<OrderGroup> getTotalByDate(Date beginDate, Date endDate);//获取这段时间的订单

    ProductOrder get(Integer productOrder_id);
    ProductOrder getByCode(String producyOrder_code);
    Integer getTotal(ProductOrder productOrder,Byte[] productOrder_status_array);
}
