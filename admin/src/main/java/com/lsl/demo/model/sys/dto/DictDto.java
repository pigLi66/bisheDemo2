package com.lsl.demo.model.sys.dto;

import com.lsl.demo.utils.validate.UpdateGroup;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author lisiliang
 * @since 2020/2/13
 */
@Data
public class DictDto {

    /**
     * 字典编码
     */
    @NotBlank(message = "dictKey不能为空", groups = {UpdateGroup.class})
    private String dictKey;

    /**
     * 字典名称
     */
    private String dictValue;

    /**
     * 字典值编码
     */
    @NotBlank(message = "vKey不能为空", groups = {UpdateGroup.class})
    private String vKey;

    /**
     * 字典值
     */
    @NotBlank(message = "vValue不能为空", groups = {UpdateGroup.class})
    private String vValue;

}
