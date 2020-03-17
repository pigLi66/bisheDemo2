package com.lsl.demo.model.common.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author lisiliang
 * @since 2020/2/15
 */
@ApiModel
@Data
public class LevelDto {

    /**
     * 电影id
     */
    @NotBlank(message = "电影id不能为空")
    @ApiModelProperty("电影id")
    private String movieId;

    /**
     * 用户id
     */
    @NotBlank(message = "用户id不能为空")
    @ApiModelProperty("用户id")
    private String userId;


    /**
     * 评级（1--10）
     */
    @Max(10)
    @Min(1)
    @NotNull
    @ApiModelProperty("电影评级 1 - 10")
    private Double level;

}
