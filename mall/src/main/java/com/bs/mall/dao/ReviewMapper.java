package com.bs.mall.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bs.mall.entity.Review;
import org.apache.ibatis.annotations.Mapper;

/**
 * 评论Mapper接口
 */
@Mapper
public interface ReviewMapper extends BaseMapper<Review> {
}
