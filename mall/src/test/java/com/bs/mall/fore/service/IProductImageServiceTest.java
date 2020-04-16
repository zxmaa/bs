package com.bs.mall.fore.service;

import com.bs.mall.dao.pojo.ProductImage;
import com.bs.mall.service.fore.IProductImageService;
import com.bs.mall.service.fore.IProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IProductImageServiceTest {
    @Autowired
    private IProductImageService productImageService;

    @Test
    public void productImageServiceTest(){
        List<ProductImage> imagesByType = productImageService.getImagesByType(1, 2);
        for (ProductImage productImage : imagesByType) {
            System.out.println(productImage);
        }
    }
}
