package com.lsl.demo.first.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDate;
import com.lsl.demo.first.utils.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户信息表
 * </p>
 *
 * @author lsl_ja
 * @since 2020-02-25
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_user_info")
public class UserInfoEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 电话
     */
    private String phone;

    /**
     * 用户主键
     */
    private String userId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 用户简介
     */
    private String profile;

    /**
     * 出生年月日
     */
    private LocalDate birthday;

    /**
     * 性别：0表示男，1表示女
     */
    private String sex;

}
