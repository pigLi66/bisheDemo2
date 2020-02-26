package com.lsl.demo.first.sys.service;

import com.lsl.demo.first.sys.dto.UserInfoDto;
import com.lsl.demo.first.sys.entity.UserInfoEntity;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户信息表 服务类
 * </p>
 *
 * @author lsl_ja
 * @since 2020-02-13
 */
public interface IUserInfoService extends IService<UserInfoEntity> {

    /**
     * 保存用户信息，能够判断用户是否已经存在和用户的信息是否已经存在
     * @param dto
     * @return
     */
    String saveUserInfo(UserInfoDto dto);

    /**
     * 修改用户信息
     * @param dto
     */
    String upUserInfo(UserInfoDto dto);

    /**
     * 获取用户信息
     * @param userId
     * @return
     */
    UserInfoEntity getUserInfo(String userId);

    /**
     * 删除用户信息
     * @param id
     */
    void deleteUserInfo(String id);

}
