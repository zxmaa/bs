package com.bs.mall.fore.service;

import com.bs.mall.dto.req.ForeAddReviewReqDto;
import com.bs.mall.service.fore.IAddressService;
import com.bs.mall.service.fore.IReviewService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IReviewServiceTest {
    @Autowired
    private IReviewService reviewService;

    @Test
    public void getTotalByOrderItemId(){
        Integer count = reviewService.getTotalByOrderItemId(300);
        System.out.println(count);
    }

    @Test
    public void addReviewTest(){
        ForeAddReviewReqDto reviewReqDto = new ForeAddReviewReqDto();
        reviewReqDto.setReviewContent("hahhaa");
        reviewReqDto.setOrderItemId(288);
        reviewService.addReview(reviewReqDto);
    }
}
