package com.itheima.jdbc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

//开启WebSecurity功能
@EnableWebSecurity

@EnableGlobalMethodSecurity(securedEnabled = true,prePostEnabled = true,jsr250Enabled = true)
@Configuration
public class WebSecurityConfig {

    /**
     * SpringSecurity的安全拦截机制
     *
     * @param http
     * @return
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> {
                    //   /r/r1请求需要p1权限
                    auth.requestMatchers("/r/r1").hasAuthority("p1");
                }).authorizeHttpRequests(auth -> {
                    //  /r/r2请求需要p2权限
                    auth.requestMatchers("/r/r2").hasAuthority("p2");
                }).authorizeHttpRequests(auth -> {
                    //  /r下的所有请求都必须认证(登录) ，包括/r请求、登录成功页也需要登录后才能访问
                    auth.requestMatchers("/r/**", "/login-success").authenticated();
                }).authorizeHttpRequests(auth -> {
                    // 放行其他所有请求
                    auth.anyRequest().permitAll();
                })
                // 允许表单登录
                .formLogin(formLogin -> {
                    // 登录页面（即输入用户名和密码的页面，默认loginPage和loginProcessingUrl都是/login ）
                    // 如果只配置loginPage而不配置loginProcessingUrl，那么loginProcessingUrl默认就是loginPage
                    // 如果只配置loginProcessUrl，就会用不了自定义登陆页面，security会使用自带的默认登陆页面。
                    // 如果跳转页面是/login，提交表单/toLogin，那最好就是loginPage配/login,loginProcessingUrl配/toLogin
                    formLogin.loginPage("/login-view")
                            // 验证用户名和密码是否正确的接口（即form表单里action的值）
                            .loginProcessingUrl("/login")
                            // 登录成功后跳转到的url
                            .successForwardUrl("/login-success");
                })
                // 退出操作将会发生： HTTP Session失效、清除SecurityContextHolder、跳转到 /login-view?logout
                .logout(logout->{
                    logout
                            .logoutUrl("/logout")
                            .logoutSuccessUrl("/login‐view?logout")
                            // 退出登录成功后的处理
                            // .logoutSuccessHandler(logoutSuccessHandler)
                            // 执行退出登录操作的处理(不管是否退出成功)
                            // .addLogoutHandler(logoutHandler)
                            // 使 HTTP Session 失效
                            .invalidateHttpSession(true);
                })
                // 在cookie中使用CsrfToken（默认使用HttpSessionCsrfTokenRepository存储在HttpSession中）
                // 自定义登录页后不会向cookie中存入XSRF-TOKEN，而使用默认的登录页会存入
                // .csrf(csrf -> csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()));
                // 禁用csrf
                .csrf(csrf->{csrf.disable();})
                // session过期后的配置
                .sessionManagement(management -> {
                    management
                            // always: 如果没有session存在就创建一个
                            // ifRequired : 如果需要就创建一个Session（默认）登录时
                            // never: SpringSecurity 将不会创建Session，但是如果应用中其他地方创建了Session，那么Spring Security将会使用它。
                            // stateless: SpringSecurity将绝对不会创建Session，也不使用Session
                            .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                            // 设置过期后跳转到的url(此方法已经不能用了)
                            //.expiredUrl("/login‐view?error=EXPIRED_SESSION")
                            // session失效后跳转到的url
                            .invalidSessionUrl("/login‐view?error=INVALID_SESSION");
                });
        return http.build();
    }

    /**
     * 密码编码器，指明密码的加密方式
     * (用户输入的密码与数据库中的密码的比较规则)
     *
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        // 字符串匹配（直接比较两个字符串是否相等）
        return new BCryptPasswordEncoder();
    }

}
