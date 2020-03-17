package com.lsl.demo.model.sys.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.lsl.demo.utils.ConvertUtil;
import com.lsl.demo.common.exceptions.BusinessException;
import com.lsl.demo.model.sys.dto.DictDto;
import com.lsl.demo.model.sys.entity.DictEntity;
import com.lsl.demo.model.sys.mapper.DictMapper;
import com.lsl.demo.model.sys.service.IDictService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 字典表 服务实现类
 * </p>
 *
 * @author lsl_ja
 * @since 2020-02-13
 */
@Service
public class DictServiceImpl extends ServiceImpl<DictMapper, DictEntity> implements IDictService {

    @Override
    public String saveDict(String dictValue) {
        DictEntity entity = new DictEntity();
        entity.setDictKey(nextDictKey());
        entity.setDictValue(dictValue);
        entity.setVKey("0");
        entity.setVValue("1");
        this.baseMapper.insert(entity);
        return entity.getDictKey();
    }

    @Override
    public void upDictValue(String dictKey, String dictValue) {
        UpdateWrapper<DictEntity> wrapper = new UpdateWrapper<>();
        wrapper.eq("dict_key", dictKey);
        wrapper.eq("valid", 0);

        DictEntity entity = new DictEntity();
        entity.setDictValue(dictValue);
        this.baseMapper.update(entity, wrapper);
    }

    @Override
    public String getDictValue(String dictKey) {
        QueryWrapper<DictEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("dict_key", dictKey);
        wrapper.eq("v_key", 0);
        wrapper.eq("valid", 0);
        DictEntity entity = this.baseMapper.selectOne(wrapper);

        if (Objects.isNull(entity)) {
            return null;
        }
        return entity.getDictValue();
    }

    @Override
    public void deleteDictByKey(String dictKey) {
        QueryWrapper<DictEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("dict_key", dictKey);
        this.baseMapper.delete(wrapper);
    }

    @Override
    public void deleteDictByValue(String dictValue) {
        QueryWrapper<DictEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("dict_value", dictValue);
        this.baseMapper.delete(wrapper);
    }

    @Override
    public String saveV(String dictKey, String vValue) {
        String dictValue = this.getDictValue(dictKey);
        if (Objects.isNull(dictValue)) {
            throw new BusinessException("字典不存在");
        }
        if (!judgeVUnique(dictKey, vValue)) {
            throw  new BusinessException("字典值已存在");
        }
        String next = nextVKey(dictKey);
        DictEntity entity = new DictEntity();
        entity.setDictKey(dictKey);
        entity.setDictValue(dictValue);
        entity.setVKey(next);
        entity.setVValue(vValue);
        this.baseMapper.insert(entity);
        return next;
    }

    @Override
    public void upVValue(DictDto dictDto) {
        QueryWrapper<DictEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("dict_key", dictDto.getDictKey());
        queryWrapper.ne("v_key", dictDto.getDictKey());
        queryWrapper.eq("v_value", dictDto.getDictValue());
        queryWrapper.eq("valid", 0);

        DictEntity dictEntity = this.baseMapper.selectOne(queryWrapper);
        if (Objects.nonNull(dictEntity)) {
            throw new BusinessException("字典值已存在");
        }

        UpdateWrapper<DictEntity> wrapper = new UpdateWrapper<>();
        wrapper.eq("dict_key", dictDto.getDictKey());
        wrapper.eq("v_key", dictDto.getDictKey());
        DictEntity entity = ConvertUtil.sourceToTarget(dictDto, DictEntity.class);
        entity.setDictValue(null);
        this.baseMapper.update(entity, wrapper);
    }

    @Override
    public String getVValue(String dictKey, String vKey) {
        QueryWrapper<DictEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("dict_key", dictKey);
        wrapper.eq("v_key", vKey);
        wrapper.eq("valid", 0);
        DictEntity entity = this.baseMapper.selectOne(wrapper);
        if (Objects.nonNull(entity)) {
            return entity.getVValue();
        }
        return null;
    }

    @Override
    public void deleteV(DictDto dictDto) {
        QueryWrapper<DictEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("dict_key", dictDto.getDictKey());
        if (Objects.nonNull(dictDto.getVKey())) {
            wrapper.eq("v_key", dictDto.getVKey());
        } else {
            wrapper.eq("v_value", dictDto.getVValue());
        }
        this.baseMapper.delete(wrapper);
    }

    @Override
    public List<String> getDictList() {
        return this.baseMapper.getDictList();
    }

    @Override
    public List<DictDto> getVList(String dictKey) {
        return this.baseMapper.getVList(dictKey);
    }

    private boolean judgeVUnique(String dictKey, String vValue) {
        QueryWrapper<DictEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("dict_key", dictKey);
        wrapper.eq("v_value", vValue);
        wrapper.eq("valid", "0");
        DictEntity entity = this.baseMapper.selectOne(wrapper);
        return entity == null;
    }

    private String nextDictKey() {
        QueryWrapper<DictEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("dict_key", "0");
        DictEntity entity = this.baseMapper.selectOne(wrapper);
        int next = Integer.parseInt(entity.getDictValue());
        entity.setDictValue(String.valueOf(next + 1));
        this.baseMapper.updateById(entity);
        return String.valueOf(next);
    }

    private String nextVKey(String dictKey) {
        QueryWrapper<DictEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("dict_key", dictKey);
        wrapper.eq("v_key", "0");
        DictEntity entity = this.baseMapper.selectOne(wrapper);
        if (Objects.isNull(entity)) {
            return null;
        } else {
            int next = Integer.parseInt(entity.getVValue());
            entity.setVValue(String.valueOf(next + 1));
            this.baseMapper.updateById(entity);
            return String.valueOf(next);
        }
    }

}
