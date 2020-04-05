package com.bs.mall.dto.res;



/**
 *用户常用地址响应
 */
public class ForeUserAddressResDto {

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
     *1：默认地址  0：非默认地址
     */
    private Integer flag;

    /**
     *邮政编码
     */
    private String postCode;

    /**
     * 省份名称
     */
    private String province;

    /**
     * 市名称
     */
    private String city;

    /**
     * 县/区名称
     */
    private String district;


}
