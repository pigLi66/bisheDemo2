package com.lsl.demo.model.common.dto;

import com.lsl.demo.utils.validate.AddGroup;
import com.lsl.demo.utils.validate.UpdateGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author lisiliang
 * @since 2020/2/15
 */

@ApiModel
@Data
public class CommentDto {

    /**
     * 用户评论
     */
    @ApiModelProperty("用户评论内容")
    @NotBlank(message = "评论不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String comment;

}
