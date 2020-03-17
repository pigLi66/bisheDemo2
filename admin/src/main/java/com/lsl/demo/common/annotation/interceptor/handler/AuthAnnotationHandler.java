package com.lsl.demo.common.annotation.interceptor.handler;

import cn.hutool.core.util.StrUtil;
import com.lsl.demo.model.sys.entity.UserEntity;
import com.lsl.demo.model.sys.service.IUserService;
import com.lsl.demo.common.annotation.interceptor.Auth;
import com.lsl.demo.common.enums.Actor;
import com.lsl.demo.common.exceptions.BaseException;
import com.lsl.demo.common.exceptions.LowUserLevelException;
import com.lsl.demo.common.exceptions.UnLoginException;
import com.lsl.demo.common.token.Token;
import com.lsl.demo.common.token.TokenBuilder;
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
        UserEntity userEntity = this.userService.getById(token.getUserId());
        this.judgeType(auth, userEntity);
        this.judgeUser(auth, userEntity);
    }

    /**
     * 判断用户角色是否符合
     * @param auth
     * @param userEntity
     */
    private void judgeUser(Auth auth, UserEntity userEntity) {
        if (0 == auth.users().length) {
            return;
        }
        if (!Arrays.asList(auth.users()).contains(userEntity.getName())) {
            throw new LowUserLevelException();
        }
    }

    /**
     * 判断角色类别是否符合要求
     * @param auth
     * @param userEntity
     */
    private void judgeType(Auth auth, UserEntity userEntity) {
        if (Actor.ALL == auth.type()) {
            return;
        }
        if (!auth.type().getType().equals(userEntity.getType())) {
            throw new LowUserLevelException();
        }
    }

}
