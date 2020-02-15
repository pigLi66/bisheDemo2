package com.lsl.demo.first.common.controller;


import com.lsl.demo.first.common.dto.CommentCountDto;
import com.lsl.demo.first.common.service.ICommentCountService;
import com.lsl.demo.first.utils.exceptions.BusinessException;
import com.lsl.demo.first.utils.validate.ValidatorUtil;
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
@RestController
@RequestMapping("/common/comment/count")
public class CommentCountController {

    @Autowired
    private ICommentCountService commentCountService;

    @PostMapping
    public ResponseEntity<String> save(@RequestBody CommentCountDto dto) {
        ValidatorUtil.validateEntity(dto);
        return new ResponseEntity<>(this.commentCountService.save(dto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable String id) {
        this.commentCountService.removeById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<String> count(String commentId, String type) {
        if (Objects.isNull(commentId) || Objects.isNull(type)) {
            throw new BusinessException("commentId和type不能为空");
        }
        return new ResponseEntity<>(String.valueOf(this.commentCountService.count(commentId, type)), HttpStatus.OK);
    }

}
