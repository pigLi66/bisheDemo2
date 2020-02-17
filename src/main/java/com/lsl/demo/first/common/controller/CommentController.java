package com.lsl.demo.first.common.controller;


import com.lsl.demo.first.common.dto.CommentDto;
import com.lsl.demo.first.common.entity.CommentEntity;
import com.lsl.demo.first.common.service.ICommentService;
import com.lsl.demo.first.utils.validate.ValidatorUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 电影评论表 前端控制器
 * </p>
 *
 * @author lsl_ja
 * @since 2020-02-13
 */
@RestController
@RequestMapping("/common/comment")
@Api("用户评论相关")
public class CommentController {

    @Autowired
    private ICommentService commentService;

    @ApiOperation("保存一个评论")
    @PostMapping
    public ResponseEntity<String> saveComment(@RequestBody CommentDto dto) {
        ValidatorUtil.validateEntity(dto);
        return ResponseEntity.ok(this.commentService.saveComment(dto));
    }

    @ApiOperation("根据用户id获取评论")
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<CommentEntity>> getUserComment(@PathVariable String userId) {
        return ResponseEntity.ok(this.commentService.getUserCommentList(userId));
    }

    @ApiOperation("根据电影id获取评论")
    @GetMapping("/movie/{movieId}")
    public ResponseEntity<List<CommentEntity>> getMoiveComment(@PathVariable String movieId) {
        return ResponseEntity.ok(this.commentService.getMovieCommentList(movieId));
    }

    @ApiOperation("根据电影id删除评论")
    @DeleteMapping("/movie/{movieId}")
    public ResponseEntity deleteMovieComment(@PathVariable String movieId) {
        this.commentService.deleteMovieComment(movieId);
        return (ResponseEntity) ResponseEntity.ok();
    }

    @ApiOperation("根据用户id删除评论")
    @DeleteMapping("/user/{userId}")
    public ResponseEntity deleteUserComment(@PathVariable String userId) {
        this.commentService.deleteUserComment(userId);
        return (ResponseEntity) ResponseEntity.ok();
    }

}
