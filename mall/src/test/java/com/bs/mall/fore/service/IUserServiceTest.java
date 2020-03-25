package com.bs.mall.fore.service;

import com.bs.mall.dto.ForeUserDto;
import com.bs.mall.dto.req.ForeUserReqDto;
import com.bs.mall.service.fore.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IUserServiceTest {
    @Autowired
    private IUserService userService;

    @Test
    public void addUserTest(){
        ForeUserDto foreUserDto = new ForeUserDto();
        foreUserDto.setUserName("zxm");
        foreUserDto.setUserPassword("123");
        foreUserDto.setUserBirthday(new Date());
        foreUserDto.setUserGender(1);
        foreUserDto.setUserTel("13598756894");
        userService.addUser(foreUserDto);
    }

    @Test
    public void updateUserTest(){
        ForeUserDto foreUserDto = new ForeUserDto();
        foreUserDto.setUserId(29);
        foreUserDto.setUserName("zxm");
        foreUserDto.setUserPassword("125");
        userService.updateUser(foreUserDto);
    }

    @Test
    public void userLoginTest(){
        ForeUserReqDto userReqDto = new ForeUserReqDto();
        userReqDto.setUserName("zxm");
        userReqDto.setUserPassword("123");
        ForeUserDto foreUserDto = userService.userLogin(userReqDto);
        System.out.println(foreUserDto);
    }
}
