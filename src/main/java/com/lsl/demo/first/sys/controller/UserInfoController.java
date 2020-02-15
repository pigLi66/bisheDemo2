package com.lsl.demo.first.sys.controller;


import com.lsl.demo.first.sys.dto.UserInfoDto;
import com.lsl.demo.first.sys.entity.UserInfoEntity;
import com.lsl.demo.first.sys.service.IUserInfoService;
import com.lsl.demo.first.sys.service.IUserService;
import com.lsl.demo.first.utils.enums.Operation;
import com.lsl.demo.first.utils.validate.AddGroup;
import com.lsl.demo.first.utils.validate.UpdateGroup;
import com.lsl.demo.first.utils.validate.ValidatorUtil;
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
@RestController
@RequestMapping("/sys/user/info")
public class UserInfoController {

    @Autowired
    private IUserInfoService userInfoService;

    @PostMapping
    public ResponseEntity<String> saveUserInfo(UserInfoDto dto) {
        ValidatorUtil.validateEntity(dto, AddGroup.class);
        return new ResponseEntity<>(this.userInfoService.saveUserInfo(dto), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<String> upUserInfo(UserInfoDto dto) {
        ValidatorUtil.validateEntity(dto, UpdateGroup.class);
        this.userInfoService.upUserInfo(dto);
        return new ResponseEntity<>(Operation.SUCCESS.get(), HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserInfoEntity> getUserInfo(@PathVariable String userId) {
        return new ResponseEntity<>(this.userInfoService.getUserInfo(userId), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUserInfo(@PathVariable String id) {
        this.userInfoService.removeById(id);
        return new ResponseEntity<>(Operation.SUCCESS.get(), HttpStatus.OK);
    }

}
