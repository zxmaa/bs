package com.bs.mall.service.fore.impl;

import com.baomidou.mybatisplus.core.conditions.query.Query;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bs.mall.dao.UserAddressMapper;
import com.bs.mall.dao.pojo.UserAddress;
import com.bs.mall.dto.res.ForeAddressDetailResDto;
import com.bs.mall.dto.res.ForeUserAddressResDto;
import com.bs.mall.service.fore.IAddressService;
import com.bs.mall.service.fore.IUserAddressService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserAddressServiceImpl implements IUserAddressService {
    @Autowired
    private UserAddressMapper userAddressMapper;
    @Autowired
    private IAddressService addressService;

    /**
     * 根据用户id,得到该用户的常用地址（返回结果的：第一个为默认地址）
     * @param userID
     * @return
     */
    @Override
    public List<ForeUserAddressResDto> getAllUserAddress(Integer userID) {
        List<ForeUserAddressResDto> result = new ArrayList<>();

        QueryWrapper<UserAddress> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",userID)
                .orderByDesc("flag")
                .orderByDesc("user_address_id");
        List<UserAddress> userAddresses = userAddressMapper.selectList(wrapper);

        ForeUserAddressResDto temp;
        for (UserAddress userAddress : userAddresses) {
            temp = new ForeUserAddressResDto();
            BeanUtils.copyProperties(userAddress,temp);
            ForeAddressDetailResDto details = addressService.getDetails(userAddress.getAddressAreaId());
            BeanUtils.copyProperties(details,temp);
            result.add(temp);
        }

        return result;
    }

    /**
     * 新增用户常用地址
     * @param userAddress
     */
    @Override
    @Transactional(rollbackFor=Exception.class)
    public void addUserAddress(UserAddress userAddress) {
        //若新增的地址，设为了默认地址
        if(userAddress.getFlag()==1){
            //将之前的默认地址设为0
            QueryWrapper<UserAddress> wrapper = new QueryWrapper<>();
            wrapper.eq("user_id",userAddress.getUserId())
                    .eq("flag",1);
            UserAddress temp = userAddressMapper.selectOne(wrapper);
            if(temp != null){
                temp.setFlag(0);
                userAddressMapper.updateById(temp);
            }

            //新增现在的地址
            userAddressMapper.insert(userAddress);
            return;
        }

        //若新增没有设为默认地址
        userAddressMapper.insert(userAddress);
        return;
    }

    /**
     * 根据userAddressId，删除用户常用地址
     * @param userAddressId
     */
    @Override
    public void deleteUserAddress(Integer userAddressId) {
        userAddressMapper.deleteById(userAddressId);
    }

    /**
     * 修改用户常用地址
     * @param userAddress
     */
    @Override
    public void updateUserAddress(UserAddress userAddress) {
        //若修改的的地址，设为了默认地址
        if(userAddress.getFlag()==1) {
            //将之前的默认地址设为0
            QueryWrapper<UserAddress> wrapper = new QueryWrapper<>();
            wrapper.eq("user_id", userAddress.getUserId())
                    .eq("flag", 1);
            UserAddress temp = userAddressMapper.selectOne(wrapper);
            if (temp != null) {
                temp.setFlag(0);
                userAddressMapper.updateById(temp);
            }
        }

        //修改现在的地址
        userAddressMapper.updateById(userAddress);
        return;
    }

    /**
     * 根据userAddressId,得到一条记录
     * @param userAddressId
     * @return
     */
    @Override
    public UserAddress getUserAddressById(Integer userAddressId) {
        return userAddressMapper.selectById(userAddressId);
    }
}
