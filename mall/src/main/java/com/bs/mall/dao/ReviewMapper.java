package com.bs.mall.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bs.mall.dao.pojo.Review;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 评论Mapper接口
 */
@Repository
@Mapper
public interface ReviewMapper extends BaseMapper<Review> {
}
