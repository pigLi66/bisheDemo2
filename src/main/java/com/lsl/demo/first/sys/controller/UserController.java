package com.lsl.demo.first.sys.controller;


import com.lsl.demo.first.utils.BaseContextHandler;
import com.lsl.demo.first.utils.token.Token;
import com.lsl.demo.first.utils.validate.ValidatorUtil;
import com.lsl.demo.first.sys.dto.LoginDto;
import com.lsl.demo.first.sys.entity.UserEntity;
import com.lsl.demo.first.sys.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lsl_ja
 * @since 2020-01-03
 */
@RestController
@RequestMapping("/user")
@Api("用户相关接口")
public class UserController {

    @Autowired
    private IUserService userService;

    @ApiOperation("登陆用")
    @PostMapping("/login")
    public ResponseEntity<String> judgeUser(@RequestBody LoginDto dto, HttpServletResponse response) {
        System.out.println("login");
        System.out.println(dto);
        ValidatorUtil.validateEntity(dto);

        UserEntity user = userService.login(dto);
        System.out.println(user);

        Token token = new Token(user.getId());
        BaseContextHandler.setUserId(token.getUserId());
        Cookie cookie = new Cookie("token", token.getToken());
        cookie.setHttpOnly(false);
        cookie.setMaxAge((int) Token.DEFAULT_TIME_OUT / 1000 + 100);
        response.addCookie(cookie);
        return ResponseEntity.ok("登录成功");
    }

    @ApiOperation("注册用")
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody LoginDto dto) {
        ValidatorUtil.validateEntity(dto);
        userService.register(dto);
        return ResponseEntity.ok("注册成功");
    }


}
