package com.lsl.demo.first.common.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author lisiliang
 * @since 2020/2/15
 */
@Data
public class CommentDto {

    /**
     * 电影id
     */
    @NotBlank(message = "moviId不能为空")
    private String movieId;

    /**
     * 用户id
     */
    @NotBlank(message = "userId不能为空")
    private String userId;

    /**
     * 用户评论
     */
    @NotBlank(message = "评论不能为空")
    private String comment;

}
