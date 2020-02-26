package com.lsl.demo.first.common.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lsl.demo.first.common.dto.CommentCountDto;
import com.lsl.demo.first.common.entity.CommentCountEntity;
import com.lsl.demo.first.common.mapper.CommentCountMapper;
import com.lsl.demo.first.common.service.ICommentCountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lsl.demo.first.utils.ConvertUtil;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * <p>
 * 评论点赞计数 服务实现类
 * </p>
 *
 * @author lsl_ja
 * @since 2020-02-15
 */
@Service
public class CommentCountServiceImpl extends ServiceImpl<CommentCountMapper, CommentCountEntity> implements ICommentCountService {

    @Override
    public String save(CommentCountDto dto) {
        QueryWrapper<CommentCountEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("comment_id", dto.getCommentId());
        queryWrapper.eq("user_id", dto.getUserId());
        queryWrapper.eq("type", dto.getType());
        CommentCountEntity entity = this.baseMapper.selectOne(queryWrapper);

        if (Objects.isNull(entity)) {
            entity = ConvertUtil.sourceToTarget(dto, CommentCountEntity.class);
            this.baseMapper.insert(entity);
        } else {
            entity.setValid("0");
            this.baseMapper.updateById(entity);
        }
        return entity.getId();
    }

    @Override
    public void delete(CommentCountDto dto) {
        QueryWrapper<CommentCountEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("comment_id", dto.getCommentId());
        queryWrapper.eq("user_id", dto.getUserId());
        queryWrapper.eq("type", dto.getType());
        this.baseMapper.delete(queryWrapper);
    }

    @Override
    public Integer count(String commentId, String type) {
        QueryWrapper<CommentCountEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("comment_id", commentId);
        queryWrapper.eq("type", type);
        return this.baseMapper.selectList(queryWrapper).size();
    }

}
