package com.bs.mall.service.admin;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Map;

public interface IAdminOrderService {
    String goToPage(HttpSession session, Map<String, Object> map);

    String goToDetailsPage(HttpSession session, Map<String, Object> map, Integer oid/* 订单ID */);

    String updateOrder(String order_id);

    String getOrderBySearch(String productOrder_code/* 订单号 */,
                            String productOrder_post/* 订单邮政编码 */,
                            Byte[] productOrder_status_array/* 订单状态数组 */,
                            String orderBy/* 排序字段 */,
                            Boolean isDesc/* 是否倒序 */,
                            Integer index/* 页数 */,
                            Integer count/* 行数 */);
}
