package com.bs.mall.controller.Admin;

import com.bs.mall.controller.BaseController;
import com.bs.mall.service.IAdminService;
import com.bs.mall.service.IProductOrderService;
import com.bs.mall.service.IProductService;
import com.bs.mall.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * author:xs
 * date:2020/3/19 20:24
 * description:管理员主页控制器
 */

@RestController
@RequestMapping("/admin")
public class AdminHomeController extends BaseController {
    @Autowired
    private IAdminService adminService;

    @Autowired
    private IProductOrderService productOrderService;

    @Autowired
    private IProductService productService;

    @Autowired
    private IUserService userService;

    public String goToPage(HttpSession session, Map<String, Object> map){
        return null;
    }
}
