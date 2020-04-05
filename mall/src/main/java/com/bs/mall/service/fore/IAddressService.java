package com.bs.mall.service.fore;

import com.bs.mall.dao.pojo.Address;
import com.bs.mall.dto.res.ForeAddressDetailResDto;

import java.util.List;

public interface IAddressService {
    //=====================user=================================================================

    /**
     * 根据areaId，查出其下一级的市/县信息
     * @param areaId
     * @return
     */
    List<Address> getListByAreaId(String areaId);

    /**
     * 得到所有的省份
     * @return
     */
    List<Address> getAllProvince();


    /**
     * 根据县/区的areaid，依次往上推出各级的名称
     * @param areaId
     * @return
     */
    ForeAddressDetailResDto getDetails(String  areaId);


}
