package com.lsl.demo.first.sys.controller;


import com.lsl.demo.first.Utils.BaseContextHandler;
import com.lsl.demo.first.Utils.Token;
import com.lsl.demo.first.Utils.TokenBuilder;
import com.lsl.demo.first.Utils.ValidatorUtil;
import com.lsl.demo.first.sys.Dto.LoginDto;
import com.lsl.demo.first.sys.entity.User;
import com.lsl.demo.first.sys.service.IUserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lsl_ja
 * @since 2020-01-03
 */
@Controller
@RequestMapping()
@Api("用户相关接口")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping("/login")
    public String login(){
        return "/user/login.html";
    }


    @PostMapping("/login")
    public ResponseEntity<String> judgeUser(@RequestBody LoginDto dto, HttpServletResponse response) {
        ValidatorUtil.validateEntity(dto);

        User user = userService.login(dto);
        if (Objects.nonNull(user)) {
            Token token = new Token(user.getId());
            BaseContextHandler.setUserId(token.getUserId());
            Cookie cookie = new Cookie("token", token.getToken());
            cookie.setHttpOnly(false);
            cookie.setMaxAge((int)Token.DEFAULT_TIME_OUT/1000 + 100);
            response.addCookie(cookie);
            return new ResponseEntity<String>("登录成功", HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("登录失败", HttpStatus.OK);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody LoginDto dto) {
        ValidatorUtil.validateEntity(dto);

        User user = userService.register(dto);
        return new ResponseEntity<String>("注册成功", HttpStatus.OK);
    }


}
