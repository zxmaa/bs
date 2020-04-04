package com.bs.mall.controller.fore;

import com.alibaba.fastjson.JSONObject;
import com.bs.mall.controller.BaseController;
import com.bs.mall.dao.pojo.Address;
import com.bs.mall.service.fore.IAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 地址controller
 */
@RestController
public class ForeAdressController extends BaseController {

    @Autowired
    private IAddressService addressService;
    /**
     * 根据address_areaId获取下级地址信息-ajax
     * @param areaId
     * @return
     */
    @RequestMapping(value = "/address/{areaId}",method = RequestMethod.GET)
    public String getAddressByAreaId(@PathVariable String areaId){
        JSONObject object = new JSONObject();
        logger.info("获取以areaId为父地址的地区信息");
        List<Address> addressList = addressService.getListByAreaId(areaId);
        if(addressList == null || addressList.size() ==0){
            object.put("success",false);
            return object.toJSONString();
        }
        logger.info("获取该地址可能的子地址信息");
        List<Address> childAddressList = addressService.getListByAreaId(addressList.get(0).getAddressAreaId());
        object.put("success",true);
        object.put("addressList", addressList);
        object.put("childAddressList", childAddressList);
        return object.toString();
    }


}
