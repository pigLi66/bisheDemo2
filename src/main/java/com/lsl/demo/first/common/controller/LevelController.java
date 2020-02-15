package com.lsl.demo.first.common.controller;


import com.lsl.demo.first.common.dto.LevelDto;
import com.lsl.demo.first.common.service.ILevelService;
import com.lsl.demo.first.utils.enums.Operation;
import com.lsl.demo.first.utils.exceptions.BusinessException;
import com.lsl.demo.first.utils.validate.ValidatorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
@RestController
@RequestMapping("/common/level")
public class LevelController {

    @Autowired
    private ILevelService levelService;

    @GetMapping("{movieId}")
    public ResponseEntity<String> getMovieLevel(@PathVariable String movieId) {
        return new ResponseEntity<>(this.levelService.getMovieLevel(movieId), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<String> getMovieLevel(LevelDto dto) {
        ValidatorUtil.validateEntity(dto);
        return new ResponseEntity<>(this.levelService.saveOrUpLevel(dto), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Integer> getLevel(String movieId, String userId) {
        if (Objects.isNull(movieId) || Objects.isNull(userId)) {
            throw new BusinessException(Operation.FAIL.get());
        }
        return new ResponseEntity<>(this.levelService.getLevel(movieId, userId), HttpStatus.OK);
    }

}
