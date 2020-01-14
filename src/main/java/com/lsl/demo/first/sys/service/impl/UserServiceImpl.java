package com.lsl.demo.first.sys.service.impl;

import com.lsl.demo.first.sys.Dto.LoginDto;
import com.lsl.demo.first.sys.entity.User;
import com.lsl.demo.first.sys.mapper.UserMapper;
import com.lsl.demo.first.sys.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lsl_ja
 * @since 2020-01-03
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Override
    public User login(LoginDto dto) {
        return null;
    }

    @Override
    public User register(LoginDto dto) {
        return null;
    }
}
