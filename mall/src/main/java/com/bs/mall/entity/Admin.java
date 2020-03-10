package com.bs.mall.entity;

import lombok.Data;

/**
 * author:xs
 * date:2020/3/9 15:22
 * description:管理员实体类
 */
@Data
public class Admin {
    private Integer admin_id/*管理员ID*/;

    private String admin_name/*管理员登录名*/;

    private String admin_nickname/*管理员昵称*/;

    private String admin_password/*管理员密码*/;

    private String admin_profile_picture_src/*管理员头像路径*/;

    @Override
    public String toString() {
        return "Admin{" +
                "admin_id=" + admin_id +
                ", admin_name='" + admin_name + '\'' +
                ", admin_nickname='" + admin_nickname + '\'' +
                ", admin_password='" + admin_password + '\'' +
                ", admin_profile_picture_src='" + admin_profile_picture_src + '\'' +
                '}';
    }
    public Admin(){

    }

    public Admin(Integer admin_id, String admin_name, String admin_nickname, String admin_password, String admin_profile_picture_src) {
        this.admin_id = admin_id;
        this.admin_name = admin_name;
        this.admin_nickname = admin_nickname;
        this.admin_password = admin_password;
        this.admin_profile_picture_src = admin_profile_picture_src;
    }

}
