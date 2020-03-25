package com.bs.mall.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 用于查询新增的产品ID
 */
@Repository
@Mapper
public interface LastIDMapper {
    int selectLastID();
}
