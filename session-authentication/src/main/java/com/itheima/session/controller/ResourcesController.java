package com.itheima.session.controller;

import com.itheima.session.entity.UserEntity;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class ResourcesController {

    /**
     * 测试资源1 (需要p1权限才能访问)
     * @param httpSession
     * @return
     */
    @GetMapping(value = "/r/r1",produces = {"text/plain;charset=UTF-8"})
    public String r1(HttpSession httpSession){
        String fullname = null;
        Object object = httpSession.getAttribute(UserEntity.SESSION_USER_KEY);
        if (object == null){
            fullname = "匿名";
        }else {
            fullname =  ((UserEntity) object).getFullname();
        }
        return fullname + "访问资源r1";
    }

    /**
     * 测试资源2 (需要p2权限才能访问)
     * @param httpSession
     * @return
     */
    @GetMapping(value = "/r/r2",produces = {"text/plain;charset=UTF-8"})
    public String r2(HttpSession httpSession){
        String fullname = null;
        Object object = httpSession.getAttribute(UserEntity.SESSION_USER_KEY);
        if (object == null){
            fullname = "匿名";
        }else {
            fullname = ((UserEntity) object).getFullname();
        }
        return fullname + "访问资源r2";
    }

}
