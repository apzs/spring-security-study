package com.itheima.jdbc.dao.impl;

import com.itheima.jdbc.dao.UserDao;
import com.itheima.jdbc.entity.UserEntity;
import jakarta.annotation.Resource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @Resource
    JdbcTemplate jdbcTemplate;

    /**
     * 根据用户名查询用户信息
     * @param username
     * @return
     */
    @Override
    public UserEntity getUserByUserName(String username) {
        String sql = "select id, username, password, fullname, mobile from jdbc_user where username = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(UserEntity.class), username);
    }

    @Override
    public List<String> findPermissionsByUserId(Integer userId){
        String sql = "select code from jdbc_permission where id in (  " +
                    "    select permission_id from jdbc_role_permission where role_id in ( " +
                    "        select role_id from jdbc_user_role where user_id = ?"+
                    "        ) " +
                    ");";
        //return jdbcTemplate.query(sql, new Object[]{userId}, new BeanPropertyRowMapper<>(String.class));
        return jdbcTemplate.queryForList(sql,String.class,userId);
    }
}
