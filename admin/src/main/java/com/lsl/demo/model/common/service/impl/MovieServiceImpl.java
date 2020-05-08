package com.lsl.demo.model.common.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lsl.demo.common.base.entity.BaseEntity;
import com.lsl.demo.model.common.dto.MovieAbstractDto;
import com.lsl.demo.model.common.dto.MovieDto;
import com.lsl.demo.model.common.dto.MovieInfoDto;
import com.lsl.demo.model.common.entity.MovieEntity;
import com.lsl.demo.model.common.mapper.MovieMapper;
import com.lsl.demo.model.common.service.ILevelService;
import com.lsl.demo.model.common.service.IMovieService;
import com.lsl.demo.model.sys.service.IArtistService;
import com.lsl.demo.model.sys.service.IRecommendService;
import com.lsl.demo.utils.ConvertUtil;
import com.lsl.demo.utils.global.BaseContextHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

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

    @Autowired
    private ILevelService levelService;

    @Autowired
    private IArtistService artistService;

    @Autowired
    private IRecommendService recommendService;

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
        return this.listRecommend();
    }

    @Override
    public IPage<MovieAbstractDto> getPage(int startPage, int pageLimit) {
        Page<MovieAbstractDto> result = new Page<>(startPage, pageLimit);
        List<MovieEntity> allMovie = this.listRecommend();
        result.setSize(pageLimit);
        result.setCurrent(startPage);
        result.setTotal(allMovie.size());
        result.setRecords(ConvertUtil.sourceToTarget(allMovie.subList(startPage*pageLimit, (startPage+1) * pageLimit), MovieAbstractDto.class));
        result.getRecords().forEach(item -> item.setLevel(levelService.getMovieLevel(item.getId())));
        return result;
    }

    @Override
    public MovieInfoDto getMovieInfoById(String id) {
        recommendService.tap(id);
        MovieEntity movieEntity = this.baseMapper.selectById(id);
        MovieInfoDto rs = ConvertUtil.sourceToTarget(movieEntity, MovieInfoDto.class);
        rs.setDirectors(Arrays.stream(movieEntity.getDirectorIds().split(","))
                .map(item->artistService.getById(item)).collect(Collectors.toList()));
        rs.setPerformers(Arrays.stream(movieEntity.getPerformerIds().split(","))
                .map(item->artistService.getById(item)).collect(Collectors.toList()));
        rs.setLevel(levelService.getMovieLevel(movieEntity.getId()));
        return rs;
    }

    public List<MovieEntity> listRecommend() {
        List<String> recommendMovie = recommendService.listSortedMovieId(BaseContextHandler.getUserId());
        Map<String, MovieEntity> allMovie = super.list().stream()
                .collect(Collectors.toMap(BaseEntity::getId, Function.identity()));
        List<MovieEntity> rs = recommendMovie.stream().map(allMovie::get).collect(Collectors.toList());
        rs.addAll(allMovie.values().stream().filter(item -> !rs.contains(item)).collect(Collectors.toList()));
        return rs;
    }

    @Override
    public String save(MovieDto movieDto) {
        MovieEntity entity = ConvertUtil.sourceToTarget(movieDto, MovieEntity.class);
        this.baseMapper.insert(entity);
        return entity.getId();
    }
}
