package com.lsl.demo.first.common.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.Query;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lsl.demo.first.common.dto.MovieDto;
import com.lsl.demo.first.common.entity.MovieEntity;
import com.lsl.demo.first.common.mapper.MovieMapper;
import com.lsl.demo.first.common.service.IMovieService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lsl.demo.first.utils.ConvertUtil;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 电影表 服务实现类
 * </p>
 *
 * @author lsl_ja
 * @since 2020-02-13
 */
@Service
public class MovieServiceImpl extends ServiceImpl<MovieMapper, MovieEntity> implements IMovieService {

    @Override
    public List<MovieEntity> searchByName(String movieName) {
        QueryWrapper<MovieEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("movie_name", movieName);
        return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    public List<MovieEntity> getByClassList(String classId) {
        QueryWrapper<MovieEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("class_id", classId);
        return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    public List<MovieEntity> getAllMovieList() {
        return this.baseMapper.selectList(new QueryWrapper<>());
    }

    @Override
    public IPage<MovieEntity> getPage(int startPage, int pageLimit) {
        Page<MovieEntity> rs = new Page<>(startPage, pageLimit);
        this.baseMapper.selectPage(rs, new QueryWrapper<>());
        return rs;
    }

    @Override
    public String save(MovieDto movieDto) {
        MovieEntity entity = ConvertUtil.sourceToTarget(movieDto, MovieEntity.class);
        this.baseMapper.insert(entity);
        return entity.getId();
    }
}
