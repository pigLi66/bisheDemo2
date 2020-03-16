package com.lsl.demo.first.utils.annotation.interceptor.handler;

import cn.hutool.core.util.StrUtil;
import com.lsl.demo.first.sys.entity.UserEntity;
import com.lsl.demo.first.sys.service.IUserService;
import com.lsl.demo.first.utils.annotation.interceptor.Auth;
import com.lsl.demo.first.utils.exceptions.BaseException;
import com.lsl.demo.first.utils.exceptions.LowUserLevelException;
import com.lsl.demo.first.utils.exceptions.UnLoginException;
import com.lsl.demo.first.utils.token.Token;
import com.lsl.demo.first.utils.token.TokenBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author lisiliang
 * @since 2020/3/9
 */
@Component
public class AuthAnnotationHandler extends BaseAnnotationHandler {

    @Autowired
    private IUserService userService;

    @Override
    public void headle(Method method, HttpServletRequest request) throws BaseException {
        Auth auth = method.getAnnotation(Auth.class);
        Token token;
        if (auth == null) {
            auth = method.getDeclaringClass().getAnnotation(Auth.class);
            if (auth == null) {
                return;
            }
        }
        String encodeToken = request.getHeader(Token.HEADER_TOKEN);
        if (StrUtil.isBlank(encodeToken)) {
            throw new UnLoginException();
        }
        token = TokenBuilder.buildByEncode(encodeToken);
        if (token.getTimeOut().getTime() < System.currentTimeMillis()) {
            throw new UnLoginException("登陆已过期");
        }
        if (0 == auth.users().length) {
            return;
        }
        UserEntity userEntity = this.userService.getById(token.getUserId());
        if (!Arrays.asList(auth.users()).contains(userEntity.getName())) {
            throw new LowUserLevelException();
        }
    }

}
