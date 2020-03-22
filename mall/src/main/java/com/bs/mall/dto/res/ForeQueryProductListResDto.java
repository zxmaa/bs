package com.bs.mall.dto.res;

import com.bs.mall.dao.pojo.Product;
import com.github.pagehelper.PageInfo;
import lombok.Data;

/**
 * 用于根据商品类型，或是搜索框输入的商品类型/商品名字模糊查询 的返回值的dto
 */
@Data
public class ForeQueryProductListResDto {

    /**
     * 类型ID
     */
    private Integer categoryId;

    /**
     *搜索框中的内容
     */
    private String searchProductName;

    /**
     *排序字段
     */
    private String orderBy;

    /**
     * 是否降序：true:是（综合、新品、销量这三个一直传true）
     * false：否
     * 价格：升序，降序根据情况而定
     */
    private Boolean isDesc;

    /**
     * 查询出来的产品结果集
     */
    PageInfo<Product> products;
}
