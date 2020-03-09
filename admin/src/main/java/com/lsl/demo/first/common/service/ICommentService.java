package com.lsl.demo.first.common.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lsl.demo.first.common.dto.CommentDto;
import com.lsl.demo.first.common.dto.CommentInfoRSDto;
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
     * 根据id更新评论数据
     * @param dto
     * @return
     */
    String updateById(String id, CommentDto dto);

    /**
     * 获取一个页面的数据
     * @param startPage
     * @param pageSize
     * @param comment
     * @return
     */
    IPage<CommentInfoRSDto> getPage(int startPage, int pageSize, String comment);

    /**
     * 获取一个用户的所有评论
     * @param userId
     * @return
     */
    List<CommentEntity> getUserCommentList(String userId);

    /**
     * 获取一个评论集下的一页评论
     * @param startPage
     * @param pageSize
     * @param collectId
     * @return
     */
    Page<CommentInfoRSDto> getMovieCommentPage(int startPage, int pageSize, String collectId);

    /**
     * 用过id 获取评论信息
     * @param id
     * @return
     */
    CommentInfoRSDto getCommentInfoById(String id);

    /**
     * 删除一个电影下的所有评论
     * @param commentId
     */
    void deleteMovieComment(String commentId);

    /**
     * 删除一个用户的所有评论
     * @param userId
     */
    void deleteUserComment(String userId);

}
