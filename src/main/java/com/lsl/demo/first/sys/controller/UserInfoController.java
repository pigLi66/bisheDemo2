package com.lsl.demo.first.sys.controller;


import com.lsl.demo.first.sys.dto.UserInfoDto;
import com.lsl.demo.first.sys.entity.UserInfoEntity;
import com.lsl.demo.first.sys.service.IUserInfoService;
import com.lsl.demo.first.utils.BaseContextHandler;
import com.lsl.demo.first.utils.enums.Operation;
import com.lsl.demo.first.utils.validate.AddGroup;
import com.lsl.demo.first.utils.validate.UpdateGroup;
import com.lsl.demo.first.utils.validate.ValidatorUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 用户信息表 前端控制器
 * </p>
 *
 * @author lsl_ja
 * @since 2020-02-13
 */
@Api("用户信息相关接口")
@RestController
@RequestMapping("/sys/user/info")
public class UserInfoController {

    @Autowired
    private IUserInfoService userInfoService;

    @ApiOperation("新增用户信息")
    @PostMapping
    public ResponseEntity<String> saveUserInfo(UserInfoDto dto) {
        ValidatorUtil.validateEntity(dto, AddGroup.class);
        return new ResponseEntity<>(this.userInfoService.saveUserInfo(dto), HttpStatus.OK);
    }

    @ApiOperation("获取当前用户信息")
    @GetMapping
    public ResponseEntity<UserInfoEntity> getUserInfo() {
        return ResponseEntity.ok(this.userInfoService.getUserInfo(BaseContextHandler.getUserId()));
    }

    @ApiOperation("获取一个用户的信息")
    @GetMapping("/{userId}")
    public ResponseEntity<UserInfoEntity> getUserInfo(@PathVariable String userId) {
        return new ResponseEntity<>(this.userInfoService.getUserInfo(userId), HttpStatus.OK);
    }

    @PutMapping
    @ApiOperation("更新用户信息")
    public ResponseEntity<String> upUserInfo(UserInfoDto dto) {
        ValidatorUtil.validateEntity(dto, UpdateGroup.class);
        this.userInfoService.upUserInfo(dto);
        return new ResponseEntity<>(Operation.SUCCESS.get(), HttpStatus.OK);
    }

    @ApiOperation("根据id删除用户信息")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUserInfo(@PathVariable String id) {
        this.userInfoService.removeById(id);
        return new ResponseEntity<>(Operation.SUCCESS.get(), HttpStatus.OK);
    }

}
