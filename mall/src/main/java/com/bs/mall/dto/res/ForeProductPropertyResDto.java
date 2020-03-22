package com.bs.mall.dto.res;

import lombok.Data;

import java.util.List;

/**
 * 产品的属性值
 */
@Data
public class ForeProductPropertyResDto {
    /**
     * 产品名称
     */
    private String productName;

    List<ForePropertyValueResDto> property;

}
