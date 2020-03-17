package com.lsl.demo.model.common.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lsl.demo.model.common.dto.CommentDto;
import com.lsl.demo.model.common.dto.CommentInfoRSDto;
import com.lsl.demo.model.common.entity.CommentCollectEntity;
import com.lsl.demo.model.common.entity.CommentEntity;
import com.lsl.demo.model.common.mapper.CommentMapper;
import com.lsl.demo.model.common.service.ICommentCollectService;
import com.lsl.demo.model.common.service.ICommentCountService;
import com.lsl.demo.model.common.service.ICommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lsl.demo.utils.BaseContextHandler;
import com.lsl.demo.utils.ConvertUtil;
import com.lsl.demo.utils.db.PageBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

    @Autowired
    private ICommentCollectService collectService;

    @Autowired
    private ICommentCountService commentCountService;

    @Override
    public String saveComment(CommentDto dto) {
        String comment = dto.getComment();
        CommentEntity entity = new CommentEntity();
        entity.setComment(comment);
        entity.setUserId(BaseContextHandler.getUserId());
        entity.setCollectIds(String.join(",", collectService.resolve(comment)));
        this.baseMapper.insert(entity);
        return entity.getId();
    }

    @Override
    public String updateById(String id, CommentDto dto) {
        CommentEntity entity = ConvertUtil.sourceToTarget(dto, CommentEntity.class);
        entity.setUserId(BaseContextHandler.getUserId());
        entity.setId(id);
        entity.setCollectIds(String.join(",", collectService.resolve(dto.getComment())));
        return Integer.toString(this.baseMapper.updateById(entity));
    }

    @Override
    public IPage<CommentInfoRSDto> getPage(int startPage, int pageSize, String comment) {
        QueryWrapper<CommentEntity> commentQueryWrapper = new QueryWrapper<>();
        commentQueryWrapper.like("comment", comment);
        List<CommentEntity> commentList = this.baseMapper.selectList(commentQueryWrapper);
        return PageBuilder.build(startPage, pageSize,
                this.listCommentInfoById(commentList.stream().map(CommentEntity::getId).collect(Collectors.toList())));
    }

    @Override
    public List<CommentEntity> getUserCommentList(String userId) {
        QueryWrapper<CommentEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        queryWrapper.eq("valid", 0);
        return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    public Page<CommentInfoRSDto> getMovieCommentPage(int startPage, int pageSize, String collectId) {
        return PageBuilder.build(startPage, pageSize, this.listCommentInfoByCollectId(collectId));
    }

    @Override
    public void deleteMovieComment(String commentId) {
        QueryWrapper<CommentEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("collect_ids", commentId);
        this.baseMapper.delete(queryWrapper);
    }

    @Override
    public void deleteUserComment(String userId) {
        QueryWrapper<CommentEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        this.baseMapper.delete(queryWrapper);
    }

    private List<CommentInfoRSDto> listCommentInfoByCollectId(String collectId) {
        QueryWrapper<CommentEntity> commentQueryWrapper = new QueryWrapper<>();
        commentQueryWrapper.like("collect_ids", collectId);
        List<CommentEntity> commentList = this.baseMapper.selectList(commentQueryWrapper);
        return this.listCommentInfoById(commentList.stream().map(CommentEntity::getId).collect(Collectors.toList()));
    }

    private List<CommentInfoRSDto> listCommentInfoById(List<String> ids) {
        List<CommentInfoRSDto> rsList = this.baseMapper.listCommentInfoById(ids);
        rsList.forEach(item -> item.setCount(commentCountService.count(item.getCommentId(), "0")));
        rsList.forEach(item ->{
            List<String> collectIds = Arrays.asList(item.getCollectIds().split(","));
            List<CommentCollectEntity> collects = collectService.listByIds(collectIds);
            item.setCollect(collects.stream().map(CommentCollectEntity::getName).collect(Collectors.joining(",")));
        });
        return rsList;
    }

    @Override
    public CommentInfoRSDto getCommentInfoById(String id) {
        List<String> param = new ArrayList<>(1);
        param.add(id);
        List<CommentInfoRSDto> rs = this.listCommentInfoById(param);
        return rs.size() > 0 ? rs.get(0) : null;
    }

}
