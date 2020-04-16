package com.bs.mall.service.admin.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bs.mall.dao.AdminMapper;
import com.bs.mall.dao.pojo.Admin;
import com.bs.mall.service.admin.BaseService;
import com.bs.mall.service.admin.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

;

@Service("adminService")
public class AdminServiceImpl extends BaseService implements IAdminService {
    @Autowired
    AdminMapper adminMapper;
    public void setAdminMapper(AdminMapper adminMapper) {
        this.adminMapper = adminMapper;
    }


    @Override
    public String checkLogin(HttpSession session, String username, String password) {
        Admin admin = adminMapper.selectByLogin(username,password);

        JSONObject object = new JSONObject();
        if(admin == null){
            logger.info("登录验证失败");
            object.put("success",false);
        } else {
            logger.info("登录验证成功，管理员ID传入会话");
            session.setAttribute("adminId",admin.getAdminId());
            object.put("success",true);
        }
        return object.toJSONString();
    }

    @Override
    public String getAdminProfilePicture(String username) {
        QueryWrapper<Admin> wrapper=new QueryWrapper<>();
        wrapper.eq("admin_name", username);
        Admin admin=adminMapper.selectOne(wrapper);
        JSONObject object = new JSONObject();
        if(admin == null){
            logger.info("未找到头像路径");
            object.put("success",false);
        } else {
            logger.info("成功获取头像路径");
            object.put("success",true);
            object.put("srcString",admin.getAdminProfilePictureSrc());
        }
        return object.toJSONString();
    }


    @Override
    public Admin login(String admin_name, String admin_password) {
        return adminMapper.selectByLogin(admin_name, admin_password);
    }


}
