package com.bs.mall.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bs.mall.entity.Admin;
import com.bs.mall.util.PageUtil;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 管理员Mapper接口
 */
@Mapper
public interface AdminMapper extends BaseMapper<Admin> {
    Integer insertOne(@Param("admin") com.bs.mall.entity.Admin admin);
    Integer updateOne(@Param("admin") com.bs.mall.entity.Admin admin);

    List<com.bs.mall.entity.Admin> select(@Param("admin_name") String admin_name, @Param("pageUtil") PageUtil pageUtil);
    com.bs.mall.entity.Admin selectOne(@Param("admin_name") String admin_name, @Param("admin_id") Integer admin_id);
    com.bs.mall.entity.Admin selectByLogin(@Param("admin_name") String admin_name, @Param("admin_password") String admin_password);
    Integer selectTotal(@Param("admin_name") String admin_name);

}
