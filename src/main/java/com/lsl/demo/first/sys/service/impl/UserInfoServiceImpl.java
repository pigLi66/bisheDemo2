package com.lsl.demo.first.sys.service.impl;

import com.lsl.demo.first.sys.entity.UserInfoEntity;
import com.lsl.demo.first.sys.mapper.UserInfoMapper;
import com.lsl.demo.first.sys.service.IUserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
