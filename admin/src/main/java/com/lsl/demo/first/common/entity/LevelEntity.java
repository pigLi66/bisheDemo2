package com.lsl.demo.first.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.lsl.demo.first.utils.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 电影评级表
 * </p>
 *
 * @author lsl_ja
 * @since 2020-02-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("level")
public class LevelEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 电影id
     */
    private String movieId;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 评级（1--10）
     */
    private Double level;


}
