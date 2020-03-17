package com.lsl.demo.model.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lsl.demo.model.sys.dto.UserInfoDto;
import com.lsl.demo.model.sys.entity.UserInfoEntity;
import com.lsl.demo.model.sys.mapper.UserInfoMapper;
import com.lsl.demo.model.sys.service.IUserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lsl.demo.model.sys.service.IUserService;
import com.lsl.demo.utils.ConvertUtil;
import com.lsl.demo.common.enums.Operation;
import com.lsl.demo.common.exceptions.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author lsl_ja
 * @since 2020-02-13
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfoEntity> implements IUserInfoService {

    @Autowired
    private IUserService userService;

    @Override
    public String saveUserInfo(UserInfoDto dto) {
        QueryWrapper<UserInfoEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", dto.getUserId());
        if (Objects.nonNull(this.baseMapper.selectOne(wrapper))
                || Objects.isNull(this.userService.getById(dto.getUserId()))) {
            throw new BusinessException(Operation.SUCCESS.get());
        }

        UserInfoEntity entity = ConvertUtil.sourceToTarget(dto, UserInfoEntity.class);
        this.baseMapper.insert(entity);

        entity = this.baseMapper.selectOne(wrapper);
        return entity.getId();
    }

    @Override
    public String upUserInfo(UserInfoDto dto) {
        UserInfoEntity entity = ConvertUtil.sourceToTarget(dto, UserInfoEntity.class);

        QueryWrapper<UserInfoEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", dto.getUserId());
        UserInfoEntity entityDB = this.baseMapper.selectOne(queryWrapper);

        if (Objects.isNull(entityDB))  {
            this.baseMapper.insert(entity);
        } else {
            entity.setId(entityDB.getId());
            this.baseMapper.updateById(entity);
        }
        return entity.getId();
    }

    @Override
    public UserInfoEntity getUserInfo(String userId) {
        QueryWrapper<UserInfoEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId);
        wrapper.eq("valid", 0);
        return this.baseMapper.selectOne(wrapper);
    }

    @Override
    public void deleteUserInfo(String id) {
        this.baseMapper.deleteById(id);
    }
}
