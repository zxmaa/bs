package com.bs.mall.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * 用户常用地址
 */
@Data
public class UserAddress {
    /**
     *常用地址id
     */
    @TableId(type = IdType.AUTO)
    private Integer userAddressId;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 地址id
     */
    private String addressAreaId;

    /**
     * 详细地址
     */
    private String detailAddress;
}
