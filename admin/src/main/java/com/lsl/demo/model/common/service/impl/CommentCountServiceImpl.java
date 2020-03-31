package com.lsl.demo.model.common.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lsl.demo.model.common.dto.CommentCountDto;
import com.lsl.demo.model.common.entity.CommentCountEntity;
import com.lsl.demo.model.common.mapper.CommentCountMapper;
import com.lsl.demo.model.common.service.ICommentCountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lsl.demo.utils.ConvertUtil;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

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
    synchronized public Integer saveOrDelete(CommentCountDto dto) {
        Wrapper<CommentCountEntity> queryWrapper = new QueryWrapper<CommentCountEntity>().lambda()
                .eq(CommentCountEntity::getCommentId, dto.getCommentId())
                .eq(CommentCountEntity::getUserId, dto.getUserId())
                .eq(CommentCountEntity::getType, dto.getType());
        Optional<CommentCountEntity> entity = Optional.ofNullable(this.baseMapper.selectOne(queryWrapper));
        return entity.map(item -> {
            if ("0".equals(item.getValid())) {
                baseMapper.deleteById(item.getId());
                return -1;
            } else {
                item.setValid("0");
                baseMapper.updateById(item);
                return 1;
            }
        }).orElseGet(() -> {
            save(dto);
            return 1;
        });
    }

    @Override
    public String save(CommentCountDto dto) {
        Wrapper<CommentCountEntity> queryWrapper = new QueryWrapper<CommentCountEntity>().lambda()
                .eq(CommentCountEntity::getCommentId, dto.getCommentId())
                .eq(CommentCountEntity::getUserId, dto.getUserId())
                .eq(CommentCountEntity::getType, dto.getType());
        Optional<CommentCountEntity> entity = Optional.ofNullable(this.baseMapper.selectOne(queryWrapper));

        return entity.map(item -> {
            item.setValid("0");
            baseMapper.updateById(item);
            return item.getId();
        }).orElseGet(() -> {
            CommentCountEntity insertEntity = ConvertUtil.sourceToTarget(dto, CommentCountEntity.class);
            baseMapper.insert(insertEntity);
            return insertEntity.getId();
        });
    }

    @Override
    public void delete(CommentCountDto dto) {
        Wrapper<CommentCountEntity> queryWrapper = new QueryWrapper<CommentCountEntity>().lambda()
                .eq(CommentCountEntity::getCommentId, dto.getCommentId())
                .eq(CommentCountEntity::getUserId, dto.getUserId())
                .eq(CommentCountEntity::getType, dto.getType());
        this.baseMapper.delete(queryWrapper);
    }

    @Override
    public Integer count(String commentId, String type) {
        QueryWrapper<CommentCountEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("comment_id", commentId);
        queryWrapper.eq("type", type);
        return this.baseMapper.selectCount(queryWrapper);
    }

}
