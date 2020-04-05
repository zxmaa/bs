package com.bs.mall.fore.service;

import com.bs.mall.dao.pojo.Category;
import com.bs.mall.dto.ForeProductSimpleDto;
import com.bs.mall.dto.res.ForeCategoryAndProductResDto;
import com.bs.mall.service.fore.ICategoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ICategoryServiceTest {
    @Autowired
    private ICategoryService categoryService;

    @Test
    public void getAllCategoryTest(){
        List<Category> allCategory = categoryService.getAllCategory();
        for (Category category : allCategory) {
            System.out.println(category);
        }
    }

    @Test
    public void getCategoryAndProductTest(){
        List<ForeCategoryAndProductResDto> categoryAndProduct = categoryService.getCategoryAndProduct();
        for (ForeCategoryAndProductResDto foreCategoryAndProductResDto : categoryAndProduct) {
            System.out.println(foreCategoryAndProductResDto);
        }
    }

    @Test
    public void getProductByCategoryIdTest(){
        List<ForeProductSimpleDto> productByCategoryId = categoryService.getProductByCategoryId(1);
        for (ForeProductSimpleDto foreProductSimpleDto : productByCategoryId) {
            System.out.println(foreProductSimpleDto);
        }
    }
}
