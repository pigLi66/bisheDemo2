package com.lsl.demo.model.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lsl.demo.common.exceptions.BaseException;
import com.lsl.demo.common.server.UploadJpgToMyLinux;
import com.lsl.demo.utils.BaseContextHandler;
import com.lsl.demo.utils.ConvertUtil;
import com.lsl.demo.common.annotation.aop.MyTest;
import com.lsl.demo.common.exceptions.UserException;
import com.lsl.demo.model.sys.dto.LoginDto;
import com.lsl.demo.model.sys.entity.UserEntity;
import com.lsl.demo.model.sys.mapper.UserMapper;
import com.lsl.demo.model.sys.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.httpclient.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    @MyTest
    @Override
    public UserEntity login(LoginDto dto) {
        Wrapper<UserEntity> wrapper = new QueryWrapper<UserEntity>().lambda()
                .eq(UserEntity::getName, dto.getName())
                .eq(UserEntity::getPassword, dto.getPassword())
                .eq(UserEntity::getType, dto.getType());
        UserEntity user = this.baseMapper.selectOne(wrapper);
        if (Objects.isNull(user)) {
            throw new UserException("账号不存在或者密码错误");
        }
        return user;
    }

    @Override
    public String register(LoginDto dto) {
        Wrapper<UserEntity> wrapper = new QueryWrapper<UserEntity>().lambda()
                .eq(UserEntity::getName, dto.getName())
                .eq(UserEntity::getType, dto.getType());
        UserEntity user = this.baseMapper.selectOne(wrapper);
        if (Objects.nonNull(user)) {
            throw new UserException("账号已存在");
        }
        user = ConvertUtil.sourceToTarget(dto, UserEntity.class);
        user.setType(String.valueOf(dto.getType()));
        this.baseMapper.insert(user);
        return user.getId();
    }

    @Override
    public String savePic(MultipartFile file) {
        String fileUrl;
        try {
            fileUrl = UploadJpgToMyLinux.upload(file.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
            throw new BaseException("", HttpStatus.SC_INTERNAL_SERVER_ERROR);
        }
        UserEntity userEntity = this.getById(BaseContextHandler.getUserId());
        userEntity.setPictureUrl(fileUrl);
        this.updateById(userEntity);
        return fileUrl;
    }
}
