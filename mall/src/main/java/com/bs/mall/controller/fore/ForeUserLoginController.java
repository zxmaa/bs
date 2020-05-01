package com.bs.mall.controller.fore;


import com.alibaba.fastjson.JSONObject;
import com.bs.mall.controller.BaseController;
import com.bs.mall.dto.ForeUserDto;
import com.bs.mall.dto.req.ForeUserReqDto;
import com.bs.mall.service.fore.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpSession;

/**
 * 用户登录的controller
 */
@Controller
public class ForeUserLoginController extends BaseController {
    @Autowired
    private IUserService userService;

    @RequestMapping("/login")
    public String goToLoginPage(){
        logger.info("转到用户登录界面");
        return "user/loginPage";
    }


    /**
     * 用户登录
     * @param userReqDto
     * @param model
     * @return
     */
    @ResponseBody
    @RequestMapping("/login/doLogin")
    public String userLogin(@RequestBody ForeUserReqDto userReqDto,HttpSession session){
        ForeUserDto userDto = userService.userLogin(userReqDto);

        JSONObject jsonObject = new JSONObject();
        if(null == userDto ){
            logger.info("登录验证失败：用户名或密码错误");
            jsonObject.put("success", false);
        }else{
            //设置session的值
            logger.info("登录成功:"+userDto.getUserName());
            session.setAttribute("userName",userDto.getUserName());
            jsonObject.put("success", true);
        }
        return jsonObject.toJSONString();
    }

    /**
     * 退出登录
     * @param session
     * @return
     */
    @RequestMapping("/login/logout")
    public String logout(HttpSession session){
        Object userName = session.getAttribute("userName");
        //清除session
        if( userName != null){
            session.removeAttribute("userName");
            session.invalidate();
            logger.info("登录信息已清除，重定向到用户登录页");
        }
        logger.info("跳转到登录！！");
        return "redirect:/login";
    }
}
