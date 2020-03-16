package com.lsl.demo.first.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.lsl.demo.first.utils.base.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 电影评论表
 * </p>
 *
 * @author lsl_ja
 * @since 2020-02-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("comment")
public class CommentEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 评论所属类别标识id
     */
    private String collectIds;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 用户评论
     */
    private String comment;


}
