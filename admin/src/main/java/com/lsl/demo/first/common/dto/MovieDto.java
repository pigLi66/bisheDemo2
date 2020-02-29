package com.lsl.demo.first.common.dto;

import com.lsl.demo.first.utils.validate.AddGroup;
import com.lsl.demo.first.utils.validate.UpdateGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author lisiliang
 * @since 2020/2/17
 */

@ApiModel
@Data
public class MovieDto {

    /**
     * 电影名，不具有唯一性
     */
    @NotBlank(message = "电影名字不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String movieName;

    /**
     * 导演id
     */
    @ApiModelProperty("导演的名字")
    private String directorIds;

    /**
     * 演员id
     */
    private String performerIds;

    /**
     * 电影类别id
     */
    private String classIds;

    /**
     * 电影时长
     */
    @ApiModelProperty("电影时长")
    private Integer span;

    /**
     * 电影简介
     */
    @ApiModelProperty("电影简介")
    private String profile;

    /**
     * 上映时间
     */
    private String releaseTime;

}
