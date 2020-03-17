package com.lsl.demo.model.sys.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author lisiliang
 * @since 2020/3/2
 */
@Data
public class ArtistDto {
    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 性别
     */
    private String sex;

    /**
     * 出生日期
     */
    private String birthday;

    /**
     * 出生地
     */
    private String birthPlace;

    /**
     * 职业
     */
    private String position;

    /**
     * 简介
     */
    private String profile;

}
