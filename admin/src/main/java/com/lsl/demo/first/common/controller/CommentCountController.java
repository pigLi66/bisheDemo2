package com.lsl.demo.first.common.controller;


import com.lsl.demo.first.common.dto.CommentCountDto;
import com.lsl.demo.first.common.service.ICommentCountService;
import com.lsl.demo.first.utils.BaseContextHandler;
import com.lsl.demo.first.utils.annotation.interceptor.Auth;
import com.lsl.demo.first.utils.enums.Operation;
import com.lsl.demo.first.utils.exceptions.BusinessException;
import com.lsl.demo.first.utils.validate.ValidatorUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

/**
 * <p>
 * 评论点赞计数 前端控制器
 * </p>
 *
 * @author lsl_ja
 * @since 2020-02-15
 */

@Api("电影评论点赞计数")
@RestController
@RequestMapping("/common/comment/count")
public class CommentCountController {

    @Autowired
    private ICommentCountService commentCountService;

    @Auth
    @PutMapping
    public ResponseEntity<Integer> saveOrDelete(@RequestBody CommentCountDto dto) {
        ValidatorUtil.validateEntity(dto);
        dto.setUserId(BaseContextHandler.getUserId());
        return ResponseEntity.ok(this.commentCountService.saveOrDelete(dto));
    }

    @Auth
    @ApiOperation("电影评论计数，点赞传入type=0")
    @PostMapping
    public ResponseEntity<String> save(@RequestBody CommentCountDto dto) {
        ValidatorUtil.validateEntity(dto);
        return new ResponseEntity<>(this.commentCountService.save(dto), HttpStatus.OK);
    }

    @ApiOperation("评论计数，例如，获取点赞数，tpye=0")
    @GetMapping()
    public ResponseEntity<String> count(String commentId, String type) {
        if (Objects.isNull(commentId) || Objects.isNull(type)) {
            throw new BusinessException("commentId和type不能为空");
        }
        return new ResponseEntity<>(String.valueOf(this.commentCountService.count(commentId, type)), HttpStatus.OK);
    }

    @Auth
    @ApiOperation("根据id 删除一个评论操作，例如取消一个点赞")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable String id) {
        this.commentCountService.removeById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Auth
    @ApiOperation("根据评论id 和type 删除记录, 例子：取消点赞type = 0")
    @DeleteMapping
    public ResponseEntity delete(String commentId, String type) {
        if (Objects.isNull(commentId) || Objects.isNull(type)) {
            throw new BusinessException(Operation.FAIL.get());
        }
        CommentCountDto dto = new CommentCountDto();
        dto.setCommentId(commentId);
        dto.setType(type);
        this.commentCountService.delete(dto);
        return (ResponseEntity) ResponseEntity.ok();
    }

}
