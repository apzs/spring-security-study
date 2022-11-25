package com.itheima.session.service;


import com.itheima.session.entity.UserEntity;
import com.itheima.session.vo.AuthenticationVo;

public interface AuthenticationService {

    UserEntity authentication(AuthenticationVo vo);

}
