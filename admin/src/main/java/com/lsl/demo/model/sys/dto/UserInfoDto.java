package com.lsl.demo.model.sys.dto;

import com.lsl.demo.utils.validate.AddGroup;
import com.lsl.demo.utils.validate.UpdateGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

/**
 * @author lisiliang
 * @since 2020/2/15
 */
@ApiModel
@Data
public class UserInfoDto {

    /**
     * 真实姓名
     */
    @ApiModelProperty("用户的真实姓名")
    @NotBlank(message = "真实姓名不能为空", groups = {UpdateGroup.class})
    private String realName;

    /**
     * 邮箱
     */
    @ApiModelProperty("用户的邮箱")
    private String email;

    /**
     * 电话
     */
    @ApiModelProperty("用户的电话")
    private String phone;

    /**
     * 用户主键
     */
    @ApiModelProperty("用户的主键id")
    @NotBlank(message = "用户id不能为空", groups = {AddGroup.class})
    private String userId;

    /**
     * 用户名
     */
    @ApiModelProperty("用户名")
    @NotBlank(message = "用户名不能为空", groups = {UpdateGroup.class})
    private String userName;

    /**
     * 用户简介
     */
    @ApiModelProperty("用户简介")
    private String profile;

    /**
     * 出生年月日
     */
    @ApiModelProperty("用户的出生年月日")
    private LocalDate birthday;

    /**
     * 性别：0表示男，1表示女
     */
    @ApiModelProperty("用户的性别，0标识男，1标识女")
    @NotBlank(message = "性格不能为空", groups = {UpdateGroup.class})
    @Size(max = 1, message = "性别不合法", groups = {UpdateGroup.class})
    private String sex;


}
