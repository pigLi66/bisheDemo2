package com.lsl.demo.first.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lsl.demo.first.Utils.ConvertUtil;
import com.lsl.demo.first.Utils.exceptions.UserException;
import com.lsl.demo.first.sys.Dto.LoginDto;
import com.lsl.demo.first.sys.entity.UserEntity;
import com.lsl.demo.first.sys.mapper.UserMapper;
import com.lsl.demo.first.sys.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lsl_ja
 * @since 2020-01-03
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements IUserService {

    @Override
    public UserEntity login(LoginDto dto) {
        System.out.println("service.login");
        QueryWrapper<UserEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("name", dto.getName());
        wrapper.eq("pwd", dto.getPassword());
        wrapper.eq("type", "1");
        UserEntity user = this.baseMapper.selectOne(wrapper);
        if (Objects.isNull(user)) {
            throw new UserException("账号不存在或者密码错误");
        }
        return user;
    }

    @Override
    public void register(LoginDto dto) {
        QueryWrapper<UserEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("name", dto.getName());
        wrapper.eq("type", "1");
        UserEntity user = this.baseMapper.selectOne(wrapper);
        if (Objects.nonNull(user)) {
            throw new UserException("账号已存在");
        }
        user = ConvertUtil.sourceToTarget(dto, UserEntity.class);
        this.baseMapper.insert(user);
    }
}
