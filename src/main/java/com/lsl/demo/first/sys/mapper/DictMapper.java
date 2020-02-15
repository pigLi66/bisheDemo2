package com.lsl.demo.first.sys.mapper;

import com.lsl.demo.first.sys.dto.DictDto;
import com.lsl.demo.first.sys.entity.DictEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 字典表 Mapper 接口
 * </p>
 *
 * @author lsl_ja
 * @since 2020-02-13
 */
public interface DictMapper extends BaseMapper<DictEntity> {

    List<String> getDictList();

    List<DictDto> getVList(String dictKey);

}
