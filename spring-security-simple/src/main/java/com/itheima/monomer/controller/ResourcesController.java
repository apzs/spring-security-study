package com.itheima.monomer.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ResourcesController {

    /**
     * 测试资源1 (需要p1权限才能访问)
     * @return
     */
    @GetMapping(value = "/r/r1",produces = {"text/plain;charset=UTF-8"})
    public String r1(){
        return " 访问资源1";
    }

    /**
     * 测试资源2 (需要p2权限才能访问)
     * @return
     */
    @GetMapping(value = "/r/r2",produces = {"text/plain;charset=UTF-8"})
    public String r2(){
        return " 访问资源2";
    }

    /**
     * 需要认证(登录)才能访问
     * @return
     */
    @GetMapping(value = "/r/r3",produces = {"text/plain;charset=UTF-8"})
    public String r3(){
        return " 访问资源3";
    }

    /**
     *  WebSecurityConfig类的filterChain方法设置的 /r/** 请求需要认证(登录)，
     *  按理说 /r 请求不需要认证(登录)，但是实测需要登录
     * @return
     */
    @GetMapping(value = "/r",produces = {"text/plain;charset=UTF-8"})
    public String r(){
        return " 访问资源4";
    }

    /**
     * 不需要认证(登录)就能访问
     * @return
     */
    @GetMapping(value = "/rs",produces = {"text/plain;charset=UTF-8"})
    public String rs(){
        return " 访问资源4";
    }

}
