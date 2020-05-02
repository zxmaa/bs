package com.bs.mall.dto.res;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.bs.mall.dao.pojo.Product;
import com.bs.mall.dao.pojo.ProductImage;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ForeProductListResDto {
    /**
     * 参评类型名
     */
    private String categoryName;

    /**
     * 产品ID
     */
    private Integer productId;

    /**
     * 产品名称
     */
    private String  productName;

    /**
     * 产品标题
     */
    private String productTitle;

    /**
     * 产品原价格
     */
    private Double productPrice;

    /**
     * 产品促销价格
     */
    private Double productSalePrice;

    /**
     * 产品创建日期
     */
    private Date productCreateDate;

    /**
     * 产品对应类型的id
     */
    private Integer productCategoryId;

    /**
     * 产品状态:销售中0 停售中1 促销中2
     */
    private Integer productIsEnabled;

    /**
     * 销量数
     */
    private Integer productSaleCount;

    /**
     * 评价数
     */
    private Integer productReviewCount;


    /**
     * 产品数量(库存值)
     */
    private Integer productQuantity;



    /**
     * 该产品对应的预览图片
     */
    List<ProductImage> previewPicture;

}
