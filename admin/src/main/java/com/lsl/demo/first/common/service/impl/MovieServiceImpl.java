package com.lsl.demo.first.common.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lsl.demo.first.common.dto.MovieDto;
import com.lsl.demo.first.common.dto.MovieAbstractDto;
import com.lsl.demo.first.common.dto.MovieInfoDto;
import com.lsl.demo.first.common.entity.MovieEntity;
import com.lsl.demo.first.common.mapper.MovieMapper;
import com.lsl.demo.first.common.service.ILevelService;
import com.lsl.demo.first.common.service.IMovieService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lsl.demo.first.sys.service.IArtistService;
import com.lsl.demo.first.utils.ConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
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
    public IPage<MovieAbstractDto> getPage(int startPage, int pageLimit) {
        Page<MovieAbstractDto> rs = new Page<>(startPage, pageLimit);
        Page<MovieEntity> page = new Page<>(startPage, pageLimit);
        this.baseMapper.selectPage(page, new QueryWrapper<>());
        List<MovieAbstractDto> rsList = ConvertUtil.sourceToTarget(page.getRecords(), MovieAbstractDto.class);
        rsList.forEach(item-> item.setLevel(levelService.getMovieLevel(item.getId())));
        rs.setRecords(rsList);
        rs.setSize(page.getSize());
        rs.setCurrent(page.getCurrent());
        rs.setOrders(page.getOrders());
        rs.setTotal(page.getTotal());
        rs.setPages(page.getPages());
        return rs;
    }

    @Override
    public MovieInfoDto getMovieInfoById(String id) {
        MovieEntity movieEntity = this.baseMapper.selectById(id);
        MovieInfoDto rs = ConvertUtil.sourceToTarget(movieEntity, MovieInfoDto.class);
        rs.setDirectors(Arrays.stream(movieEntity.getDirectorIds().split(","))
                .map(item->artistService.getById(item)).collect(Collectors.toList()));
        rs.setPerformers(Arrays.stream(movieEntity.getPerformerIds().split(","))
                .map(item->artistService.getById(item)).collect(Collectors.toList()));
        rs.setLevel(levelService.getMovieLevel(movieEntity.getId()));
        return rs;
    }

    @Override
    public String save(MovieDto movieDto) {
        MovieEntity entity = ConvertUtil.sourceToTarget(movieDto, MovieEntity.class);
        this.baseMapper.insert(entity);
        return entity.getId();
    }
}
