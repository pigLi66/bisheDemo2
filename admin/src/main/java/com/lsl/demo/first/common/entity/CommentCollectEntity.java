package com.lsl.demo.first.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.lsl.demo.first.utils.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author lsl_ja
 * @since 2020-03-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("comment_collect")
public class CommentCollectEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 评论类别名
     */
    private String name;

    /**
     * 创建者
     */
    private String createBy;

    /**
     * 简介
     */
    private String profile;

    /**
     * 图片地址
     */
    private String pictureUrl;


}
