package com.bs.mall.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;


@Data
public class ForeUserDto {
    /**
     * 用户id
     */
    @TableId(type = IdType.AUTO)
    private Integer userId;

    /**
     * 用户登录名
     */
    private String userName;


    /**
     * 用户密码
     */
    private String userPassword;


    /**
     * 用户性别
     */
    private Integer userGender;

    /**
     * 用户电话号码
     */
    private String userTel;

    /**
     * 生日
     */
    private Date userBirthday;

    /**
     * 用户注册时间
     */
    private Date registerTime;

    /**
     * 用户头像路径
     */

    private String  userProfilePictureSrc;
}
