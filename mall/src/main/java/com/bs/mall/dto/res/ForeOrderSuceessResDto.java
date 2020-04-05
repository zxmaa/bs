package com.bs.mall.dto.res;

import com.bs.mall.dao.pojo.Product;
import com.bs.mall.dao.pojo.ProductImage;
import com.bs.mall.dao.pojo.ProductOrderItem;
import lombok.Data;

/**
 * 交易成功后界面
 * 若该订单只有一个订单项，且该订单项没有评价过产品，这些属性才设置值
 * 其余这些属性均设为null
 */
@Data
public class ForeOrderSuceessResDto {
    /**
     * 产品
     */
    private Product product;
    /**
     * 产品照片
     */
    private ProductImage productImage;
    /**
     * 订单项
     */
    private ProductOrderItem productOrderItem;
}
