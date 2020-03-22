package com.bs.mall.dto.req;

import lombok.Data;

/**
 * 用于根据商品类型，或是搜索框输入的商品类型/商品名字模糊查询 的请求dto
 */
@Data
public class QueryProductListReqDto {

    /**
     * 类型ID
     */
    private Integer categoryId;

    /**
     *搜索框中的内容:以空白分割
     */
    private String searchValue;

    /**
     * searchValue分割后的结果
     */
    private String[] searchValueSplit;
    /**
     *排序字段
     * 综合：传 product_name
     *
     * 新品：传：product_create_date
     *
     * 销量：传：product_sale_count
     *
     * 价格：传：product_sale_price
     */
    private String orderBy;

    /**
     * 是否降序：true:是（综合、新品、销量这三个一直传true）
     * false：否
     * 价格：升序，降序根据情况而定
     */
    private Boolean isDesc;

    /**
     *当前页：当从搜索框搜索出来，或category_id，或点击了四个排序方式：均传值为1
     *
     * 点击页数时，才传对应的页数
     *
     * 不传时默认为1
     */
    private Integer currentPage;

    /**
     * 每页大小：不传默认为20
     */
    private Integer pageSize;
}
