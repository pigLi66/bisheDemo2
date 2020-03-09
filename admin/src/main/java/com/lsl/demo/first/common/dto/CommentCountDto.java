package com.lsl.demo.first.common.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @author lisiliang
 * @since 2020/2/15
 */

@ApiModel
@Data
public class CommentCountDto {

    @NotBlank(message = "commentId不能为空")
    private String commentId;

    private String userId;

    @ApiModelProperty("统计评论的类别，点赞数使用type=0")
    @NotBlank(message = "type不能为空")
    @Size(max = 1)
    private String type;

}
