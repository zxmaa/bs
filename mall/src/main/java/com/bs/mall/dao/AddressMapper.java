package com.bs.mall.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bs.mall.dao.pojo.Address;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 地址Mapper接口
 */
@Repository
@Mapper
public interface AddressMapper extends BaseMapper<Address> {

    com.bs.mall.entity.Address selectOne(@Param("addressAreaId") String addressAreaId);

    //=====================fore===================================

    /**
     * 根据areaId，查出其下一级的市/县信息
     * @param areaId
     * @return
     */
    List<Address>  getListByAreaIdFore(String areaId);

    /**
     * 得到所有的省份
     * @return
     */
    List<Address>  getAllProvinceFore();
}

