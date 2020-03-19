package com.bs.mall.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bs.mall.dao.pojo.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 管理员Mapper接口
 */
@Mapper
public interface AdminMapper extends BaseMapper<Admin> {
  /*  Admin selectByLogin(@Param("admin_name") String admin_name, @Param("admin_password") String admin_password);

    Admin selectOne(@Param("admin_name") String admin_name, @Param("admin_id") Integer admin_id);
*/
}
