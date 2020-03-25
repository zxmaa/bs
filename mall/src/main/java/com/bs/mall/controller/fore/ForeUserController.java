package com.bs.mall.controller.fore;

import com.alibaba.fastjson.JSONObject;
import com.bs.mall.controller.BaseController;
import com.bs.mall.dto.ForeUserDto;
import com.bs.mall.dto.req.ForeUserReqDto;
import com.bs.mall.service.fore.IUserService;
import com.bs.mall.service.fore.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * 用户controller
 */
@Controller
@RequestMapping("/user")
@SessionAttributes("userIdSession")
public class ForeUserController extends BaseController {
    @Autowired
    private IUserService userService;


    /**
     * 得到用户详细信息，在转发到用户详情页
     * @param model
     * @return
     */
    @RequestMapping("/userDetails")
    public String goToUserDetail(Model model,HttpSession session){

        String userName = (String) session.getAttribute("userName");
        ForeUserDto userDto = userService.findUserByUsereName(userName);
        model.addAttribute("user",userDto);

        return "fore/userDetails";
    }

    @ResponseBody
    @RequestMapping("/user/uploadUserHeadImage")
    public String  uploadUserHeadImage(@RequestParam MultipartFile file, HttpSession session){
        //获取上传文件的名字
        String originalFileName = file.getOriginalFilename();
        //取出该文件的扩展名
        String extension = originalFileName.substring(originalFileName.lastIndexOf('.'));
        String fileName = UUID.randomUUID() + extension;
        //上传文件的位置
        String filePath = session.getServletContext().getRealPath("/") + "res/images/item/userProfilePicture/" + fileName;
        logger.info("文件上传路径：", filePath);
        JSONObject jsonObject = new JSONObject();
        try {
            logger.info("文件上传中...");
            file.transferTo(new File(filePath));
            logger.info("文件上传成功！");
            jsonObject.put("success", true);
            jsonObject.put("fileName", fileName);
        } catch (IOException e) {
            logger.warn("文件上传失败！");
            e.printStackTrace();
            jsonObject.put("success", false);
        }
        return jsonObject.toJSONString();
    }


    /**
     * 修改用户信息(格式的合法性：前端验证)
     * @param userDto
     * @return
     */
    @ResponseBody
    @RequestMapping("/user/updateBasic")
    public String updateUserInfo(@RequestBody ForeUserDto userDto){
        String userName = userDto.getUserName();
        ForeUserDto temp = userService.findUserByUsereName(userName);
        userService.updateUser(userDto);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("success",true);
        //注：修改成功，由前端弹出提示窗口：个人资料修改成功
        //然后前端，在跳到用户详情页，/userDetails
        return jsonObject.toJSONString();
    }

    /**
     * 修改用户名，密码(密码的强度，前端验证)
     * @param userDto
     * @return
     */
    @ResponseBody
    @RequestMapping("/user/updateAccount")
    public String updateUserAccount(@RequestBody ForeUserDto userDto){
        String userName = userDto.getUserName();
        ForeUserDto temp = userService.findUserByUsereName(userName);
        if(temp != null){
            logger.info("已存在该用户名");
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("success",false);
            jsonObject.put("msg","已存在该用户名，请重新修改！");
            return jsonObject.toJSONString();
        }
        userService.updateUser(userDto);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("success",true);
        //注：修改成功，由前端弹出提示窗口：用户名，密码修改成功，请重新登录；
        //然后前端跳转到:/login/logout
        return jsonObject.toJSONString();
    }
}
