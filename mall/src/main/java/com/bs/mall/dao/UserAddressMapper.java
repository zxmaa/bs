package com.bs.mall.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bs.mall.dao.pojo.UserAddress;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户详细地址Mapper接口
 */
@Mapper
public interface UserAddressMapper extends BaseMapper<UserAddress> {
}
