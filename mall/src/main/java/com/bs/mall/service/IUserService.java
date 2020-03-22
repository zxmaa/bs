package com.bs.mall.service;


import com.bs.mall.dao.pojo.User;
import com.bs.mall.dto.UserDto;
import com.bs.mall.dto.req.UserReqDto;

public interface IUserService {
    //=====================user========================================================
    /**
     * 新增用户
     * @param userDto
     */
    void addUser(UserDto userDto);

    /**
     * 修改用户信息
     * @param userDto
     */
    void updateUser(UserDto userDto);

    /**
     * 用户登录
     * @param userReqDto
     * @return
     */
    UserDto userLogin(UserReqDto userReqDto);

    /**
     * 根据username查找用户
     * @param userName
     * @return
     */
    UserDto findUserByUsereName(String userName);

    /**
     * 根据用户id查找用户
     * @param userId
     * @return
     */
    UserDto findUserByUserId(Integer userId);


    //======================admin===================================================
}
