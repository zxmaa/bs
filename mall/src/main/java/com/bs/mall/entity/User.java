package com.bs.mall.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
     * 用户密码
     */
    private String userPassword;


    /**
     * 用户性别
     */
    private Byte userGender;

    /**
     * 用户电话号码
     */
    private String userTel;

    /**
     * 用户注册时间
     */
    private Date registerTime;

    /**
     *     用户姓名
     */
      private String userRealname;

    /**
     * 用户昵称
     */
     private String userNickname;


    /**
     * 用户头像路径
     */

    private String  userProfilePictureSrc;

    /**
     * 生日
     */

    private Date userBirthday;

//    /**
//     *     用户现居地
//     */
//    private String userAddress;
//
//    /**
//     *  用户家乡地址
//      */
//
//    private String userHomeplace;

    private Address userAddress/*用户现居地*/;
    private Address userHomeplace/*用户家乡地址*/;
    private List<Review> reviewList/*评论集合*/;
    private List<ProductOrderItem> productOrderItemList/*订单项（购物车）集合*/;
    private List<ProductOrder> productOrderList/*订单集合*/;

    public String formatTime(Date date) {
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
        return df.format(date);
    }

}
