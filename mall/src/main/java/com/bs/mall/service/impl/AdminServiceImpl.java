package com.bs.mall.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.bs.mall.dao.AdminMapper;
import com.bs.mall.dao.pojo.Admin;
import com.bs.mall.service.IAdminService;
import com.bs.mall.util.PageUtil;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service("adminService")
public class AdminServiceImpl implements IAdminService {
    AdminMapper adminMapper;


    @Override
    public boolean add(Admin admin) {
        return adminMapper.insert(admin)>0;
    }

    @Override
    public boolean update(Admin admin) {
        return adminMapper.updateById(admin)>0;
    }

    @Override
    public String checkLogin(HttpSession session, String username, String password) {
        Admin admin = adminMapper.selectByLogin(username,password);

        JSONObject object = new JSONObject();
        if(admin == null){
            //logger.info("登录验证失败");
            object.put("success",false);
        } else {
            //logger.info("登录验证成功，管理员ID传入会话");
            session.setAttribute("adminId",admin.getAdminId());
            object.put("success",true);
        }
        return object.toString();
    }

    @Override
    public String getAdminProfilePicture(String username) {
        Admin admin=adminMapper.selectOne(username, null);
        JSONObject object = new JSONObject();
        if(admin == null){
            //logger.info("未找到头像路径");
            object.put("success",false);
        } else {
            //logger.info("成功获取头像路径");
            object.put("success",true);
            object.put("srcString",admin.getAdminProfilePictureSrc());
        }
        return object.toString();
    }


    @Override
    public Admin login(String admin_name, String admin_password) {
        return adminMapper.selectByLogin(admin_name, admin_password);
    }




}
