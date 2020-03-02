package com.lsl.demo.first.common.controller;


import com.lsl.demo.first.common.dto.LevelDto;
import com.lsl.demo.first.common.service.ILevelService;
import com.lsl.demo.first.utils.BaseContextHandler;
import com.lsl.demo.first.utils.enums.Operation;
import com.lsl.demo.first.utils.exceptions.BusinessException;
import com.lsl.demo.first.utils.validate.ValidatorUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

/**
 * <p>
 * 电影评级表 前端控制器
 * </p>
 *
 * @author lsl_ja
 * @since 2020-02-13
 */

@Api("电影评级接口")
@RestController
@RequestMapping("/common/level")
public class LevelController {

    @Autowired
    private ILevelService levelService;

    @ApiOperation("保存或者更新用户评级")
    @PutMapping
    public ResponseEntity<String> saveOrUpLevel(@RequestBody LevelDto dto) {
        ValidatorUtil.validateEntity(dto);
        return ResponseEntity.ok(this.levelService.saveOrUpLevel(dto));
    }

    @ApiOperation("根据电影id 获取电影评级")
    @GetMapping("{movieId}")
    public ResponseEntity<Double> getMovieLevel(@PathVariable String movieId) {
        return ResponseEntity.ok(this.levelService.getMovieLevel(movieId));
    }

    @ApiOperation("获取单个用户对电影评级")
    @GetMapping
    public ResponseEntity<Double> getLevel(String movieId, String userId) {
        if (Objects.isNull(movieId) || Objects.isNull(userId)) {
            throw new BusinessException(Operation.FAIL.get());
        }
        return ResponseEntity.ok(this.levelService.getLevel(movieId, userId));
    }

    @ApiOperation("获取当前用户对电影的评级")
    @GetMapping("/movieId")
    public ResponseEntity<Double> getLevel(@PathVariable String movieId) {
        return ResponseEntity.ok(this.levelService.getLevel(movieId, BaseContextHandler.getUserId()));
    }

}
