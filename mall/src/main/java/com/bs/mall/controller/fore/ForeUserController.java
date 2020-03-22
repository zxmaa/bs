package com.bs.mall.controller.fore;

import com.alibaba.fastjson.JSONObject;
import com.bs.mall.controller.BaseController;
import com.bs.mall.dto.ForeUserDto;
import com.bs.mall.dto.req.ForeUserReqDto;
import com.bs.mall.service.fore.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * 用户controller
 */
@Controller
@RequestMapping("/user")
@SessionAttributes("userIdSession")
public class ForeUserController extends BaseController {
    @Autowired
    private UserServiceImpl userServiceImpl;

    /**
     * 用户登录
     * @param userReqDto
     * @param model
     * @return
     */
    @ResponseBody
    @RequestMapping("/login")
    public String userLogin(@RequestBody ForeUserReqDto userReqDto, Model model){
        ForeUserDto userDto = userServiceImpl.userLogin(userReqDto);

        JSONObject jsonObject = new JSONObject();
        if(null == userDto ){
            logger.info("登录验证失败：用户名或密码错误");
            jsonObject.put("success","false");
        }else{
            //设置session的值
            model.addAttribute("userIdSession",userDto.getUserId());
            jsonObject.put("success",true);
        }
        return jsonObject.toJSONString();
    }

    /**
     * 退出登录
     * @param sessionStatus
     * @return
     */
    @RequestMapping("/logout")
    public String logout(SessionStatus sessionStatus){
        //清除session
       sessionStatus.setComplete();
        return "redirect:/fore/loginPages";
    }

    /**
     * 得到用户详细信息
     * @param session
     * @return
     */
    @RequestMapping("/getUserDetail")
    public ModelAndView getUserDetail(HttpSession session){
       ModelAndView modelAndView = new ModelAndView();
       ForeUserDto userDto = userServiceImpl.findUserByUserId((Integer)session.getAttribute("userId"));
        modelAndView.addObject("user",userDto);
        modelAndView.setViewName("fore/userDetails");
        return modelAndView;
    }

    @RequestMapping("/userRegister")
    public String userRegister(@RequestBody ForeUserDto userDto){
         String userName = userDto.getUserName();
         ForeUserDto temp = userServiceImpl.findUserByUsereName(userName);
        JSONObject jsonObject = new JSONObject();
         if(temp != null){
             logger.info("已存在该用户名");
             jsonObject.put("success",false);
             jsonObject.put("msg","用户名已存在，请重新输入！");
             return jsonObject.toJSONString();
         }
         userServiceImpl.addUser(userDto);
        jsonObject.put("success",true);
        return jsonObject.toJSONString();
    }

    /**
     * 修改用户信息
     * @param userDto
     * @return
     */
    @ResponseBody
    @RequestMapping("/updateUserInfo")
    public String updateUserInfo(@RequestBody ForeUserDto userDto){
        String userName = userDto.getUserName();
        ForeUserDto temp = userServiceImpl.findUserByUsereName(userName);
        if(temp != null){
            logger.info("已存在该用户名");
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("success",false);
            jsonObject.put("msg","已存在该用户名，请重新修改！");
            return jsonObject.toJSONString();
        }
        userServiceImpl.updateUser(userDto);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("success",true);
        return jsonObject.toJSONString();
    }
}
