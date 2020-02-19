package com.lsl.demo.first.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import com.lsl.demo.first.utils.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author lsl_ja
 * @since 2020-01-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_user")
public class UserEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    private String name;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 用户的类型，0表示管理员，1表示普通用户，2白表示导演，3表示演员
     */
    private String type;

    /**
     * 图片url
     */
    private String pictureUrl;

}
