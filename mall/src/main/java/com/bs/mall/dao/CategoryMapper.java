package com.bs.mall.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bs.mall.dao.pojo.Category;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 类型Mapper接口
 */
@Repository
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {

}
