package com.lsl.demo.first.common.dto;

import lombok.Data;

/**
 * @author lisiliang
 * @since 2020/3/9
 */
@Data
public class ReplyCommentInfoDto {

    /**
     * 回复的评论的内容
     */
    private String comment;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 图片url
     */
    private String pictureUrl;


}
