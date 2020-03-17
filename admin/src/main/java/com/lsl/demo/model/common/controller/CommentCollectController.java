package com.lsl.demo.model.common.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lsl.demo.common.CommonConstants;
import com.lsl.demo.common.base.controller.BaseCRUDController;
import com.lsl.demo.model.common.dto.CommentCollectDto;
import com.lsl.demo.model.common.entity.CommentCollectEntity;
import com.lsl.demo.model.common.service.ICommentCollectService;
import io.swagger.annotations.Api;
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
@Api("用户超话接口")
@RestController
@RequestMapping("/common/comment/collect")
public class CommentCollectController
        extends BaseCRUDController<CommentCollectDto, CommentCollectEntity, ICommentCollectService> {


    @DeleteMapping("{id}")
    @Override
    public ResponseEntity<String> deleteById(@PathVariable String id) {
        return ResponseEntity.ok(CommonConstants.MSG_NON_API);
    }

    @GetMapping
    public ResponseEntity<Page<CommentCollectEntity>> getPage(@RequestParam(defaultValue = "1") int startPage,
                                                              @RequestParam(defaultValue = "5") int pageSize,
                                                              @RequestParam(defaultValue = "") String collect) {
        return ResponseEntity.ok(this.service.getPage(startPage, pageSize, collect));
    }

    @Override
    protected Class<CommentCollectEntity> getEntityClass() {
        return CommentCollectEntity.class;
    }

    @Override
    protected CommentCollectEntity getEntity() {
        return new CommentCollectEntity();
    }

}
