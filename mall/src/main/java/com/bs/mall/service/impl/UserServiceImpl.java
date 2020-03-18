package com.bs.mall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bs.mall.dao.UserMapper;
import com.bs.mall.dao.pojo.User;
import com.bs.mall.dto.UserDto;
import com.bs.mall.dto.req.UserReqDto;
import com.bs.mall.service.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    UserMapper userMapper;

    //=====================user========================================================
    /**
     * 新增用户
     * @param userDto
     */
    @Override
    public void addUser(UserDto userDto) {
        User user = new User();
        BeanUtils.copyProperties(userDto,user);
        user.setRegisterTime(new Date());
        userMapper.insert(user);
    }

    /**
     * 修改用户信息
     * @param userDto
     */
    @Override
    public void updateUser(UserDto userDto) {
        User user = new User();
        BeanUtils.copyProperties(userDto,user);
        userMapper.updateById(user);
    }

    /**
     * 用户登录
     * @param userReqDto
     * @return
     */
    @Override
    public  UserDto userLogin(UserReqDto userReqDto) {

        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_name",userReqDto.getUserName())
                .eq("user_password",userReqDto.getUserPassword());

        User user = userMapper.selectOne(wrapper);
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(user,userDto);
        return userDto;
    }

    /**
     * 根据username查找用户
     * @param userName
     * @return
     */
    @Override
    public UserDto findUserByUsereName(String userName) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_name",userName);
        User user = userMapper.selectOne(wrapper);
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(user,userDto);
        return userDto;
    }


    @Override
    public UserDto findUserByUserId(Integer userId) {
        User user = userMapper.selectById(userId);
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(user,userDto);
        return userDto;
    }


    //======================admin===========================================================
}
