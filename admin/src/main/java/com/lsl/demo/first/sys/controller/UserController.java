package com.lsl.demo.first.sys.controller;


import com.lsl.demo.first.sys.dto.LoginResponse;
import com.lsl.demo.first.utils.BaseContextHandler;
import com.lsl.demo.first.utils.annotation.aop.MyTest;
import com.lsl.demo.first.utils.annotation.interceptor.Auth;
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


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lsl_ja
 * @since 2020-01-03
 */
@RestController
@RequestMapping("/sys/user")
@Api("用户相关接口")
public class UserController {

    @Autowired
    private IUserService userService;

    @MyTest
    @ApiOperation("登陆用")
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginDto dto) {
        ValidatorUtil.validateEntity(dto);
        UserEntity user = userService.login(dto);
        Token token = new Token(user.getId());
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setMessage("登陆成功");
        loginResponse.setTimeOut(Token.DEFAULT_TIME_OUT.toString());
        loginResponse.setToken(token.getToken());
        return ResponseEntity.ok(loginResponse);

        /*
        Cookie cookie = new Cookie("token", token.getToken());
        cookie.setHttpOnly(false);
        cookie.setMaxAge((int) Token.DEFAULT_TIME_OUT / 1000 + 100);
        response.addCookie(cookie);
        */
    }

    @MyTest
    @ApiOperation("注册用")
    @PostMapping("/register")
    public ResponseEntity<LoginResponse> register(@RequestBody LoginDto dto) {
        ValidatorUtil.validateEntity(dto);
        String userId = userService.register(dto);
        Token token = new Token(userId);
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(token.getToken());
        loginResponse.setTimeOut(token.getTimeOut().toString());
        loginResponse.setMessage("注册成功");
        return ResponseEntity.ok(loginResponse);
    }

    @MyTest
    @Auth
    @GetMapping("/picture")
    public ResponseEntity<String> getPicUrl() {
        return ResponseEntity.ok(this.userService.getById(BaseContextHandler.getUserId()).getPictureUrl());
    }

}
