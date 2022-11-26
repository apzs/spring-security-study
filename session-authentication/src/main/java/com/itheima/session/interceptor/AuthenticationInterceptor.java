package com.itheima.session.interceptor;

import com.itheima.session.entity.UserEntity;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;
import java.io.PrintWriter;

public class AuthenticationInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object object = request.getSession().getAttribute(UserEntity.SESSION_USER_KEY);
        if (object == null) {
            writeContent(response,"请登录账户");
            return false;
        }
        UserEntity userEntity = (UserEntity) object;
        request.getRequestURI();
        String requestURI = request.getRequestURI();

        // 如果访问 /r/r1 必须要有 p1权限
        if( requestURI.contains("/r/r1") && !userEntity.getAuthorities().contains("p1")){
            writeContent(response,"没有访问r1的权限");
            return false;
        }
        // 如果访问 /r/r2 必须要有p2权限
        if(requestURI.contains("/r/r2") && !userEntity.getAuthorities().contains("p2")){
            writeContent(response,"没有访问r2的权限");
            return false;
        }
        //
        return true;
    }

    //响应信息给客户端
    private void writeContent(HttpServletResponse response, String msg) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.print(msg);
        writer.close();
    }

}
