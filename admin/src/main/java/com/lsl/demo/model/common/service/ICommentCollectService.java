package com.lsl.demo.model.common.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lsl.demo.model.common.entity.CommentCollectEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lsl.demo.model.common.entity.MovieEntity;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lsl_ja
 * @since 2020-03-04
 */
public interface ICommentCollectService extends IService<CommentCollectEntity> {

    /**
     * 解析评论中的评论类别，并返回数据库中的id
     * @param comment 评论
     * @return id
     */
    List<String> resolve(String comment);

    /**
     * 根据评论类别进行查找，获取一个页面的数据
     * @param startPage
     * @param pageSize
     * @param collect 获取条件
     * @return
     */
    Page<CommentCollectEntity> getPage(int startPage, int pageSize, String collect);

    /**
     * 新建一个和电影相关的评论集合
     * @param movieEntity 电影实体
     */
    void initByMovie(MovieEntity movieEntity);

}
