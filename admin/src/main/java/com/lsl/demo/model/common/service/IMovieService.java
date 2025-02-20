package com.lsl.demo.model.common.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lsl.demo.model.common.dto.MovieDto;
import com.lsl.demo.model.common.dto.MovieAbstractDto;
import com.lsl.demo.model.common.dto.MovieInfoDto;
import com.lsl.demo.model.common.entity.MovieEntity;
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

    /**
     * 获取一页的数据
     * @param startPage 显示的页数
     * @param pageLimit 一页的数据量
     * @return
     */
    IPage<MovieAbstractDto> getPage(int startPage, int pageLimit);

    /**
     * 通过id获取电影信息
     * @param id
     * @return
     */
    MovieInfoDto getMovieInfoById(String id);

}
