package com.itheima.session.controller;

import com.itheima.session.entity.UserEntity;
import com.itheima.session.service.AuthenticationService;
import com.itheima.session.vo.AuthenticationVo;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class AuthenticationController {

    @Autowired
    AuthenticationService authenticationService;


    @PostMapping(value = "/login",produces = {"text/plain;charset=UTF-8"})
    public String login(AuthenticationVo vo, HttpSession httpSession){
        try {
            UserEntity userEntity = authenticationService.authentication(vo);
            httpSession.setAttribute(UserEntity.SESSION_USER_KEY,userEntity);
            return userEntity.getFullname() + "登录成功";
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return e.getMessage();
        }

    }

    @GetMapping(value = "/logout",produces = {"text/plain;charset=UTF-8"})
    public String logout(HttpSession httpSession){
        UserEntity userEntity = (UserEntity) httpSession.getAttribute(UserEntity.SESSION_USER_KEY);
        httpSession.invalidate();
        return userEntity.getFullname() + "退出登录成功";
    }

}
