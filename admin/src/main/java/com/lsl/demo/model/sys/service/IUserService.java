package com.lsl.demo.model.sys.service;

import com.lsl.demo.model.sys.dto.LoginDto;
import com.lsl.demo.model.sys.entity.UserEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lsl_ja
 * @since 2020-01-03
 */
public interface IUserService extends IService<UserEntity> {

    /**
     * 登陆判断
     * @param dto
     * @return
     */
    UserEntity login(LoginDto dto);

    /**
     * 注册判断
     * @param dto
     */
    String register(LoginDto dto);

    /**
     * 保存图片
     * @param file
     * @return
     */
    String savePic(MultipartFile file);

}
