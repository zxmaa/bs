package com.bs.mall.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * 管理员
 */
@Data
public class Admin {
    /**
     * 管理员id
     */
    @TableId(type = IdType.AUTO)
    private Integer adminId;

    /**
     * 管理员登录名
     */
    private String adminName;

    /**
     * 管理员昵称
     */
    private String adminNickName;

    /**
     * 管理员密码
      */
    private  String adminPassword;

    /**
     * 管理员头像路径
     */
    private String adminProfilePictureSrc;
}
