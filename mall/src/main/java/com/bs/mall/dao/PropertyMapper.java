package com.bs.mall.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bs.mall.dao.pojo.Property;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 属性Mapper接口
 */
@Repository
@Mapper
public interface PropertyMapper extends BaseMapper<Property> {
}
