package com.lsl.demo.model.common.dto;

import io.swagger.annotations.ApiModel;

/**
 * @author lisiliang
 * @since 2020/3/4
 */
@ApiModel
public class CommentCollectDto {

    /**
     * 评论类别名
     */
    private String name;

    /**
     * 简介
     */
    private String profile;

    /**
     * 图片地址
     */
    private String pictureUrl;

}
