package com.bs.mall.controller.Admin;

import com.bs.mall.service.admin.IAdminOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * author:xs
 * date:2020/4/9 15:05
 * description:管理端订单控制器
 */
@Controller
public class AdminOrderController {
    @Autowired
    IAdminOrderService adminOrderService;

    //转到后台管理-订单页-ajax
    @RequestMapping(value = "admin/order", method = RequestMethod.GET)
    public String goToPage(HttpSession session, Map<String, Object> map){
        return adminOrderService.goToPage(session, map);
    }


    //转到后台管理-订单详情页-ajax
    @RequestMapping(value = "admin/order/{oid}", method = RequestMethod.GET)
    public String goToDetailsPage(HttpSession session, Map<String, Object> map, @PathVariable Integer oid/* 订单ID */){
        return adminOrderService.goToDetailsPage(session, map, oid);
    }

    //更新订单信息-ajax
    @ResponseBody
    @RequestMapping(value = "admin/order/{order_id}", method = RequestMethod.PUT, produces = "application/json;charset=UTF-8")
    public String updateOrder(@PathVariable("order_id") String order_id){
        return adminOrderService.updateOrder(order_id);
    }

    //按条件查询订单-ajax
    @ResponseBody
    @RequestMapping(value = "admin/order/{index}/{count}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public String getOrderBySearch(@RequestParam(required = false) String productOrder_code/* 订单号 */,
                                   @RequestParam(required = false) String productOrder_post/* 订单邮政编码 */,
                                   @RequestParam(required = false) Byte[] productOrder_status_array/* 订单状态数组 */,
                                   @RequestParam(required = false) String orderBy/* 排序字段 */,
                                   @RequestParam(required = false,defaultValue = "true") Boolean isDesc/* 是否倒序 */,
                                   @PathVariable Integer index/* 页数 */,
                                   @PathVariable Integer count/* 行数 */){
        return adminOrderService.getOrderBySearch(productOrder_code,productOrder_post,productOrder_status_array,orderBy,isDesc,index,count);
    }


}
