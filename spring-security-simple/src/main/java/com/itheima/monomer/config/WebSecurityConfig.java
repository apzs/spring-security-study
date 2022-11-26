package com.itheima.monomer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

//开启WebSecurity功能
@EnableWebSecurity
@Configuration
public class WebSecurityConfig {

    /**
     * SpringSecurity的安全拦截机制
     * @param http
     * @return
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests(auth->{
            //   /r/r1请求需要p1权限
            auth.requestMatchers("/r/r1").hasAuthority("p1");
        }).authorizeHttpRequests(auth->{
            //  /r/r2请求需要p2权限
            auth.requestMatchers("/r/r2").hasAuthority("p2");
        }).authorizeHttpRequests(auth->{
            //  /r下的所有请求都必须认证(登录) ，包括/r请求、登录成功页也需要登录后才能访问
            auth.requestMatchers("/r/**","/login-success").authenticated();
        }).authorizeHttpRequests(auth->{
            // 放行其他所有请求
            auth.anyRequest().permitAll();
        })
        // 允许表单登录
        .formLogin()
        // 登录成功后跳转到的url
        .successForwardUrl("/login-success");
        return http.build();
    }

    /**
     * 密码编码器，指明密码的加密方式
     * (用户输入的密码与数据库中的密码的比较规则)
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        // 字符串匹配（直接比较两个字符串是否相等）
        return NoOpPasswordEncoder.getInstance();
    }

    /**
     * 用户信息服务
     * 指定有哪些用户，这些用户分别具有哪些权限
     * @return
     */
    @Bean
    public UserDetailsService userDetailsService() {
        // 用户详情存储在内存的管理器
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("admin").password("admin").authorities("p1","p2").build());
        manager.createUser(User.withUsername("zhangsan").password("123").authorities("p1").build());
        manager.createUser(User.withUsername("lisi").password("456").authorities("p2").build());
        // 用户必须设置 角色或权限，否则会报错，直接启动失败
        manager.createUser(User.withUsername("wangwu").password("789").roles("anonymous").build());
        return manager;
    }

}
