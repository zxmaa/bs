package com.bs.mall.dto.res;

import com.bs.mall.dao.pojo.ProductOrder;
import lombok.Data;

import java.util.List;

/**
 * 确认收货页的展示信息
 */
@Data
public class ForeConfirmReceiptResDto {
    /**
     * 订单信息
     */
    private ProductOrder productOrder;

    /**
     * 总金额
     */
    private Double totalPrice;


    /**
     * 订单项，显示详情
     */
    List<ForeOrderItemReceiptResDto> orderItemDetails;


}
