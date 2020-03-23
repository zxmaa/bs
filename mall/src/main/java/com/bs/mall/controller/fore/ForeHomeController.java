package com.bs.mall.controller.fore;

import com.bs.mall.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 主页的controller
 */
@Controller
public class ForeHomeController extends BaseController {


    /**
     * 转发到前台主页
     * @param model
     * @return
     */
    @RequestMapping("/")
    public String goToHomePage(Model model){
           return null;
    }

}
