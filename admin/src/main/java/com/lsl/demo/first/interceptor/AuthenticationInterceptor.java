package com.lsl.demo.first.interceptor;

import cn.hutool.core.util.StrUtil;
import com.lsl.demo.first.utils.BaseContextHandler;
import com.lsl.demo.first.utils.annotation.interceptor.handler.Handler;
import com.lsl.demo.first.utils.token.Token;
import com.lsl.demo.first.utils.token.TokenBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户权限拦截器
 * @author lisiliang
 * @since 2020/1/10
 */
@Configuration
public class AuthenticationInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private Handler annotationHandler;

    // 留一个以后可能用的东西，以后实施去除keep-alive 让每次的http 都重新进行请求，检测option
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 如果请求中带有token， 则刷新token
        String tokenEncode = request.getHeader(Token.HEADER_TOKEN);
        if (!StrUtil.isBlank(tokenEncode)) {
            Token token = TokenBuilder.buildByEncode(tokenEncode);
            BaseContextHandler.setUserId(token.getUserId());
            token.refresh();
            response.setHeader(Token.HEADER_TOKEN, token.getToken());
        }
        // 处理方法请求
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            annotationHandler.handle(handlerMethod.getMethod(), request);
        }
        return super.preHandle(request, response, handler);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        BaseContextHandler.clear();
        super.afterCompletion(request, response, handler, ex);
    }

}
