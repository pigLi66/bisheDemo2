package com.lsl.demo.model.common.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lsl.demo.common.CommonConstants;
import com.lsl.demo.common.base.controller.BaseController;
import com.lsl.demo.model.common.dto.MovieAbstractDto;
import com.lsl.demo.model.common.dto.MovieInfoDto;
import com.lsl.demo.model.common.entity.MovieEntity;
import com.lsl.demo.model.common.service.IMovieService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
public class MovieController
        extends BaseController<MovieEntity, IMovieService> {

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable String id) {
        return ResponseEntity.ok(CommonConstants.MSG_NON_API);
    }

    @ApiOperation("根据id获取电影的信息")
    @GetMapping("/{id}")
    public ResponseEntity<MovieInfoDto> getById(@PathVariable String id) {
        return ResponseEntity.ok(this.service.getMovieInfoById(id));
    }

    @ApiOperation("根据电影名字搜索相关电影")
    @GetMapping("/include/{movieName}")
    public ResponseEntity<List<MovieEntity>> searchMovieByName(@PathVariable String movieName) {
        return ResponseEntity.ok(this.service.searchByName(movieName));
    }

    @ApiOperation("根据电影类别获取电影列表")
    @GetMapping("/class/{classId}")
    public ResponseEntity<List<MovieEntity>> getByClass(@PathVariable String classId) {
        return ResponseEntity.ok(this.service.getByClassList(classId));
    }

    @ApiOperation("获取指定页数的电影列表")
    @GetMapping("/page")
    public ResponseEntity<IPage<MovieAbstractDto>> getPage(Integer startPage, Integer pageLimit) {
        return ResponseEntity.ok(this.service.getPage(startPage, pageLimit));
    }

    @ApiOperation("获取所有的电影")
    @GetMapping
    public ResponseEntity<List<MovieEntity>> getAll() {
        return ResponseEntity.ok(this.service.getAllMovieList());
    }

}
