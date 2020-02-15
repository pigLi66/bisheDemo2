package com.lsl.demo.first.common.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author lisiliang
 * @since 2020/2/15
 */
@Data
public class CommentCountDto {

    @NotBlank(message = "commentId不能为空")
    private String commentId;

    @NotBlank(message = "userId不能为空")
    private String userId;

    @NotBlank(message = "type不能为空")
    private String type;

}
