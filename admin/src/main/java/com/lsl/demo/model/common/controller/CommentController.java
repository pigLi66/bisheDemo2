package com.lsl.demo.model.common.controller;


import com.lsl.demo.common.base.controller.BaseCRUDController;
import com.lsl.demo.model.common.dto.CommentDto;
import com.lsl.demo.model.common.entity.CommentEntity;
import com.lsl.demo.model.common.service.ICommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
public class CommentController
        extends BaseCRUDController<CommentDto, CommentEntity, ICommentService> {

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

    @Override
    @Profile("dev")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable String id) {
        return super.deleteById(id);
    }

    @Override
    protected Class<CommentEntity> getEntityClass() {
        return CommentEntity.class;
    }

    @Override
    protected CommentEntity getEntity() {
        return new CommentEntity();
    }

}
