package com.bs.mall.controller.Admin;

import com.bs.mall.controller.BaseController;

import com.bs.mall.service.admin.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * author:xs
 * date:2020/3/19 14:21
 * description:管理员登录controller
 */
@RestController
@RequestMapping("/admin")
public class AdminLoginController extends BaseController {
    @Autowired
    private IAdminService adminService;

    //转到后台管理-登录页
    @RequestMapping("/login")
    public String goToPage(){
        logger.info("转到后台管理-登录页");
        return "admin/loginPage";
    }

    //登陆验证-ajax
    @RequestMapping(value = "/login/doLogin",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    public String checkLogin(HttpSession session, @RequestParam String username, @RequestParam String password){
        return adminService.checkLogin(session, username, password);
    }

    //获取管理员头像路径-ajax
    @RequestMapping(value = "/login/profile_picture",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    public String getAdminProfilePicture(@RequestParam String username){
        return adminService.getAdminProfilePicture(username);
    }

}
