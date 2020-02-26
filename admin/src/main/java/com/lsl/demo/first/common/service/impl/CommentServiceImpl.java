package com.lsl.demo.first.common.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lsl.demo.first.common.dto.CommentDto;
import com.lsl.demo.first.common.entity.CommentEntity;
import com.lsl.demo.first.common.mapper.CommentMapper;
import com.lsl.demo.first.common.service.ICommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lsl.demo.first.utils.ConvertUtil;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 电影评论表 服务实现类
 * </p>
 *
 * @author lsl_ja
 * @since 2020-02-13
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, CommentEntity> implements ICommentService {

    @Override
    public String saveComment(CommentDto dto) {
        CommentEntity entity = ConvertUtil.sourceToTarget(dto, CommentEntity.class);
        this.baseMapper.insert(entity);
        return entity.getId();
    }

    @Override
    public List<CommentEntity> getUserCommentList(String userId) {
        QueryWrapper<CommentEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        queryWrapper.eq("valid", 0);
        return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    public List<CommentEntity> getMovieCommentList(String movieId) {
        QueryWrapper<CommentEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("valid", 0);
        queryWrapper.eq("movie_id", movieId);
        return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    public void deleteMovieComment(String movieId) {
        QueryWrapper<CommentEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("movie_id", movieId);
        this.baseMapper.delete(queryWrapper);
    }

    @Override
    public void deleteUserComment(String userId) {
        QueryWrapper<CommentEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        this.baseMapper.delete(queryWrapper);
    }
}
