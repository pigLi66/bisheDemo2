package com.lsl.demo.first.common.service;

import com.lsl.demo.first.common.dto.LevelDto;
import com.lsl.demo.first.common.entity.LevelEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 电影评级表 服务类
 * </p>
 *
 * @author lsl_ja
 * @since 2020-02-13
 */
public interface ILevelService extends IService<LevelEntity> {

    /**
     * 保存或者更新用户的评级
     * @param dto
     * @return 主键id
     */
    String saveOrUpLevel(LevelDto dto);

    /**
     * 获取单个电影的评级
     * @param movieId
     * @return
     */
    String getMovieLevel(String movieId);

    /**
     * 获取一个电影的所有评级
     * @param movieId
     * @return
     */
    List<Integer> getLevelList(String movieId);

    /**
     * 获取单个人员的评级
     * @param movieId
     * @param userId
     * @return
     */
    Integer getLevel(String movieId, String userId);

}
