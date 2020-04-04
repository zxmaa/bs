package com.bs.mall.service.fore;

import com.bs.mall.dao.pojo.UserAddress;
import com.bs.mall.dto.res.ForeUserAddressResDto;

import java.util.List;

public interface IUserAddressService {

    /**
     * 根据用户id,得到该用户的常用地址（返回结果的：第一个为默认地址）
     * 若返回null:该用户无常用地址
     * @param userID
     * @return
     */
    List<ForeUserAddressResDto> getAllUserAddress(Integer userID);

    /**
     * 新增用户常用地址
     * @param userAddress
     */
    void addUserAddress(UserAddress userAddress);

    /**
     * 根据userAddressId，删除用户常用地址
     * @param userAddressId
     */
    void deleteUserAddress(Integer userAddressId);

    /**
     * 修改用户常用地址
     * @param userAddress
     */
    void updateUserAddress(UserAddress userAddress);

    /**
     * 根据userAddressId,得到一条记录
     * @param userAddressId
     * @return
     */
    UserAddress getUserAddressById(Integer userAddressId);
}
