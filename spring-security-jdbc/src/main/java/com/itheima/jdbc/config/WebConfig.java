package com.itheima.jdbc.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // 请求/路径，重定向到 /login-view ，重定向不拼前后缀
        registry.addViewController("/").setViewName("redirect:/login-view");
        // 请求/login-view路径跳转到的视图名为 login ，拼接前后缀后就变为了 /login.html
        // (这样就跳转到了自定义的登录页，而不是默认的/login请求的登录页)
        registry.addViewController("/login-view").setViewName("login");
    }
}
