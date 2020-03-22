package com.bs.mall.dto.res;


import lombok.Data;

/**
 * 猜你喜欢返回属性
 */
@Data
public class ForeProductGuessResDto {
    /**
     * 产品ID
     */
    private Integer productId;

    /**
     * 产品图片路径：找一张概述图的路径
     */
    private String productImageSrc;

}
