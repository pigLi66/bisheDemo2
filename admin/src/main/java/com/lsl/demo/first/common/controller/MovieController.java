package com.lsl.demo.first.common.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lsl.demo.first.common.dto.MovieDto;
import com.lsl.demo.first.common.dto.MovieAbstractDto;
import com.lsl.demo.first.common.dto.MovieInfoDto;
import com.lsl.demo.first.common.entity.MovieEntity;
import com.lsl.demo.first.common.service.IMovieService;
import com.lsl.demo.first.utils.ConvertUtil;
import com.lsl.demo.first.utils.validate.AddGroup;
import com.lsl.demo.first.utils.validate.UpdateGroup;
import com.lsl.demo.first.utils.validate.ValidatorUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 电影表 前端控制器
 * </p>
 *
 * @author lsl_ja
 * @since 2020-02-13
 */
@Api("电影相关接口")
@RestController
@RequestMapping("/common/movie")
public class MovieController {

    @Autowired
    private IMovieService movieService;

    @ApiOperation("保存新增一个电影")
    @PostMapping
    public ResponseEntity<String> save(@RequestBody MovieDto dto) {
        ValidatorUtil.validateEntity(dto, AddGroup.class);
        return ResponseEntity.ok(this.movieService.save(dto));
    }

    @ApiOperation("根据id获取电影的信息")
    @GetMapping("/{id}")
    public ResponseEntity<MovieInfoDto> getById(@PathVariable String id) {
        return ResponseEntity.ok(movieService.getMovieInfoById(id));
    }

    @ApiOperation("根据电影id更新电影数据")
    @PutMapping("/{id}")
    public ResponseEntity upMovie(@RequestBody MovieDto dto, @PathVariable String id) {
        ValidatorUtil.validateEntity(dto, UpdateGroup.class);
        MovieEntity entity = ConvertUtil.sourceToTarget(dto, MovieEntity.class);
        entity.setId(id);
        this.movieService.updateById(entity);
        return (ResponseEntity) ResponseEntity.ok();
    }

    @ApiOperation("根据电影名字搜索相关电影")
    @GetMapping("/include/{movieName}")
    public ResponseEntity<List<MovieEntity>> serachMovieByname(@PathVariable String movieName) {
        return ResponseEntity.ok(this.movieService.searchByName(movieName));
    }

    @ApiOperation("根据电影类别获取电影列表")
    @GetMapping("/class/{classId}")
    public ResponseEntity<List<MovieEntity>> getByClass(@PathVariable String classId) {
        return ResponseEntity.ok(this.movieService.getByClassList(classId));
    }

    @ApiOperation("获取指定页数的电影列表")
    @GetMapping("/page")
    public ResponseEntity<IPage<MovieAbstractDto>> getpage(Integer startPage, Integer pageLimit) {
        return ResponseEntity.ok(movieService.getPage(startPage, pageLimit));
    }

    @ApiOperation("获取所有的电影")
    @GetMapping
    public ResponseEntity<List<MovieEntity>> getAll() {
        return ResponseEntity.ok(this.movieService.getAllMovieList());
    }

}
