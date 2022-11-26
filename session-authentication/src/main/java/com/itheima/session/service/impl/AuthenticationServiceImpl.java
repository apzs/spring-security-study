package com.itheima.session.service.impl;

import com.itheima.session.dao.UserDao;
import com.itheima.session.entity.UserEntity;
import com.itheima.session.service.AuthenticationService;
import com.itheima.session.vo.AuthenticationVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    UserDao userDao;

    @Override
    public UserEntity authentication(AuthenticationVo vo) {
        // 判断输入的账户和密码是否为空
        if (vo == null || vo.getUsername() ==null || vo.getPassword() ==null){
            throw new RuntimeException("账号和密码不能为空");
        }
        // 根据用户名查找数据库中的用户信息
        UserEntity userDto = userDao.getUserEntity(vo.getUsername());
        //如果没有查到用户 或 密码与查到到密码不一致 则证明登陆失败
        if (userDto == null || !vo.getPassword().equals(userDto.getPassword())){
            throw new RuntimeException("账号或密码错误");
        }
        // 返回用户信息
        return userDto;
    }





}
