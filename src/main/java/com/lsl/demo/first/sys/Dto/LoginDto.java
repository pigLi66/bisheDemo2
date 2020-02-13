package com.lsl.demo.first.sys.Dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author lisiliang
 * @since 2020/1/11
 */
@Data
@ApiModel("用户登陆的实体类")
public class LoginDto {

    @NotBlank(message = "用户名不能为空")
    @ApiModelProperty("用户名")
    private String name;

    @NotBlank(message = "密码不能为空")
    @ApiModelProperty("密码")
    private String password;

}
