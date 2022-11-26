package com.itheima.jdbc.service;

import com.itheima.jdbc.dao.impl.UserDaoImpl;
import com.itheima.jdbc.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    UserDaoImpl userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userDao.getUserByUserName(username);

        List<String> permissions = userDao.findPermissionsByUserId(userEntity.getId());

        return User.withUsername(userEntity.getUsername()).password(userEntity.getPassword())
                .authorities(permissions.toArray(new String[0])).build();
    }
}
