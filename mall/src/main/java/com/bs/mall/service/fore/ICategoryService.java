package com.bs.mall.service.fore;

import com.bs.mall.dao.pojo.Category;
import com.bs.mall.dto.res.ForeCategoryAndProductResDto;

import java.util.List;

/**
 * 类型的service接口
 */
public interface ICategoryService {


    /***
     * 得到所有的category
     * @return
     */
    List<Category> getAllCategory();

    /**
     * 得到每个category下的产品的部分信息:用于首页
     * @return
     */
    List<ForeCategoryAndProductResDto> getCategoryAndProduct();


}
