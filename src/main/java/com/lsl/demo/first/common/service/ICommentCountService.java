package com.lsl.demo.first.common.service;

import com.lsl.demo.first.common.dto.CommentCountDto;
import com.lsl.demo.first.common.entity.CommentCountEntity;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 评论点赞计数 服务类
 * </p>
 *
 * @author lsl_ja
 * @since 2020-02-15
 */
public interface ICommentCountService extends IService<CommentCountEntity> {

    /**
     * 保存用户行为
     * @param dto
     */
    String save(CommentCountDto dto);

    /**
     * 删除用户某个行为
     * @param dto
     */
    void delete(CommentCountDto dto);

    /**
     * 获取评论下特定用户的所有行为统计
     * @param commentId
     * @param type
     * @return
     */
    Integer count(String commentId, String type);

}
