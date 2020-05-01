package com.bs.mall.fore.service;


import com.alibaba.fastjson.JSONObject;
import com.bs.mall.dao.pojo.ProductOrder;
import com.bs.mall.dto.req.ForeCreateOrderByListReqDto;
import com.bs.mall.dto.req.ForeCreateOrderByListSimpleReqDto;
import com.bs.mall.dto.req.ForeCreateOrderByOneReqDto;
import com.bs.mall.dto.req.ForeOrderShowReqDto;
import com.bs.mall.dto.res.*;
import com.bs.mall.service.fore.IProductOrderService;
import com.github.pagehelper.PageInfo;
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

    @Test
    public void payOrderTest(){
        productOrderService.payOrder("2020041323545254191263");
    }

    @Test
    public void getPaySuccessInfoByOrderCodeTest(){
        ForePaySuccessResDto paySuccessInfoByOrderCode = productOrderService.getPaySuccessInfoByOrderCode("2020041323545254191263");
        System.out.println(paySuccessInfoByOrderCode);
    }

    @Test
    public void updateOrderStatusTest(){
        productOrderService.updateOrderStatus("2020041323523313045184",2);
    }

    @Test
    public void getConfirmReceiptInfoTest(){
        ForeConfirmReceiptResDto confirmReceiptInfo = productOrderService.getConfirmReceiptInfo("2020041323523313045184");
        System.out.println(confirmReceiptInfo);
    }

    @Test
    public void getOrderSuccessInfoTest(){
        ForeOrderSuceessResDto orderSuccessInfo = productOrderService.getOrderSuccessInfo("2020041323545254191263");
        System.out.println(orderSuccessInfo);
    }

    @Test
    public void getOrderShowTest(){
        ForeOrderShowReqDto orderShowReqDto = new ForeOrderShowReqDto();
        orderShowReqDto.setUserId(1);
        orderShowReqDto.setStatus(1);
        orderShowReqDto.setPageSize(10);
        orderShowReqDto.setPageNumber(1);
        PageInfo<ForeOrderShowResDto> orderShow = productOrderService.getOrderShow(orderShowReqDto);
        System.out.println(JSONObject.toJSONString(orderShow));
    }

    @Test
    public void getProductOrderByIdTest(){
        ProductOrder productOrderById = productOrderService.getProductOrderById(239);
        System.out.println(productOrderById);
    }

    @Test
    public void getWriteReviewInfoTest(){
        ForeWriteReviewResDto writeReviewInfo = productOrderService.getWriteReviewInfo(302);
        System.out.println(writeReviewInfo);
    }

}
