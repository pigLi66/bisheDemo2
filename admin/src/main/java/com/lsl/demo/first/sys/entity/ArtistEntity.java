package com.lsl.demo.first.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.lsl.demo.first.utils.base.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author lsl_ja
 * @since 2020-02-26
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_artist")
public class ArtistEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 性别
     */
    private String sex;

    /**
     * 出生日期
     */
    private String birthday;

    /**
     * 出生地
     */
    private String birthPlace;

    /**
     * 职业
     */
    private String position;

    /**
     * 简介
     */
    private String profile;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 豆瓣上面的id
     */
    private String doubanId;

    /**
     * 图片url
     */
    private String pictureUrl;

}
