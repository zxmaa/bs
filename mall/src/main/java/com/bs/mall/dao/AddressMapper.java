package com.bs.mall.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bs.mall.dao.pojo.Address;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 地址Mapper接口
 */
@Repository
@Mapper
public interface AddressMapper extends BaseMapper<Address> {
}

