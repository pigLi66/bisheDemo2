package com.lsl.demo.first.common.service;

import com.lsl.demo.first.common.dto.MovieDto;
import com.lsl.demo.first.common.entity.MovieEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 电影表 服务类
 * </p>
 *
 * @author lsl_ja
 * @since 2020-02-13
 */
public interface IMovieService extends IService<MovieEntity> {

    /**
     * 增加一个电影相关的信息
     * @param movieDto
     * @return
     */
    String save(MovieDto movieDto);

    /**
     * 根据电影名字搜索电影
     * @param movieName
     * @return
     */
    List<MovieEntity> searchByName(String movieName);

    /**
     * 通过电影的类别id获取电影
     * @param classId
     * @return
     */
    List<MovieEntity> getByClassList(String classId);

    /**
     * 获取所有的电影
     * @return
     */
    List<MovieEntity> getAllMovieList();

}
