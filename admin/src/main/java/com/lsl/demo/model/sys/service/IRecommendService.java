package com.lsl.demo.model.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lsl.demo.model.sys.dto.UserAndMovie;
import com.lsl.demo.model.sys.entity.RecommendEntity;

import java.util.List;

/**
 * @author lisiliang
 * @since 2020/4/2
 */
public interface IRecommendService extends IService<RecommendEntity> {

    /**
     * 增加一次点击
     * @param movieId 点击的用户和电影ID
     */
    void tap(String movieId);

    /**
     * 增加一次评论
     * @param movieId 点击的用户和电影ID
     */
    void comment(String movieId);

    /**
     * 获取用户推荐排名列表
     * @return
     */
    List<String> listSortedUserId();

    /**
     * 获取电影推荐列表
     * @param userId 推荐的用户id
     * @return 推荐列表
     */
    List<String> listSortedMovieId(String userId);

}
