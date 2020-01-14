package com.lsl.demo.first.sys.service.impl;

import com.lsl.demo.first.sys.entity.UserInfo;
import com.lsl.demo.first.sys.mapper.UserInfoMapper;
import com.lsl.demo.first.sys.service.IUserInfoService;
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
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements IUserInfoService {

}
