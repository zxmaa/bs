package com.bs.mall.fore.service;

import com.bs.mall.dao.pojo.UserAddress;
import com.bs.mall.dto.res.ForeUserAddressResDto;
import com.bs.mall.service.fore.IUserAddressService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IUserAddressServiceTest {
    @Autowired
    private IUserAddressService userAddressService;

    @Test
    public void getAllUserAddressTest(){
        List<ForeUserAddressResDto> allUserAddress = userAddressService.getAllUserAddress(1);
        System.out.println(allUserAddress);
        for (ForeUserAddressResDto userAddress : allUserAddress) {
            System.out.println(userAddress);
        }
    }

    @Test
    public void addUserAddressTest(){
        UserAddress userAddress = new UserAddress();
        userAddress.setUserId(1);
        userAddress.setReceiver("aa");
        userAddress.setTel("18758956236");
        userAddress.setPostCode("789565");
        userAddress.setFlag(1);
        userAddress.setDetailAddress("大家好");
        userAddress.setAddressAreaId("510922");
        userAddressService.addUserAddress(userAddress);
    }

    @Test
    public void deleteUserAddressTest(){
        userAddressService.deleteUserAddress(8);
    }

    @Test
    public void updateUserAddressTest(){
        UserAddress userAddress = new UserAddress();
        userAddress.setUserAddressId(7);
        userAddress.setDetailAddress("bb");
        userAddress.setFlag(1);
        userAddress.setUserId(1);
        userAddressService.updateUserAddress(userAddress);
    }

    @Test
    public void getUserAddressById(){
        UserAddress userAddressById = userAddressService.getUserAddressById(9);
        System.out.println(userAddressById);
    }
    

}
