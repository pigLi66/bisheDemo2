package com.lsl.demo.model.common.controller;


import com.lsl.demo.common.base.controller.BaseController;
import com.lsl.demo.model.common.dto.ReplyCommentDto;
import com.lsl.demo.model.common.dto.ReplyCommentInfoDto;
import com.lsl.demo.model.common.entity.ReplyCommentEntity;
import com.lsl.demo.model.common.service.IReplyCommentService;
import com.lsl.demo.utils.BaseContextHandler;
import com.lsl.demo.utils.ConvertUtil;
import com.lsl.demo.common.annotation.interceptor.Auth;
import com.lsl.demo.utils.validate.AddGroup;
import com.lsl.demo.utils.validate.ValidatorUtil;
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
public class ReplyCommentController extends BaseController<ReplyCommentEntity, IReplyCommentService> {

    @Auth
    @PostMapping
    public ResponseEntity<String> save(@RequestBody ReplyCommentDto dto) {
        ValidatorUtil.validateEntity(dto, AddGroup.class);
        dto.setUserId(BaseContextHandler.getUserId());
        ReplyCommentEntity entity = ConvertUtil.sourceToTarget(dto, ReplyCommentEntity.class);
        this.service.save(entity);
        return ResponseEntity.ok(entity.getId());
    }

    @GetMapping("/list/{commentId}")
    public ResponseEntity<List<ReplyCommentInfoDto>> listByCommentId(@PathVariable String commentId) {
        return ResponseEntity.ok(this.service.listByCommentId(commentId));
    }

}
