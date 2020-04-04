package com.bs.mall.dto.res;

import com.bs.mall.dao.pojo.ProductOrder;
import lombok.Data;

/**
 * 支付成功界面的显示信息
 */
@Data
public class ForePaySuccessResDto {
    /**
     * 订单信息
     */
    private ProductOrder productOrder;

    /**
     * 省/市/县
     */
    private  ForeAddressDetailResDto addressDetail;

    /**
     * 总价
     */
    private Double totalPrice;
}
