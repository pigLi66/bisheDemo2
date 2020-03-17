package com.lsl.demo.model.common.service;

import com.lsl.demo.model.common.dto.ReplyCommentInfoDto;
import com.lsl.demo.model.common.entity.ReplyCommentEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lsl_ja
 * @since 2020-03-09
 */
public interface IReplyCommentService extends IService<ReplyCommentEntity> {

    /**
     * 获取评论的全部回复，根据评论的id
     * @param commentId
     * @return
     */
    List<ReplyCommentInfoDto> listByCommentId(String commentId);

}
