package com.lsl.demo.first.common.dto;

import lombok.Data;

/**
 * @author lisiliang
 * @since 2020/3/6
 */
@Data
public class CommentInfoRSDto {

    /**
     * 评论类别名
     */
    private String collect;

    /**
     * 评论所属类别标识id
     */
    private String collectIds;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 图片url
     */
    private String pictureUrl;

    /**
     * 评论id
     */
    private String commentId;

    /**
     * 用户评论
     */
    private String comment;

    /**
     * 点赞数
     */
    private Integer count;

}
