package com.bs.mall.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bs.mall.entity.Category;
import com.bs.mall.util.PageUtil;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 类型Mapper接口
 */
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {
    Integer insertOne(@Param("category") Category category);
    Integer updateOne(@Param("category") Category category);
    List<Category> select(@Param("category_name") String category_name, @Param("pageUtil") PageUtil pageUtil);
    Category selectOne(@Param("category_id") Integer category_id);
    Integer selectTotal(@Param("category_name") String category_name);
}
