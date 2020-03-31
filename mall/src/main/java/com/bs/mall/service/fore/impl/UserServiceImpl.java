package com.bs.mall.service.fore.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bs.mall.dao.UserMapper;
import com.bs.mall.dao.pojo.User;
import com.bs.mall.dto.ForeUserDto;
import com.bs.mall.dto.req.ForeUserReqDto;
import com.bs.mall.service.fore.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;

    //=====================user========================================================
    /**
     * 新增用户
     * @param foreUserDto
     */
    @Override
    public void addUser(ForeUserDto foreUserDto) {
        User user = new User();
        BeanUtils.copyProperties(foreUserDto,user);
        user.setRegisterTime(new Date());
        user.setIntegral(0);
        userMapper.insert(user);
    }

    /**
     * 修改用户信息
     * @param foreUserDto
     */
    @Override
    public void updateUser(ForeUserDto foreUserDto) {
        User user = new User();
        BeanUtils.copyProperties(foreUserDto,user);
        userMapper.updateById(user);
    }

    /**
     * 用户登录
     * @param foreUserReqDto
     * @return
     */
    @Override
    public ForeUserDto userLogin(ForeUserReqDto foreUserReqDto) {

        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_name", foreUserReqDto.getUserName())
                .eq("user_password", foreUserReqDto.getUserPassword());

        User user = userMapper.selectOne(wrapper);
        if(null == user){
            return null;
        }
        ForeUserDto foreUserDto = new ForeUserDto();
        BeanUtils.copyProperties(user, foreUserDto);
        return foreUserDto;
    }

    /**
     * 根据username查找用户
     * @param userName
     * @return
     */
    @Override
    public ForeUserDto findUserByUsereName(String userName) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_name",userName);
        User user = userMapper.selectOne(wrapper);
        if(null == user){
            return null;
        }
        ForeUserDto foreUserDto = new ForeUserDto();
        BeanUtils.copyProperties(user, foreUserDto);
        return foreUserDto;
    }


    @Override
    public ForeUserDto findUserByUserId(Integer userId) {
        User user = userMapper.selectById(userId);
        if(null == user){
            return null;
        }
        ForeUserDto foreUserDto = new ForeUserDto();
        BeanUtils.copyProperties(user, foreUserDto);
        return foreUserDto;
    }



}
