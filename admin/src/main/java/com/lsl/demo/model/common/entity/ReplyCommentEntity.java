package com.lsl.demo.model.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.lsl.demo.common.base.entity.BaseEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author lsl_ja
 * @since 2020-03-09
 */
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("reply_comment")
public class ReplyCommentEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private String id;

    @Getter
    @Setter
    private String comment;

    @Getter
    @Setter
    private String userId;

    @Getter
    @Setter
    private String commentId;

}
