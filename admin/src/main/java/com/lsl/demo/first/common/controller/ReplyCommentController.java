package com.lsl.demo.first.common.controller;


import com.lsl.demo.first.common.dto.ReplyCommentDto;
import com.lsl.demo.first.common.dto.ReplyCommentInfoDto;
import com.lsl.demo.first.common.entity.ReplyCommentEntity;
import com.lsl.demo.first.common.service.IReplyCommentService;
import com.lsl.demo.first.utils.BaseContextHandler;
import com.lsl.demo.first.utils.ConvertUtil;
import com.lsl.demo.first.utils.annotation.interceptor.Auth;
import com.lsl.demo.first.utils.validate.AddGroup;
import com.lsl.demo.first.utils.validate.ValidatorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lsl_ja
 * @since 2020-03-09
 */
@RestController
@RequestMapping("/common/reply/comment")
public class ReplyCommentController {

    @Autowired
    private IReplyCommentService replyCommentService;

    @Auth
    @PostMapping
    public ResponseEntity<String> save(@RequestBody ReplyCommentDto dto) {
        ValidatorUtil.validateEntity(dto, AddGroup.class);
        dto.setUserId(BaseContextHandler.getUserId());
        ReplyCommentEntity entity = ConvertUtil.sourceToTarget(dto, ReplyCommentEntity.class);
        this.replyCommentService.save(entity);
        return ResponseEntity.ok(entity.getId());
    }

    @GetMapping("/list/{commentId}")
    public ResponseEntity<List<ReplyCommentInfoDto>> listByCommentId(@PathVariable String commentId) {
        return ResponseEntity.ok(this.replyCommentService.listByCommentId(commentId));
    }

}
