package com.bs.mall.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpSession;

/**
 * 基础控制器
 */
public class BaseController {
    //log4j2
    protected Logger logger = LogManager.getLogger(BaseController.class);

    //检查用户是否已登录
    protected Object checkUser(HttpSession session){
        Object userId = session.getAttribute("userId");
        if(userId == null){
            return null;
        }
        return userId;
    }
}
