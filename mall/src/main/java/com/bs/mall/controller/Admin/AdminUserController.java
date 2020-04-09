package com.bs.mall.controller.Admin;

import com.bs.mall.service.admin.IAdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * author:xs
 * date:2020/4/6 14:00
 * description:管理端用户控制器
 */
@Controller
public class AdminUserController {
    @Autowired
    IAdminUserService adminUserService;

    //转到后台管理-用户页-ajax
    @RequestMapping(value = "admin/user", method = RequestMethod.GET)
    public String goUserManagePage(HttpSession session, Map<String, Object> map){
        return adminUserService.goUserManagePage(session, map);
    }

    //转到后台管理-用户详情页-ajax
    @RequestMapping(value = "admin/user/{uid}", method = RequestMethod.GET)
    public String getUserById(HttpSession session, Map<String,Object> map, @PathVariable Integer uid/* 用户ID */){
        return adminUserService.getUserById(session, map, uid);
    }

    //按条件查询用户-ajax
    @ResponseBody
    @RequestMapping(value = "admin/user/{index}/{count}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public String getUserBySearch(@RequestParam(required = false) String user_name/* 用户名称 */,
                                  @RequestParam(required = false) Byte[] user_gender_array/* 用户性别数组 */,
                                  @RequestParam(required = false) String orderBy/* 排序字段 */,
                                  @RequestParam(required = false,defaultValue = "true") Boolean isDesc/* 是否倒序 */,
                                  @PathVariable Integer index/* 页数 */,
                                  @PathVariable Integer count/* 行数 */) throws UnsupportedEncodingException {

        return adminUserService.getUserBySearch(user_name,user_gender_array, orderBy, isDesc, index, count);
    }
}
