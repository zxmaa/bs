package com.bs.mall.dto.res;

import com.bs.mall.dto.ForeProductSimpleDto;
import lombok.Data;

import java.util.List;

/**
 * 返回每种类型下的产品的标题:用于首页的显示
 *
 * */
@Data
public class ForeCategoryAndProductResDto {

    /**
     * 类型ID
     */
    private Integer categoryId;

    /**
     * 类型名称
     */
    private String  categoryName;

    /**
     * 该属性下的产品信息
     */
    List<ForeProductSimpleDto> foreProductSimpleDtos;
}
