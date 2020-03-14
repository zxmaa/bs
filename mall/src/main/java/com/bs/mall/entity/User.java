package com.bs.mall.entity;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * author:xs
 * date:2020/3/12 16:14
 * description:用户类
 */
@Data
public class User {
    private Integer user_id/*用户ID*/;
    private String user_name/*用户登录名*/;
    private String user_nickname/*用户昵称*/;
    private String user_password/*用户密码*/;
    private String user_realname/*用户姓名*/;
    private Byte user_gender/*用户性别*/;
    private Date user_birthday/*用户生日*/;
    private Address user_address/*用户现居地*/;
    private Address user_homeplace/*用户家乡地址*/;
    private String user_profile_picture_src/*用户头像路径*/;
    private List<Review> reviewList/*评论集合*/;
    private List<ProductOrderItem> productOrderItemList/*订单项（购物车）集合*/;
    private List<ProductOrder> productOrderList/*订单集合*/;

    public User() {
    }

    public User(Integer user_id, String user_name, String user_nickname, String user_password, Byte user_gender) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.user_nickname = user_nickname;
        this.user_password = user_password;
        this.user_gender = user_gender;
    }

    public User(Integer user_id, String user_name, String user_nickname, String user_password, String user_realname, Byte user_gender, Date user_birthday, Address user_address, Address user_homeplace, String user_profile_picture_src, List<Review> reviewList) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.user_nickname = user_nickname;
        this.user_password = user_password;
        this.user_realname = user_realname;
        this.user_gender = user_gender;
        this.user_birthday = user_birthday;
        this.user_address = user_address;
        this.user_homeplace = user_homeplace;
        this.user_profile_picture_src = user_profile_picture_src;
        this.reviewList = reviewList;
    }

}
