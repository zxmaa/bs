package com.bs.mall.dto.req;

import lombok.Data;

/**
 * 猜你喜欢请求参数
 */
@Data
public class ForeProductGuessReqDto {
    /**
     * 类型ID
     */
    private Integer categoryId;

    /**
     * 第几页
     */
    private Integer pageNum;

    /**
     * 每页大小
     */
    private Integer pageSize;
}
