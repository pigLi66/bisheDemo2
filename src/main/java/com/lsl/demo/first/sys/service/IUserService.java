package com.lsl.demo.first.sys.service;

import com.lsl.demo.first.sys.dto.LoginDto;
import com.lsl.demo.first.sys.entity.UserEntity;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lsl_ja
 * @since 2020-01-03
 */
public interface IUserService extends IService<UserEntity> {

    UserEntity login(LoginDto dto);

    void register(LoginDto dto);

}
