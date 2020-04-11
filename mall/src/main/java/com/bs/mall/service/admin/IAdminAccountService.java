package com.bs.mall.service.admin;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

public interface IAdminAccountService {
    String goToPage(HttpSession session, Map<String, Object> map);

    String logout(HttpSession session);

    String uploadAdminHeadImage(MultipartFile file, HttpSession session) throws IOException;

    String updateAdmin(HttpSession session, String admin_nickname/*管理员昵称*/,
                       String admin_password/*管理员当前密码*/,
                       String admin_newPassword/*管理员新密码*/,
                       String admin_profile_picture_src/*管理员头像路径*/,
                       String admin_id/*管理员编号*/);

}
