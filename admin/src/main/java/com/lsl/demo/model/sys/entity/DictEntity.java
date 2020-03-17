package com.lsl.demo.model.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import com.lsl.demo.common.base.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 字典表
 * </p>
 *
 * @author lsl_ja
 * @since 2020-02-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_dict")
public class DictEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 字典编码
     */
    private String dictKey;

    /**
     * 字典名称
     */
    private String dictValue;

    /**
     * 字典值编码
     */
    private String vKey;

    /**
     * 字典值
     */
    private String vValue;


}
