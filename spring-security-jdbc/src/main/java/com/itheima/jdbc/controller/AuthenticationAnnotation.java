package com.itheima.jdbc.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 认证注解
 */
@RestController
public class AuthenticationAnnotation {

    /**
     * 认证(登录)通过后才能访问
     * @return
     */
    @Secured("IS_AUTHENTICATED_ANONYMOUSLY")
    @GetMapping("/a/a1")
    public String a1(){
        return "访问a1";
    };

    @Secured("IS_AUTHENTICATED_ANONYMOUSLY")
    @GetMapping("/a/a2")
    public String a2(){
        return "访问a2";
    };

    /**
     * 有 TELLER 角色才能访问
     * @return
     */
    @Secured("ROLE_TELLER")
    public String a3(){
        return "访问a3";
    };

    @PreAuthorize("isAnonymous()")
    public String b1(){
        return "访问b1";
    };

    @PreAuthorize("isAnonymous()")
    public String b2(){
        return "访问b2";
    };

    @PreAuthorize("hasAuthority('ROLE_TELLER') and hasRole('a')")
    public String b3(){
        return "访问b3";
    };

}
