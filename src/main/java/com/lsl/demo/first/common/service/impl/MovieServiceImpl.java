package com.lsl.demo.first.common.service.impl;

import com.lsl.demo.first.common.entity.MovieEntity;
import com.lsl.demo.first.common.mapper.MovieMapper;
import com.lsl.demo.first.common.service.IMovieService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 电影表 服务实现类
 * </p>
 *
 * @author lsl_ja
 * @since 2020-02-13
 */
@Service
public class MovieServiceImpl extends ServiceImpl<MovieMapper, MovieEntity> implements IMovieService {

}
