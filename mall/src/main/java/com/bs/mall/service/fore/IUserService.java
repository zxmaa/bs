package com.bs.mall.service.fore;


import com.bs.mall.dao.pojo.User;
import com.bs.mall.dto.ForeUserDto;
import com.bs.mall.dto.req.ForeUserReqDto;

public interface IUserService {

    /**
     * 新增用户
     * @param foreUserDto
     */
    void addUser(ForeUserDto foreUserDto);

    /**
     * 修改用户信息
     * @param foreUserDto
     */
    void updateUser(ForeUserDto foreUserDto);

    /**
     * 用户登录
     * @param foreUserReqDto
     * @return
     */
    ForeUserDto userLogin(ForeUserReqDto foreUserReqDto);

    /**
     * 根据username查找用户
     * @param userName
     * @return
     */
    ForeUserDto findUserByUsereName(String userName);

    /**
     * 根据用户id查找用户
     * @param userId
     * @return
     */
    ForeUserDto findUserByUserId(Integer userId);

    /**
     * 根据id,修改用户信息
     * @param user
     */
    void updateUser(User user);

}
