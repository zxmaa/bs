package com.bs.mall.service.fore.impl;


import com.bs.mall.dao.AddressMapper;
import com.bs.mall.dao.pojo.Address;
import com.bs.mall.dto.res.ForeAddressDetailResDto;
import com.bs.mall.service.fore.IAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements IAddressService {

    @Autowired
    private AddressMapper addressMapper;
    /**
     *  根据areaId，查出其下一级的市/县信息
     * @param areaId
     * @return
     */
    @Override
    public List<Address> getListByAreaId(String areaId) {
         return addressMapper.getListByAreaIdFore(areaId);
    }

    /**
     * 得到所有的省份
     * @return
     */
    @Override
    public List<Address> getAllProvince() {
        return addressMapper.getAllProvinceFore();
    }

    @Override
    public ForeAddressDetailResDto getDetails(String areaId) {
        ForeAddressDetailResDto result = new ForeAddressDetailResDto();

        //县/区
        Address address1 = addressMapper.selectById(areaId);
        result.setDistrict(address1.getAddressName());

        //市
        Address address2 = addressMapper.selectById(address1.getAddressRegionId());
        result.setCity(address2.getAddressName());

        //省
        Address address3 = addressMapper.selectById(address2.getAddressRegionId());
        result.setProvince(address3.getAddressName());

        return result;
    }
}
