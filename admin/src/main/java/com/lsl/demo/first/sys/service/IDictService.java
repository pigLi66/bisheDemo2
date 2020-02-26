package com.lsl.demo.first.sys.service;

import com.lsl.demo.first.sys.dto.DictDto;
import com.lsl.demo.first.sys.entity.DictEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 字典表 服务类
 * </p>
 *
 * @author lsl_ja
 * @since 2020-02-13
 */
public interface IDictService extends IService<DictEntity> {

    /**
     * 存储新的字典
     * @param dictValue
     * @return dictValue 对应的dictKey
     */
    String saveDict(String dictValue);

    /**
     * 根据dictKey 更新dictValue
     * @param dictKey
     * @param dictValue
     */
    void upDictValue(String dictKey, String dictValue);

    /**
     * 根据dictKey 获取dictValue
     * @param dictKey
     * @return
     */
    String getDictValue(String dictKey);

    /**
     * 删除指定的dictKey
     * @param dictKey
     */
    void deleteDictByKey(String dictKey);

    /**
     *  删除指定的dictValue
     * @param dictValue
     */
    void deleteDictByValue(String dictValue);

    /**
     * 保存指定的字典值
     * @param dictKey
     * @param vValue
     * @return
     */
    String saveV(String dictKey, String vValue);

    /**
     * 更新指定的指定字典值
     * @param dictDto
     */
    void upVValue(DictDto dictDto);

    /**
     * 获取字典值
     * @param dictKey
     * @param vKey
     * @return
     */
    String getVValue(String dictKey, String vKey);

    /**
     * 删除指定字典值
     * @param dictDto
     */
    void deleteV(DictDto dictDto);

    /**
     * 获取所有的字典
     * @return
     */
    List<String> getDictList();

    /**
     * 获取dictKey下的所有字典值
     * @param dictKey
     * @return
     */
    List<DictDto> getVList(String dictKey);

}
