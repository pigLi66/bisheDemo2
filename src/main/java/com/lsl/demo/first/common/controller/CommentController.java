package com.lsl.demo.first.common.controller;


import com.lsl.demo.first.common.dto.CommentDto;
import com.lsl.demo.first.common.entity.CommentEntity;
import com.lsl.demo.first.common.service.ICommentService;
import com.lsl.demo.first.utils.validate.ValidatorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
public class CommentController {

    @Autowired
    private ICommentService commentService;

    @PostMapping
    public ResponseEntity<String> saveComment(@RequestBody CommentDto dto) {
        ValidatorUtil.validateEntity(dto);
        return  new ResponseEntity<>(this.commentService.saveComment(dto), HttpStatus.OK);
    }

    @DeleteMapping("/movie/{movieId}")
    public ResponseEntity<String> deleteMovieComment(@PathVariable String movieId) {
        this.commentService.deleteMovieComment(movieId);
        return  new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/user/{userId}")
    public ResponseEntity<String> deleteUserComment(@PathVariable String userId) {
        this.commentService.deleteUserComment(userId);
        return  new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<CommentEntity>> getUserComment(@PathVariable String userId) {
        return  new ResponseEntity<>(this.commentService.getUserCommentList(userId), HttpStatus.OK);
    }

    @GetMapping("/movie/{movieId}")
    public ResponseEntity<List<CommentEntity>> getMoiveComment(@PathVariable String movieId) {
        return  new ResponseEntity<>(this.commentService.getMovieCommentList(movieId), HttpStatus.OK);
    }

}
