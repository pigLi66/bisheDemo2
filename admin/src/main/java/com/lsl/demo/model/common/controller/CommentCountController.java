package com.lsl.demo.model.common.controller;


import com.lsl.demo.common.base.controller.BaseController;
import com.lsl.demo.model.common.dto.CommentCountDto;
import com.lsl.demo.model.common.entity.CommentCountEntity;
import com.lsl.demo.model.common.service.ICommentCountService;
import com.lsl.demo.utils.global.BaseContextHandler;
import com.lsl.demo.common.annotation.interceptor.Auth;
import com.lsl.demo.common.enums.Operation;
import com.lsl.demo.common.exceptions.BusinessException;
import com.lsl.demo.utils.validate.ValidatorUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
public class CommentCountController
        extends BaseController<CommentCountEntity, ICommentCountService> {

    @ApiOperation("用户点赞的计数接口")
    @Auth
    @PutMapping
    public ResponseEntity<Integer> saveOrDelete(@RequestBody CommentCountDto dto) {
        ValidatorUtil.validateEntity(dto);
        dto.setUserId(BaseContextHandler.getUserId());
        return ResponseEntity.ok(this.service.saveOrDelete(dto));
    }

    @Auth
    @ApiOperation("电影评论计数，点赞传入type=0")
    @PostMapping
    public ResponseEntity<String> save(@RequestBody CommentCountDto dto) {
        ValidatorUtil.validateEntity(dto);
        return new ResponseEntity<>(this.service.save(dto), HttpStatus.OK);
    }

    @ApiOperation("评论计数，例如，获取点赞数，tpye=0")
    @GetMapping()
    public ResponseEntity<String> count(String commentId, String type) {
        if (Objects.isNull(commentId) || Objects.isNull(type)) {
            throw new BusinessException("commentId和type不能为空");
        }
        return new ResponseEntity<>(String.valueOf(this.service.count(commentId, type)), HttpStatus.OK);
    }

    @Auth
    @ApiOperation("根据id 删除一个评论操作，例如取消一个点赞")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable String id) {
        this.service.removeById(id);
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
        this.service.delete(dto);
        return (ResponseEntity) ResponseEntity.ok();
    }

}
