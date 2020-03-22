package com.bs.mall.dto.req;

import lombok.Data;

@Data
public class UserReqDto {
    /**
     * 用户登录名
     */
    private String userName;


    /**
     * 用户密码
     */
    private String userPassword;
}
