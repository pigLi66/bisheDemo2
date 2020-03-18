package com.lsl.demo.model.common.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lsl.demo.common.base.controller.BaseController;
import com.lsl.demo.model.common.dto.CommentDto;
import com.lsl.demo.model.common.dto.CommentInfoRSDto;
import com.lsl.demo.model.common.entity.CommentEntity;
import com.lsl.demo.model.common.service.ICommentService;
import com.lsl.demo.common.annotation.interceptor.Auth;
import com.lsl.demo.common.enums.Operation;
import com.lsl.demo.utils.validate.AddGroup;
import com.lsl.demo.utils.validate.UpdateGroup;
import com.lsl.demo.utils.validate.ValidatorUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
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
public class CommentController extends BaseController<CommentEntity, ICommentService> {

    @Auth
    @ApiOperation("保存一个评论")
    @PostMapping
    public ResponseEntity<String> saveComment(@RequestBody CommentDto dto) {
        ValidatorUtil.validateEntity(dto, AddGroup.class);
        return ResponseEntity.ok(this.service.saveComment(dto));
    }

    @Auth
    @PutMapping("/{id}")
    public ResponseEntity<String> updateById(@PathVariable String id, @RequestBody CommentDto dto) {
        ValidatorUtil.validateEntity(dto, UpdateGroup.class);
        this.service.updateById(id, dto);
        return ResponseEntity.ok(Operation.SUCCESS.get());
    }

    @ApiOperation("根据评论标识获取评论")
    @GetMapping("/collects/{collectId}")
    public ResponseEntity<Page<CommentInfoRSDto>> getMovieComment(@PathVariable String collectId,
                                                                  @RequestParam(defaultValue = "1") int startPage,
                                                                  @RequestParam(defaultValue = "9") int pageSize) {
        return ResponseEntity.ok(this.service.getMovieCommentPage(startPage, pageSize, collectId));
    }

    @ApiOperation("根据评论标识删除评论")
    @DeleteMapping("/collects/{commentId}")
    public ResponseEntity deleteMovieComment(@PathVariable String commentId) {
        this.service.deleteMovieComment(commentId);
        return (ResponseEntity) ResponseEntity.ok();
    }

    @Profile("dev")
    @ApiOperation("根据用户id删除评论")
    @DeleteMapping("/user/{userId}")
    public ResponseEntity deleteUserComment(@PathVariable String userId) {
        this.service.deleteUserComment(userId);
        return (ResponseEntity) ResponseEntity.ok();
    }

    @Profile("dev")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable String id) {
        this.service.removeById(id);
        return ResponseEntity.ok(Operation.SUCCESS.get());
    }



}
