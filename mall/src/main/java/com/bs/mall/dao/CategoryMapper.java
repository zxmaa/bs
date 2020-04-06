package com.bs.mall.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bs.mall.dao.pojo.Category;
import com.bs.mall.util.PageUtil;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 类型Mapper接口
 */
@Repository
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {
    List<com.bs.mall.entity.Category> select(@Param("category_name") String categoryName, @Param("pageUtil") PageUtil pageUtil);
    Integer selectTotal(@Param("category_name") String category_name);
    com.bs.mall.entity.Category selectOne(@Param("category_id") Integer category_id);
    Integer insertOne(@Param("category") com.bs.mall.entity.Category category);
    Integer updateOne(@Param("category") com.bs.mall.entity.Category category);



}
