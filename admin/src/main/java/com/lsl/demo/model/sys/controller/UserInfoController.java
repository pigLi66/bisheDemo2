package com.lsl.demo.model.sys.controller;


import com.lsl.demo.common.annotation.interceptor.Auth;
import com.lsl.demo.common.base.controller.BaseController;
import com.lsl.demo.common.enums.Actor;
import com.lsl.demo.common.enums.Operation;
import com.lsl.demo.model.sys.dto.UserInfoDto;
import com.lsl.demo.model.sys.entity.UserInfoEntity;
import com.lsl.demo.model.sys.service.IUserInfoService;
import com.lsl.demo.utils.global.BaseContextHandler;
import com.lsl.demo.utils.validate.AddGroup;
import com.lsl.demo.utils.validate.UpdateGroup;
import com.lsl.demo.utils.validate.ValidatorUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Auth
@Api("用户信息相关接口")
@RestController
@RequestMapping("/sys/user/info")
public class UserInfoController extends BaseController<UserInfoEntity, IUserInfoService> {

    @ApiOperation("新增用户信息")
    @PostMapping
    public ResponseEntity<String> saveUserInfo(@RequestBody UserInfoDto dto) {
        ValidatorUtil.validateEntity(dto, AddGroup.class);
        return new ResponseEntity<>(this.service.saveUserInfo(dto), HttpStatus.OK);
    }

    @ApiOperation("获取当前用户信息")
    @GetMapping
    public ResponseEntity<UserInfoEntity> getUserInfo() {
        return ResponseEntity.ok(this.service.getUserInfo(BaseContextHandler.getUserId()));
    }

    @Auth(type = Actor.ADMIN)
    @ApiOperation("获取一个用户的信息")
    @GetMapping("/{userId}")
    public ResponseEntity<UserInfoEntity> getUserInfo(@PathVariable String userId) {
        return new ResponseEntity<>(this.service.getUserInfo(userId), HttpStatus.OK);
    }

    @PutMapping
    @ApiOperation("保存或者更新用户信息")
    public ResponseEntity<String> upUserInfo(@RequestBody UserInfoDto dto) {
        ValidatorUtil.validateEntity(dto, UpdateGroup.class);
        dto.setUserId(BaseContextHandler.getUserId());
        System.out.println(dto);
        return new ResponseEntity<>(this.service.upUserInfo(dto), HttpStatus.OK);
    }

    @Auth(type = Actor.ADMIN)
    @ApiOperation("根据id删除用户信息")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUserInfo(@PathVariable String id) {
        this.service.removeById(id);
        return new ResponseEntity<>(Operation.SUCCESS.get(), HttpStatus.OK);
    }

}
