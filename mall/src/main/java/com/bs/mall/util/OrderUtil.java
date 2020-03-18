package com.bs.mall.util;

/**
 * author:xs
 * date:2020/3/12 20:12
 * description:用于查询排序的工具
 */
public final class OrderUtil {
    //排序字段
    private String orderBy;
    //倒叙排序
    private boolean isDesc;

    public OrderUtil(String orderBy){
        this.orderBy=orderBy;
        this.isDesc=false;
    }
    public OrderUtil(String orderBy,boolean isDesc) {
        this.orderBy = orderBy;
        this.isDesc = isDesc;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public boolean getIsDesc() {
        return isDesc;
    }
}
