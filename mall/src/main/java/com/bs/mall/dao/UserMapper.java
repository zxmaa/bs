package com.bs.mall.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bs.mall.dao.pojo.User;
import com.bs.mall.util.OrderUtil;
import com.bs.mall.util.PageUtil;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户Mapper接口
 */
@Repository
@Mapper
public interface UserMapper extends BaseMapper<User> {
    List<User> findUser();

    List<com.bs.mall.entity.User> selectList(@Param("user") com.bs.mall.entity.User user, @Param("orderUtil") OrderUtil orderUtil, @Param("pageUtil") PageUtil pageUtil);

    Integer selectTotal(@Param("user") com.bs.mall.entity.User user);

    com.bs.mall.entity.User selectOne(@Param("user_id") Integer user_id);
}
