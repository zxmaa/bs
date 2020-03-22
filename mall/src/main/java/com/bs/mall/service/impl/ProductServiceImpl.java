package com.bs.mall.service.impl;


import com.bs.mall.dao.ProductMapper;
import com.bs.mall.dao.pojo.Product;
import com.bs.mall.dto.req.QueryProductListReqDto;
import com.bs.mall.service.IProductService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProductServiceImpl implements IProductService {
    @Autowired
    private ProductMapper productMapper;

    //=====================user========================================================
    /**
     * 根据商品类型，或是搜索框输入的商品类型/商品名字模糊查询
     * @param queryProductListReqDto
     * @return
     */
    @Override
    public PageInfo<Product> queryProductList(QueryProductListReqDto queryProductListReqDto) {
        if(null == queryProductListReqDto.getCurrentPage()){
            //默认第一页
            queryProductListReqDto.setCurrentPage(1);
        }
        if(null == queryProductListReqDto.getPageSize()){
            //默认每页20个产品
            queryProductListReqDto.setPageSize(20);
        }
        PageHelper.startPage(queryProductListReqDto.getCurrentPage(),queryProductListReqDto.getPageSize());
        List<Product> products = productMapper.queryProductList(queryProductListReqDto);
        PageInfo<Product> pageInfo = new PageInfo<Product>(products);
        return pageInfo;
    }



    //======================admin===================================================
}
