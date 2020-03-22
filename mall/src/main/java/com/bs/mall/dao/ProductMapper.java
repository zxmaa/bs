package com.bs.mall.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bs.mall.dao.pojo.Product;
import com.bs.mall.dto.req.ForeQueryProductListReqDto;
import com.bs.mall.dto.res.ForePropertyValueResDto;
import com.bs.mall.dto.res.ForeReviewSimpleResDto;
import com.bs.mall.util.OrderUtil;
import com.bs.mall.util.PageUtil;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 产品Mapper接口
 */
@Repository
@Mapper
public interface ProductMapper extends BaseMapper<Product> {

    List<Product> queryProductList(ForeQueryProductListReqDto queryProductListReqDto);
    Integer selectTotal(@Param("product") Product product, @Param("product_isEnabled_array") Byte[] product_isEnabled_array);
    List<Product> select(@Param("product") Product product, @Param("product_isEnabled_array") Byte[] product_isEnabled_array, @Param("orderUtil") OrderUtil orderUtil, @Param("pageUtil") PageUtil pageUtil);


    //==============================fore=======================================================

    /**
     * 输入的商品类型/商品名字模糊查询
     * @param foreQueryProductListReqDto
     * @return
     */
    List<Product> queryProductListFore(ForeQueryProductListReqDto foreQueryProductListReqDto);


    /**
     * 根据产品id得到产品的属性
     * @param productId
     * @return
     */
    List<ForePropertyValueResDto> getPropertyValueByProductIdFore(Integer productId);

    /**
     *
     * @param productId
     * @return
     */
    List<ForeReviewSimpleResDto> getReviewFore(Integer productId);
}
