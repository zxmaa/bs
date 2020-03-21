package com.bs.mall.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bs.mall.dao.pojo.UserAddress;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 用户详细地址Mapper接口
 */
@Repository
@Mapper
public interface UserAddressMapper extends BaseMapper<UserAddress> {
}
