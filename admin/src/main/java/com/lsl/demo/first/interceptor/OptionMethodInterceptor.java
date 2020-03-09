package com.lsl.demo.first.interceptor;

import org.apache.commons.httpclient.HttpStatus;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 与客户端进行通讯，设置通用的响应报文头，并拦截option 请求
 * @author lisiliang
 * @since 2020/3/7
 */
@Configuration
public class OptionMethodInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Origin,Content-Type,Accept,token,x-requested-with,Content-Disposition");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json; charset=utf-8");

        if (request.getMethod().equalsIgnoreCase("option")) {
            return false;
        }

        return super.preHandle(request, response, handler);
    }
}
