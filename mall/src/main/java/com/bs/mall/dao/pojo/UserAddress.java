package com.bs.mall.dao.pojo;

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

    /**
     * 收货人
     */
    private String receiver;

    /**
     * 收货人电话
     */
    private String tel;

    /**
     * 1：默认地址  0：非默认地址
     */
    private Integer flag;

    /**
     * 邮政编码
     */
    private String postCode;


}
