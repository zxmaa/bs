package com.bs.mall.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bs.mall.dao.pojo.PropertyValue;
import com.bs.mall.util.PageUtil;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 属性值Mapper接口
 */
@Repository
@Mapper
public interface PropertyValueMapper extends BaseMapper<PropertyValue> {
    //获取产品详情-属性值信息
    List<com.bs.mall.entity.PropertyValue> select(@Param("propertyValue") com.bs.mall.entity.PropertyValue propertyValue, @Param("pageUtil") PageUtil pageUtil);

    //插入多个
    Integer insertList(@Param("propertyValueList") List<com.bs.mall.entity.PropertyValue> propertyValueList);

    Integer updateOne(@Param("propertyValue") com.bs.mall.entity.PropertyValue propertyValue);

    Integer deleteList(@Param("propertyValue_id_list") Integer[] propertyValue_id_list);

}
