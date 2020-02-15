package com.lsl.demo.first.common.entity;

import com.lsl.demo.first.utils.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 电影表
 * </p>
 *
 * @author lsl_ja
 * @since 2020-02-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class MovieEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 电影名
     */
    private String mName;

    /**
     * 导演id
     */
    private String directorIds;

    /**
     * 演员id
     */
    private String performerIds;

    /**
     * 电影类别id
     */
    private String classIds;

    /**
     * 电影时长
     */
    private Integer span;

    /**
     * 电影简介
     */
    private String profile;


}
