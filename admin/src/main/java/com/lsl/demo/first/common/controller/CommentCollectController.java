package com.lsl.demo.first.common.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lsl.demo.first.common.dto.CommentCollectDto;
import com.lsl.demo.first.common.entity.CommentCollectEntity;
import com.lsl.demo.first.common.service.ICommentCollectService;
import com.lsl.demo.first.utils.ConvertUtil;
import com.lsl.demo.first.utils.annotation.interceptor.handler.Auth;
import com.lsl.demo.first.utils.enums.Operation;
import com.lsl.demo.first.utils.validate.AddGroup;
import com.lsl.demo.first.utils.validate.UpdateGroup;
import com.lsl.demo.first.utils.validate.ValidatorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author lsl_ja
 * @since 2020-03-04
 */
@RestController
@RequestMapping("/common/comment/collect")
public class CommentCollectController {

    @Autowired
    private ICommentCollectService commentCollectService;

    @Auth
    @PostMapping
    public ResponseEntity<String> save(@RequestBody CommentCollectDto dto) {
        ValidatorUtil.validateEntity(dto, AddGroup.class);
        CommentCollectEntity entity = ConvertUtil.sourceToTarget(dto, CommentCollectEntity.class);
        this.commentCollectService.save(entity);
        return ResponseEntity.ok(entity.getId());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentCollectEntity> getById(@PathVariable String id) {
        return ResponseEntity.ok(this.commentCollectService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateById(@PathVariable String id, @RequestBody CommentCollectDto dto) {
        ValidatorUtil.validateEntity(dto, UpdateGroup.class);
        CommentCollectEntity entity = ConvertUtil.sourceToTarget(dto, CommentCollectEntity.class);
        entity.setId(id);
        this.commentCollectService.updateById(entity);
        return ResponseEntity.ok(Operation.SUCCESS.get());
    }

    @GetMapping
    public ResponseEntity<Page<CommentCollectEntity>> getPage(@RequestParam(defaultValue = "1") int startPage,
                                                              @RequestParam(defaultValue = "5") int pageSize,
                                                              @RequestParam(defaultValue = "") String collect) {
        return ResponseEntity.ok(this.commentCollectService.getPage(startPage, pageSize, collect));
    }

}
