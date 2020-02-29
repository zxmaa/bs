package com.bs.mall.dao.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

/**
 * 用户
 */
@Data
public class User {
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
     * 用户昵称
     */
    private String userNickname;

    /**
     * 用户密码
     */
    private String userPassword;

    /**
     * 用户姓名
     */
    private String userRealname;

    /**
     * 用户性别
     */
    private Integer userGender;

    /**
     * 生日
     */
    private Date userBirthday;

    /**
     * 用户现居地
     */
    private String userAddress;

    /**
     * 用户家乡地址
     */
    private String userHomeplace;

    /***
     * 用户头像路径
     */
    private String  userProfilePictureSrc;
}
