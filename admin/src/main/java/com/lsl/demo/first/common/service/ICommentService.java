package com.lsl.demo.first.common.service;

import com.lsl.demo.first.common.dto.CommentDto;
import com.lsl.demo.first.common.entity.CommentEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 电影评论表 服务类
 * </p>
 *
 * @author lsl_ja
 * @since 2020-02-13
 */
public interface ICommentService extends IService<CommentEntity> {

    /**
     * 保存评论
     * @param dto
     * @return
     */
    String saveComment(CommentDto dto);

    /**
     * 获取一个用户的所有评论
     * @param userId
     * @return
     */
    List<CommentEntity> getUserCommentList(String userId);

    /**
     * 获取一个电影的所有评论
     * @param movieId
     * @return
     */
    List<CommentEntity> getMovieCommentList(String movieId);

    /**
     * 删除一个电影下的所有评论
     * @param movieId
     */
    void deleteMovieComment(String movieId);

    /**
     * 删除一个用户的所有评论
     * @param userId
     */
    void deleteUserComment(String userId);

}
