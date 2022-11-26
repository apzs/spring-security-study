package com.itheima.monomer.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * SpringSecurity
 */
@RestController
public class AuthenticationController {

    /**
     * SpringSecurity判断登录成功后跳转的url
     * @return
     */
    @RequestMapping(value = "/login-success",produces = {"text/plain;charset=UTF-8"})
    public String loginSuccess(){
        return " 登录成功";
    }

}
