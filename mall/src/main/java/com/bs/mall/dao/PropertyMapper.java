package com.bs.mall.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bs.mall.dao.pojo.Property;
import com.bs.mall.util.PageUtil;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 属性Mapper接口
 */
@Repository
@Mapper
public interface PropertyMapper extends BaseMapper<Property> {
    List<com.bs.mall.entity.Property> select(@Param("property") com.bs.mall.entity.Property property, @Param("pageUtil") PageUtil pageUtil);

}
