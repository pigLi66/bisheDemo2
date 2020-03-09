package com.lsl.demo.first.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author lsl_ja
 * @since 2020-03-09
 */
@Data
@Accessors(chain = true)
@TableName("reply_comment")
public class ReplyCommentEntity{

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private String id;

    private String comment;

    private String userId;

    private String commentId;

    @TableLogic
    private String valid;

}
