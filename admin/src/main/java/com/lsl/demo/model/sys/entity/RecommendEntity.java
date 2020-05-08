package com.lsl.demo.model.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.lsl.demo.common.base.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author lisiliang
 * @since 2020/4/2
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_recommend")
public class RecommendEntity extends BaseEntity {

    private String userId;

    private String movieId;

    /**
     * 点击次数
     */
    private Integer tapCount;

    /**
     * 评论次数
     */
    private Integer commentCount;

}
