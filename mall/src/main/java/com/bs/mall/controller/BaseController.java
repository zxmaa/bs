package com.bs.mall.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpSession;

/**
 * author:xs
 * date:2020/3/9 10:47
 * description:基控制器
 */
public class BaseController {
    protected Logger logger = LogManager.getLogger(BaseController.class);

    //获取管理员信息
    protected Object checkAdmin(HttpSession session){
        Object o=session.getAttribute("adminID");
        if (o==null){
            logger.info("无管理权限，返回管理员登陆页");
            return null;
        }
        logger.info("当前管理员ID：",o);
        return o;
    }

    //检查用户是否登陆
    protected Object checkUser(HttpSession session){
        Object o=session.getAttribute("UserID");
        if(o==null){
            logger.info("用户未登录");
            return null;
        }
        logger.info("用户ID：",o);
        return o;
    }

}
