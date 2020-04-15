package com.bs.mall.fore.service;

import com.bs.mall.dao.pojo.Address;
import com.bs.mall.dto.res.ForeAddressDetailResDto;
import com.bs.mall.service.fore.IAddressService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IAddressServiceTest {
    @Autowired
    private IAddressService addressService;

    @Test
    public void getListByAreaIdTest(){
        List<Address> listByAreaId = addressService.getListByAreaId("510900");
        for (Address address : listByAreaId) {
            System.out.println(address);
        }

    }

    @Test
    public void getAllProvinceTest(){
        List<Address> allProvince = addressService.getAllProvince();
        for (Address address : allProvince) {
            System.out.println(address);
        }
    }

    @Test
    public void getDetailsTest(){
        ForeAddressDetailResDto details = addressService.getDetails("510922");
        System.out.println(details);
    }


}
