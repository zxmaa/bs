package com.bs.mall.service.admin.impl;

import com.alibaba.fastjson.JSONObject;
import com.bs.mall.dao.AdminMapper;
import com.bs.mall.entity.Admin;
import com.bs.mall.service.admin.BaseService;
import com.bs.mall.service.admin.IAdminAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

/**
 * author:xs
 * date:2020/4/10 21:35
 * description:管理员账户service
 */
@Service
public class AdminAccountServiceImpl extends BaseService implements IAdminAccountService {
    @Autowired
    AdminMapper adminMapper;
    @Override
    public String goToPage(HttpSession session, Map<String, Object> map) {
        logger.info("获取管理员信息");
        Object adminId = checkAdmin(session);
        if (adminId==null){
            return "admin/include/loginMessage";
        }
        logger.info("获取目前登录的管理员信息，管理员ID：{}",adminId);
        Admin admin=adminMapper.selectOneAdmin(null, Integer.parseInt(adminId.toString()));
        map.put("admin",admin);

        logger.info("转到后台管理-账户页-ajax方式");
        return "admin/accountManagePage";
    }

    @Override
    public String logout(HttpSession session) {
        Object o = session.getAttribute("adminId");
        if (o == null) {
            logger.info("无相关信息，返回管理员登陆页");
        }else {
            session.removeAttribute("adminId");
            session.invalidate();
            logger.info("登录信息已清除，返回管理员登陆页");
        }

        return "redirect:/admin/login";
    }

    @Override
    public String uploadAdminHeadImage(MultipartFile file, HttpSession session) throws IOException {
        String originalFileName = file.getOriginalFilename();
        logger.info("获取图片原始文件名：{}", originalFileName);
        assert originalFileName != null;
        //获取图片拓展名
        String extension=originalFileName.substring(originalFileName.lastIndexOf("."));
        //生成唯一的随机名
        String fileName= UUID.randomUUID()+extension;
        //获取上传路径
        String filePath=session.getServletContext().getRealPath("/")+"res/img/item/adminProfilePicture/"+fileName;

        logger.info("文件上传路径：{}", filePath);
        JSONObject jsonObject = new JSONObject();//返回的结果
        try{
            logger.info("文件上传中...");
            file.transferTo(new File(filePath));
            logger.info("文件上传成功！");
            jsonObject.put("success", true);
            jsonObject.put("fileName", fileName);
        }catch (IOException e){
            logger.warn("文件上传失败！");
            e.printStackTrace();
            jsonObject.put("success", false);
        }

        return jsonObject.toJSONString();
    }

    @Override
    public String updateAdmin(HttpSession session, String admin_nickname, String admin_password, String admin_newPassword, String admin_profile_picture_src, String admin_id) {
        logger.info("获取管理员信息");
        Object adminId = checkAdmin(session);
        if (adminId == null) {
            return "admin/include/loginMessage";
        }
        JSONObject jsonObject = new JSONObject();//做返回结果
        Admin admin=new Admin();
        admin.setAdminId(Integer.parseInt(admin_id));
        admin.setAdminNickName(admin_nickname);

        if (admin_password!=null && !"".equals(admin_newPassword) && admin_newPassword != null && !"".equals(admin_newPassword)){
            logger.info("获取需要修改的管理员信息");
            Admin tempAdmin=adminMapper.selectOneAdmin(null, Integer.parseInt(adminId.toString()));
            if (adminMapper.selectByLogin(tempAdmin.getAdminName(), admin_password)!=null){
                logger.info("原密码正确");
                admin.setAdminPassword(admin_newPassword);
            }else {
                logger.info("原密码错误，返回错误信息");
                jsonObject.put("success", false);
                jsonObject.put("message", "原密码输入有误！");
                return jsonObject.toJSONString();
            }
        }

        if (admin_profile_picture_src != null && !"".equals(admin_profile_picture_src)){
            logger.info("管理员头像路径为{}", admin_profile_picture_src);
            admin.setAdminProfilePictureSrc(admin_profile_picture_src.substring(admin_profile_picture_src.lastIndexOf("/")+1));
        }

        logger.info("更新管理员信息，管理员ID值为：{}", admin_id);
        boolean bool=adminMapper.updateOne(admin)>0;
        if (bool){
            logger.info("更新成功！");
            jsonObject.put("success", true);
            session.removeAttribute("adminId");
            session.invalidate();
            logger.info("登录信息已清除");
        }else {
            jsonObject.put("success", false);
            logger.warn("更新失败！事务回滚");
            throw new RuntimeException();
        }
        return jsonObject.toJSONString();
    }
}
