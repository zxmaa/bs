package com.bs.mall.fore.service;


import com.bs.mall.dao.pojo.ProductOrder;
import com.bs.mall.dto.req.ForeCreateOrderByListReqDto;
import com.bs.mall.dto.req.ForeCreateOrderByListSimpleReqDto;
import com.bs.mall.dto.req.ForeCreateOrderByOneReqDto;
import com.bs.mall.dto.res.ForeCreateOrderResDto;
import com.bs.mall.dto.res.ForePayResDto;
import com.bs.mall.dto.res.ForePaySuccessResDto;
import com.bs.mall.service.fore.IProductOrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IProductOrderServiceTest {
    @Autowired
    private IProductOrderService productOrderService;

    @Test
    public void createOrderByOneTest(){
        ForeCreateOrderByOneReqDto createOrderByOneReqDto = new ForeCreateOrderByOneReqDto();
        createOrderByOneReqDto.setProductId(1);
        createOrderByOneReqDto.setProductNum(2);
        createOrderByOneReqDto.setUserMessage("test");
        createOrderByOneReqDto.setUserAddressId(9);
        createOrderByOneReqDto.setUserId(1);
        ForeCreateOrderResDto orderByOne = productOrderService.createOrderByOne(createOrderByOneReqDto);
        System.out.println(orderByOne);
    }

    @Test
    public void createOrderByListTest(){
        ForeCreateOrderByListReqDto createOrderByListReqDto = new ForeCreateOrderByListReqDto();
        createOrderByListReqDto.setUserId(1);
        createOrderByListReqDto.setUserAddressId(9);

        List<ForeCreateOrderByListSimpleReqDto> temp= new ArrayList<>();
        ForeCreateOrderByListSimpleReqDto createOrderByListSimpleReqDto1 = new ForeCreateOrderByListSimpleReqDto();
        createOrderByListSimpleReqDto1.setOrderItemId(302);
        createOrderByListSimpleReqDto1.setUserMessage("haha");

        ForeCreateOrderByListSimpleReqDto createOrderByListSimpleReqDto2 = new ForeCreateOrderByListSimpleReqDto();
        createOrderByListSimpleReqDto2.setOrderItemId(303);
        createOrderByListSimpleReqDto2.setUserMessage("heihei");

        temp.add(createOrderByListSimpleReqDto1);
        temp.add(createOrderByListSimpleReqDto2);
        createOrderByListReqDto.setCreateOrderByListSimpleReqDtos(temp);
        ForeCreateOrderResDto orderByList = productOrderService.createOrderByList(createOrderByListReqDto);

        System.out.println(orderByList);
    }

    @Test
    public void getProductOrderByOrderCodeTest(){
        ProductOrder productOrderByOrderCode = productOrderService.getProductOrderByOrderCode("2020041323473521515008");
        System.out.println(productOrderByOrderCode);
    }

    @Test
    public void getPayPageInfoByOrderCodeTest(){
        ForePayResDto payPageInfoByOrderCode = productOrderService.getPayPageInfoByOrderCode("2018052314045801");
        System.out.println(payPageInfoByOrderCode);
    }

}
