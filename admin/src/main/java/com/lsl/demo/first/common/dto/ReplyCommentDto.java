package com.lsl.demo.first.common.dto;

import com.lsl.demo.first.utils.validate.AddGroup;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author lisiliang
 * @since 2020/3/9
 */
@Data
public class ReplyCommentDto {

    @NotBlank(message = "评论不能为空", groups = {AddGroup.class})
    private String comment;

    private String userId;

    @NotBlank(message = "评论回复id不能为空", groups = {AddGroup.class})
    private String commentId;

}
