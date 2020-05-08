package com.lsl.demo.model.common.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lsl.demo.model.common.dto.LevelDto;
import com.lsl.demo.model.common.entity.LevelEntity;
import com.lsl.demo.model.common.mapper.LevelMapper;
import com.lsl.demo.model.common.service.ILevelService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lsl.demo.utils.ConvertUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * <p>
 * 电影评级表 服务实现类
 * </p>
 *
 * @author lsl_ja
 * @since 2020-02-13
 */
@Service
public class LevelServiceImpl extends ServiceImpl<LevelMapper, LevelEntity> implements ILevelService {

    @Override
    public String saveOrUpLevel(LevelDto dto) {
        QueryWrapper<LevelEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("movie_id", dto.getMovieId());
        queryWrapper.eq("user_id", dto.getUserId());
        LevelEntity entity = this.baseMapper.selectOne(queryWrapper);

        // 更新或者插入
        if (Objects.nonNull(entity)) {
            entity.setLevel(dto.getLevel());
            this.baseMapper.updateById(entity);
        } else {
            entity = ConvertUtil.sourceToTarget(dto, LevelEntity.class);
            this.baseMapper.insert(entity);
        }

        return entity.getId();
    }

    @Override
    public Double getMovieLevel(String movieId) {
        List<Double> levels = this.getLevelList(movieId);
        double rs = levels.stream().mapToDouble(a->a).summaryStatistics().getAverage();
        return Double.parseDouble(String.format("%.2f", rs));
    }

    @Override
    public List<Double> getLevelList(String movieId) {
        QueryWrapper<LevelEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("movie_id", movieId);
        return this.baseMapper.selectList(wrapper).stream().map(LevelEntity::getLevel).collect(Collectors.toList());
    }

    @Override
    public Double getLevel(String movieId, String userId) {
        QueryWrapper<LevelEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("movie_id", movieId);
        wrapper.eq("user_id", userId);
        return Optional.ofNullable(this.baseMapper.selectOne(wrapper))
                .map(LevelEntity::getLevel)
                .orElse(5.0);
    }

}
