package com.bs.mall.service;

import com.bs.mall.dao.ProductMapper;
import com.bs.mall.dao.pojo.Product;
import com.bs.mall.dto.req.QueryProductListReqDto;
import com.bs.mall.dto.res.QueryProductListResDto;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;

public interface IProductService {
    //=====================user========================================================
    /**
     *   根据商品类型，或是搜索框输入的商品类型/商品名字模糊查询
     *
     * @param queryProductListReqDto
     * @return
     */
    PageInfo<Product> queryProductList(QueryProductListReqDto queryProductListReqDto);


    //======================admin===============================================================
}
