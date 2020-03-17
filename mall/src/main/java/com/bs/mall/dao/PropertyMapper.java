package com.bs.mall.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bs.mall.entity.Property;
import org.apache.ibatis.annotations.Mapper;

/**
 * 属性Mapper接口
 */
@Mapper
public interface PropertyMapper extends BaseMapper<Property> {
}
