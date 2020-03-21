package com.bs.mall.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bs.mall.dao.pojo.PropertyValue;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 属性值Mapper接口
 */
@Repository
@Mapper
public interface PropertyValueMapper extends BaseMapper<PropertyValue> {
}
