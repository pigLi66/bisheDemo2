package com.lsl.demo.model.common.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lsl.demo.model.common.entity.CommentCollectEntity;
import com.lsl.demo.model.common.entity.MovieEntity;
import com.lsl.demo.model.common.mapper.CommentCollectMapper;
import com.lsl.demo.model.common.service.ICommentCollectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lsl.demo.utils.global.BaseContextHandler;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author lsl_ja
 * @since 2020-03-04
 */
@Service
public class CommentCollectServiceImpl extends ServiceImpl<CommentCollectMapper, CommentCollectEntity> implements ICommentCollectService {

    @Override
    public List<String> resolve(String comment) {
        int start, end;
        end = -1;
        List<String> collects = new ArrayList<>(10);
        while (true) {
            start = comment.indexOf("#", end + 1);
            if (start == -1) {
                break;
            }
            end = comment.indexOf("#", start + 1);
            if (end == -1) {
                break;
            }
            String t = comment.substring(start + 1, end);
            collects.add(StrUtil.trim(t));
        }
        return collects.size() == 0 ? collects : this.getId(collects);
    }

    @Override
    public Page<CommentCollectEntity> getPage(int startPage, int pageSize, String collect) {
        Page<CommentCollectEntity> rsPage = new Page<>(startPage, pageSize);
        QueryWrapper<CommentCollectEntity> collectQueryWrapper = new QueryWrapper<>();
        collectQueryWrapper.like("name", collect);
        this.baseMapper.selectPage(rsPage, collectQueryWrapper);
        return rsPage;
    }

    @Override
    public void initByMovie(MovieEntity movieEntity) {
        Optional<CommentCollectEntity> commentCollectEntity =
                Optional.ofNullable(this.baseMapper.selectOne(
                new QueryWrapper<CommentCollectEntity>().lambda()
                .eq(CommentCollectEntity::getName, movieEntity.getMovieName())
        ));
        CommentCollectEntity entity = commentCollectEntity.orElseGet(CommentCollectEntity::new);
        entity.setPictureUrl(Objects.isNull(entity.getPictureUrl()) ? movieEntity.getPictureUrl() : entity.getPictureUrl());
        entity.setCreateBy(Objects.isNull(entity.getCreateBy()) ? "admin" : entity.getCreateBy());
        entity.setProfile(Objects.isNull(entity.getProfile()) ? movieEntity.getProfile() : entity.getProfile());
        entity.setName(Objects.isNull(entity.getName()) ? movieEntity.getMovieName() : entity.getName());
        entity.setId(Objects.isNull(entity.getId()) ? movieEntity.getId() : entity.getId());
        this.saveOrUpdate(entity);
    }

    /**
     * 查找一个评论类别的id，不存在时会插入进数据库
     * @param collect
     * @return
     */
    private String saveAndGetId(String collect) {
        collect = StrUtil.trim(collect);
        QueryWrapper<CommentCollectEntity> commentCollectEntityQueryWrapper = new QueryWrapper<>();
        commentCollectEntityQueryWrapper.eq("name", collect);
        CommentCollectEntity entity = this.baseMapper.selectOne(commentCollectEntityQueryWrapper);
        if (Objects.isNull(entity)) {
            entity = new CommentCollectEntity();
            entity.setName(collect);
            if (BaseContextHandler.getUserId() == null) {
                entity.setCreateBy("lsl");
            } else {
                entity.setCreateBy(BaseContextHandler.getUserId());
            }
            this.baseMapper.insert(entity);
        }
        return entity.getId();
    }

    private List<String> getId(Collection<String> collects) {
        return this.list(new QueryWrapper<CommentCollectEntity>().in("name", collects))
                .stream()
                .map(CommentCollectEntity::getId)
                .collect(Collectors.toList());
    }

    private List<String> saveAndGetId(List<String> collects) {
        return collects.stream().map(this::saveAndGetId).collect(Collectors.toList());
    }

}
