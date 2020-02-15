package com.lsl.demo.first.sys.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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

    @NotNull(message = "type不能为空")
    @Max(3)
    @Min(1)
    @ApiModelProperty("用户的类型，0表示管理员，1表示普通用户，2白表示导演，3表示演员")
    private Integer type;

}
