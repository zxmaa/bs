package com.bs.mall.dto.res;

import com.bs.mall.dao.pojo.Product;
import com.bs.mall.dao.pojo.ProductOrderItem;
import lombok.Data;

/**
 * 写评价的响应页
 */
@Data
public class ForeWriteReviewResDto {

    private Product product;

    private ProductOrderItem productOrderItem;
}
