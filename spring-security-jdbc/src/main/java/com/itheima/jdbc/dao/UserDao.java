package com.itheima.jdbc.dao;

import com.itheima.jdbc.entity.UserEntity;

import java.util.List;

public interface UserDao {

    UserEntity getUserByUserName(String username);

    List<String> findPermissionsByUserId(Integer userId);

}
