package com.bs.mall.util;

/**
 * author:xs
 * date:2020/3/9 15:39
 * description:分页查询工具
 */
public final class PageUtil {
    //当前页
    private Integer index;
    //每页个数
    private Integer count;
    //总数
    private Integer total;
    //行数起始值
    private Integer pageStart;

    public PageUtil(Integer index, Integer count) {
        this.index = index;
        this.count = count;
    }

    //是否有上一页
    public Boolean isHasPrev(){
        return index >= 1;
    }

    public Boolean isHasNext(){
        return index + 1 < getTotalPage();
    }

    //获取总页数
    public Integer getTotalPage(){
        return (int) Math.ceil((double) total / count);
    }

    //获取本页第一条的位置
    public Integer getPageStart() {
        if (index != null) {
            return index * count;
        } else {
            return pageStart;
        }
    }

    public PageUtil setPageStart(Integer pageStart) {
        this.pageStart = pageStart;
        return this;
    }

    public Integer getIndex() {
        return index;
    }

    public PageUtil setIndex(Integer index) {
        this.index = index;
        return this;
    }

    public Integer getCount() {
        return count;
    }

    public PageUtil setCount(Integer count) {
        this.count = count;
        return this;
    }

    public Integer getTotal() {
        return total;
    }

    public PageUtil setTotal(Integer total) {
        this.total = total;
        return this;
    }

}
