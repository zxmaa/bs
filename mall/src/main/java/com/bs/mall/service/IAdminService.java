package com.bs.mall.service;

import com.bs.mall.dao.pojo.Admin;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface IAdminService {



    String checkLogin(HttpSession session, String username, String password);

    String getAdminProfilePicture(String username);

    //List<Admin> getList(String admin_name, PageUtil pageUtil);
    //Admin get(String admin_name, Integer admin_id);
    Admin login(String admin_name, String admin_password);
    //Integer getTotal(String admin_name);


}
