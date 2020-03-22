package com.bs.mall.dao;

import com.alibaba.fastjson.JSONObject;
import com.bs.mall.dao.pojo.ProductImage;
import com.bs.mall.dao.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductImageTest {
    @Autowired
    private ProductImageMapper productImageMapper;

   @Autowired
   UserMapper userMapper;
    @Test
    public void addProductImageMapperTest(){

        ProductImage productImage = new ProductImage();
        productImage.setProductImageType(1);
        productImage.setProductImageSrc("cbgf");
        productImage.setProductImageProductId(1);

        productImageMapper.insert(productImage);
    }

    @Test
    public void user(){

        List<User> user = userMapper.findUser();
        System.out.println(JSONObject.toJSONString(user));

    }
}
