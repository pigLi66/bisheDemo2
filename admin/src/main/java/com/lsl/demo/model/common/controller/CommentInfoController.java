package com.lsl.demo.model.common.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lsl.demo.common.base.controller.BaseController;
import com.lsl.demo.model.common.dto.CommentInfoRSDto;
import com.lsl.demo.model.common.entity.CommentEntity;
import com.lsl.demo.model.common.service.ICommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author lisiliang
 * @since 2020/3/16
 */
@Api("获取评论信息的接口")
@RestController
@RequestMapping("/common/comment/info")
public class CommentInfoController extends BaseController<CommentEntity, ICommentService> {

    @ApiOperation("根据用户id获取评论")
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<CommentEntity>> getUserComment(@PathVariable String userId) {
        return ResponseEntity.ok(this.service.getUserCommentList(userId));
    }

    @ApiOperation("根据评论标识获取评论")
    @GetMapping("/collects/{collectId}")
    public ResponseEntity<Page<CommentInfoRSDto>> getMovieComment(@PathVariable String collectId,
                                                                  @RequestParam(defaultValue = "1") int startPage,
                                                                  @RequestParam(defaultValue = "9") int pageSize) {
        return ResponseEntity.ok(this.service.getMovieCommentPage(startPage, pageSize, collectId));
    }

    @ApiOperation("获取一个页面的评论信息")
    @GetMapping
    public ResponseEntity<IPage<CommentInfoRSDto>> getPage(@RequestParam(defaultValue = "1") int startPage,
                                                           @RequestParam(defaultValue = "4") int pageSize,
                                                           @RequestParam(defaultValue = "") String comment) {
        return ResponseEntity.ok(this.service.getPage(startPage, pageSize, comment));
    }

    @ApiOperation("根据评论id获取评论的信息")
    @GetMapping("/{id}")
    public ResponseEntity<CommentInfoRSDto> getCommentInfoById(@PathVariable String id) {
        return ResponseEntity.ok(this.service.getCommentInfoById(id));
    }

}
