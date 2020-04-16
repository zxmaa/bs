package com.bs.mall.controller.Admin;

import com.bs.mall.controller.BaseController;
import com.bs.mall.service.admin.IAdminHomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.Map;


/**
 * author:xs
 * date:2020/3/19 20:24
 * description:管理员主页控制器
 */

@Controller
public class AdminHomeController extends BaseController {
    @Autowired
    IAdminHomeService adminHomeService;

    @RequestMapping(value = "admin", method = RequestMethod.GET)
    public String goToPage(HttpSession session, Map<String, Object> map) throws ParseException {
        return adminHomeService.goToPage(session, map);
    }

    @RequestMapping(value = "admin/home", method = RequestMethod.GET)
    public String goToPageByAjax(HttpSession session, Map<String, Object> map) throws ParseException {
        return adminHomeService.goToPageByAjax(session, map);
    }
    @ResponseBody
    @RequestMapping(value = "admin/home/charts", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    public String getChartDataByDate(@RequestParam(required = false) String beginDate, @RequestParam(required = false) String endDate) throws ParseException {
        return adminHomeService.getChartDataByDate(beginDate, endDate);
    }

}
