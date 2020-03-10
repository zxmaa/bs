package com.bs.mall.controller.admin;

import com.alibaba.fastjson.JSONObject;
import com.bs.mall.controller.BaseController;
import com.bs.mall.entity.Admin;
import com.bs.mall.service.AdminService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * author:xs
 * date:2020/3/9 10:44
 * description:后台登录控制器
 */
@Controller
public class AdminLoginController extends BaseController {

    @Resource(name = "adminService")
    private AdminService adminService;

    //转到后台登录页
    @RequestMapping("admin/login")
    public String goToPage(){
        logger.info("转到后台管理-登录页");
        return "admin/loginPage";
    }

    //登陆验证-ajax
    /**
     * @RequestParam:用于来映射请求参数，required表示是否必须,默认为true
     * @param session
     * @param username
     * @param password
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "admin/login/doLogin" ,method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    public String checkLogin(HttpSession session, @RequestParam String username, @RequestParam String password){
        logger.info("管理员登录验证");
        Admin admin=adminService.login(username,password);

        JSONObject object=new JSONObject();
        if (admin==null){
            logger.info("管理员登录验证失败");
            object.put("success",false);
        }else {
            logger.info("管理员登录验证成功，管理员ID传入会话(session)");
            session.setAttribute("adminID",admin.getAdmin_id());
            object.put("success",true);
        }
        return object.toJSONString();
    }

    //ajax获取管理员头像路径
    @ResponseBody
    @RequestMapping(value = "admin/login/profile_picture",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    public String getAdminProfilePicture(@RequestParam String username){
        logger.info("根据管理员名字获取头像路径");
        Admin admin=adminService.get(username,null);

        JSONObject object=new JSONObject();
        if (admin==null){
            logger.info("未找到头像路径");
            object.put("success",false);
        }else {
            logger.info("成功找到头像路径");
            object.put("success",true);
        }
        return object.toJSONString();

    }
/*
   测试111
 */

}
