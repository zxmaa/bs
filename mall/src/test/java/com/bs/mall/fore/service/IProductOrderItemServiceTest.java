package com.bs.mall.fore.service;


import com.bs.mall.dao.pojo.ProductOrderItem;
import com.bs.mall.dao.pojo.User;
import com.bs.mall.dto.req.ForeAddOrderItemCartReqDto;
import com.bs.mall.dto.res.ForeShowOrderItemResDto;
import com.bs.mall.service.fore.IProductOrderItemService;
import com.bs.mall.service.fore.IProductOrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IProductOrderItemServiceTest {
    @Autowired
    private IProductOrderItemService productOrderItemService;

    @Test
    public void productOrderServiceTest(){
        Boolean flag = productOrderItemService.deleteByOrderItemId(287);
        System.out.println(flag);
    }

    @Test
    public void selectOrderItemByIdTest(){
        ProductOrderItem productOrderItem = productOrderItemService.selectOrderItemById(291);
        System.out.println(productOrderItem);
    }

    @Test
    public void selectOrderItemCartByUserIdTest(){
        List<ProductOrderItem> productOrderItems = productOrderItemService.selectOrderItemCartByUserId(1);
        for (ProductOrderItem productOrderItem : productOrderItems) {
            System.out.println(productOrderItem);
        }

    }

    @Test
    public void addOrderItemWithCartTest(){
        ForeAddOrderItemCartReqDto addOrderItemCartReqDto = new ForeAddOrderItemCartReqDto();
        addOrderItemCartReqDto.setUserId(1);
        addOrderItemCartReqDto.setProductNumber(2);
        addOrderItemCartReqDto.setProductId(1);
        Boolean flag= productOrderItemService.addOrderItemWithCart(addOrderItemCartReqDto);
        System.out.println(flag);
    }

    @Test
    public void getOrderItemCartNumberTest(){
        Integer orderItemCartNumber = productOrderItemService.getOrderItemCartNumber(17);
        System.out.println(orderItemCartNumber);
    }

    @Test
    public void selectShowOrderItemCartTest(){
        List<ForeShowOrderItemResDto> foreShowOrderItemResDtos = productOrderItemService.selectShowOrderItemCart(17);
        System.out.println(foreShowOrderItemResDtos);
        for (ForeShowOrderItemResDto foreShowOrderItemResDto : foreShowOrderItemResDtos) {
            System.out.println(foreShowOrderItemResDto);
        }
    }

    @Test
    public void updateCartTest(){
        List<ProductOrderItem> list = new ArrayList<>();
        ProductOrderItem productOrderItem = new ProductOrderItem();
        productOrderItem.setProductOrderItemId(293);
        productOrderItem.setProductOrderItemNumber(4);
        list.add(productOrderItem);

        ProductOrderItem productOrderItem1 = new ProductOrderItem();
        productOrderItem1.setProductOrderItemId(294);
        productOrderItem1.setProductOrderItemNumber(100000);
        list.add(productOrderItem1);

        String s = productOrderItemService.updateCart(list);
        System.out.println(s);
    }

    @Test
    public void getCalculateCartTest(){
        Integer []integers={293,294};
        List<ForeShowOrderItemResDto> calculateCart = productOrderItemService.getCalculateCart(integers);
        for (ForeShowOrderItemResDto foreShowOrderItemResDto : calculateCart) {
            System.out.println(foreShowOrderItemResDto);
        }
    }

    @Test
    public void calculateOrderItemCartMoneyTest(){
        Integer []integers={293,294};
        Double aDouble = productOrderItemService.calculateOrderItemCartMoney(integers);
        System.out.println(aDouble);

    }

    @Test
    public void showOrderItemWithBuyNowTest(){
        ForeShowOrderItemResDto foreShowOrderItemResDto = productOrderItemService.showOrderItemWithBuyNow(1, 45, 2);
        System.out.println(foreShowOrderItemResDto);
    }

    @Test
    public void addOrderItemTest(){
        ProductOrderItem productOrderItem = new ProductOrderItem();
        productOrderItem.setProductId(42);
        productOrderItem.setUserId(17);
        productOrderItem.setProductOrderItemNumber(2);
        productOrderItemService.addOrderItem(productOrderItem);
    }

    @Test
    public void updateOrderItemTest(){
        ProductOrderItem productOrderItem = new ProductOrderItem();
       productOrderItem.setProductOrderItemId(295);
        productOrderItem.setProductOrderItemNumber(3);
        productOrderItemService.updateOrderItem(productOrderItem);
    }

    @Test
    public void getOrderItemsByOrderIdTest(){
        List<ProductOrderItem> orderItemsByOrderId = productOrderItemService.getOrderItemsByOrderId(220);
        for (ProductOrderItem productOrderItem : orderItemsByOrderId) {
            System.out.println(productOrderItem);
        }
    }

}
