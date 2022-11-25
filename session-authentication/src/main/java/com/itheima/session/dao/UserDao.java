package com.itheima.session.dao;

import com.itheima.session.entity.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Repository
public class UserDao {

    //用户信息
    private Map<String, UserEntity> userMap = new HashMap<>();
    {
        Set<String> authorities1 = new HashSet<>();
        authorities1.add("p1");//这个p1我们人为让它和/r/r1对应
        Set<String> authorities2 = new HashSet<>();
        authorities2.add("p2");//这个p2我们人为让它和/r/r2对应
        userMap.put("zhangsan",new UserEntity("1010","zhangsan","123","张三","133443",authorities1));
        userMap.put("lisi",new UserEntity("1011","lisi","456","李四","144553",authorities2));
    }

    public UserEntity getUserEntity(String username) {
        return this.userMap.get(username);
    }

}
