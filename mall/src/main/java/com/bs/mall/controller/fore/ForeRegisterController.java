package com.bs.mall.controller.fore;

import com.alibaba.fastjson.JSONObject;
import com.bs.mall.controller.BaseController;
import com.bs.mall.dto.ForeUserDto;
import com.bs.mall.service.fore.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.logging.Logger;

/**
 * 用户注册controller
 */
@Controller
public class ForeRegisterController extends BaseController {
    @Autowired
    private IUserService userService;

    @RequestMapping("/register")
    public String goToRegisterPage(){
       logger.info("转到用户注册页");
       return "user/register";
    }

    @RequestMapping("/userRegister")
    public String userRegister(@RequestBody ForeUserDto userDto){
        String userName = userDto.getUserName();
        ForeUserDto temp = userService.findUserByUsereName(userName);
        JSONObject jsonObject = new JSONObject();
        //注：其余的字段格式合法性，由前端直接验证
        if(temp != null){
            logger.info("已存在该用户名");
            jsonObject.put("success",false);
            jsonObject.put("message","用户名已存在，请重新输入！");
            return jsonObject.toJSONString();
        }
        Boolean flag = userService.telIsExist(userDto.getUserTel());
        if(flag){
            jsonObject.put("success",false);
            jsonObject.put("message","该号码已被注册，请重新输入！");
        }
        userService.addUser(userDto);
        jsonObject.put("success",true);
        return jsonObject.toJSONString();
    }

}
