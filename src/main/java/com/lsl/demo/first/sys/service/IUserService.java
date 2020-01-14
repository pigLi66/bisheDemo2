package com.lsl.demo.first.sys.service;

import com.lsl.demo.first.Utils.Token;
import com.lsl.demo.first.sys.Dto.LoginDto;
import com.lsl.demo.first.sys.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lsl_ja
 * @since 2020-01-03
 */
public interface IUserService extends IService<User> {

    User login(LoginDto dto);

    User register(LoginDto dto);

}
