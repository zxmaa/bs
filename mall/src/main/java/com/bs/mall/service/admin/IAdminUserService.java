package com.bs.mall.service.admin;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.Map;

public interface IAdminUserService {
    String goUserManagePage(HttpSession session, Map<String, Object> map);

    String getUserById(HttpSession session, Map<String,Object> map,Integer uid/* 用户ID */);

    String getUserBySearch(String user_name/* 用户名称 */, Byte[] user_gender_array/* 用户性别数组 */, String orderBy/* 排序字段 */, Boolean isDesc/* 是否倒序 */, Integer index/* 页数 */, Integer count/* 行数 */) throws UnsupportedEncodingException;

}
