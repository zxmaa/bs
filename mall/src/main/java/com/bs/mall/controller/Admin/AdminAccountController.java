package com.bs.mall.controller.Admin;

import com.bs.mall.service.admin.IAdminAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

/**
 * author:xs
 * date:2020/4/10 21:28
 * description:管理员账户控制器
 */
@Controller
public class AdminAccountController {
    @Autowired
    IAdminAccountService adminAccountService;

    //转到后台管理-账户页-ajax
    @RequestMapping(value = "admin/account", method = RequestMethod.GET)
    public String goToPage(HttpSession session, Map<String, Object> map){
        return adminAccountService.goToPage(session, map);
    }

    //退出当前账号
    @RequestMapping(value = "admin/account/logout", method = RequestMethod.GET)
    public String logout(HttpSession session){
        return adminAccountService.logout(session);
    }

    //管理员头像上传
    @ResponseBody
    @RequestMapping(value = "admin/uploadAdminHeadImage", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String uploadAdminHeadImage(@RequestParam MultipartFile file, HttpSession session) throws IOException {
        return adminAccountService.uploadAdminHeadImage(file, session);
    }

    //更新管理员信息
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @ResponseBody
    @RequestMapping(value = "admin/account/{admin_id}", method = RequestMethod.PUT, produces = "application/json;charset=UTF-8")
    public String updateAdmin(HttpSession session, @RequestParam String admin_nickname/*管理员昵称*/,
                              @RequestParam(required = false) String admin_password/*管理员当前密码*/,
                              @RequestParam(required = false) String admin_newPassword/*管理员新密码*/,
                              @RequestParam(required = false) String admin_profile_picture_src/*管理员头像路径*/,
                              @PathVariable("admin_id") String admin_id/*管理员编号*/){

        return adminAccountService.updateAdmin(session, admin_nickname, admin_password,admin_newPassword, admin_profile_picture_src, admin_id);
    }
}
