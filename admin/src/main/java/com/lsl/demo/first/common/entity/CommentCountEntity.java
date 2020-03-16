package com.lsl.demo.first.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.lsl.demo.first.utils.base.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 评论点赞计数
 * </p>
 *
 * @author lsl_ja
 * @since 2020-02-15
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("comment_count")
public class CommentCountEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 评论id
     */
    private String commentId;

    /**
     * 用户id
     */
    private String userId;

    private String type;

}
