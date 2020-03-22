package com.bs.mall.service.admin;

import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.Map;

public interface IAdminHomeService {
    String goToPage(HttpSession session, Map<String, Object> map) throws ParseException;

    String goToPageByAjax(HttpSession session, Map<String, Object> map) throws ParseException;

    String getChartDataByDate( String beginDate, String endDate) throws ParseException;

    }
