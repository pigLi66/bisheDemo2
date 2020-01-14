package com.lsl.demo.first.interceptor;

import cn.hutool.core.map.MapUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.lsl.demo.first.Utils.BaseContextHandler;
import com.lsl.demo.first.Utils.Token;
import com.lsl.demo.first.Utils.TokenBuilder;
import org.apache.commons.httpclient.HttpStatus;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Map;
import java.util.Objects;

/**
 * @author lisiliang
 * @since 2020/1/10
 */
@Component
public class AuthenticationInterceptor extends HandlerInterceptorAdapter {

    // 留一个以后可能用的东西，以后实施去除keep-alive 让每次的http 都重新进行请求，检测option
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,OPTIONS");
        response.setHeader("Access-Control-Allow-Headers","Origin,Content-Type,Accept,token,x-requested-with,Content-Disposition");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");

        if (request.getMethod().equalsIgnoreCase("option")) {
            return resp(response, HttpStatus.getStatusText(HttpStatus.SC_OK), HttpStatus.SC_OK);
        }

        String t = request.getHeader(Token.HEADER_TOKEN);
        if (Objects.isNull(t)) {
            return resp(response, HttpStatus.getStatusText(HttpStatus.SC_NON_AUTHORITATIVE_INFORMATION), HttpStatus.SC_NON_AUTHORITATIVE_INFORMATION);
        }

        Token token = TokenBuilder.buildToken(t);

        // token 已经失效
        if (new Date().compareTo(token.getDate()) > 0) {
            return resp(response, HttpStatus.getStatusText(HttpStatus.SC_OK), HttpStatus.SC_OK);
        } else {
            token.refresh();
            BaseContextHandler.setUserId(token.getUserId());

            // 学会控制缓存后，更新缓存的token

            return super.preHandle(request, response, handler);
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }

    private boolean resp(HttpServletResponse response, String msg, int code) throws Exception{
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json; charset=utf-8");
        response.setStatus(HttpStatus.SC_OK);
        Map<String, Object> map = MapUtil.newHashMap();
        map.put("code", code);
        map.put("message", msg);
        map.put("timestamp", new Date().getTime());
        PrintWriter writer = response.getWriter();
        JSONObject object = JSONUtil.parseFromMap(map);
        writer.write(object.toString());
        writer.close();
        return false;
    }

}
