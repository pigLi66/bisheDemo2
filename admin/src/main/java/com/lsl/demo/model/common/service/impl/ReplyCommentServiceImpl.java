package com.lsl.demo.model.common.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lsl.demo.model.common.dto.ReplyCommentInfoDto;
import com.lsl.demo.model.common.entity.ReplyCommentEntity;
import com.lsl.demo.model.common.mapper.ReplyCommentMapper;
import com.lsl.demo.model.common.service.IReplyCommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lsl.demo.model.sys.service.IUserInfoService;
import com.lsl.demo.model.sys.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lsl_ja
 * @since 2020-03-09
 */
@Service
public class ReplyCommentServiceImpl extends ServiceImpl<ReplyCommentMapper, ReplyCommentEntity> implements IReplyCommentService {

    @Autowired
    private IUserService userService;

    @Autowired
    private IUserInfoService userInfoService;

    @Override
    public List<ReplyCommentInfoDto> listByCommentId(String commentId) {
        List<ReplyCommentInfoDto> rsList;
        QueryWrapper<ReplyCommentEntity> replyCommentEntityQueryWrapper = new QueryWrapper<>();
        replyCommentEntityQueryWrapper.eq("comment_id", commentId);
        replyCommentEntityQueryWrapper.orderByAsc("id");
         List<ReplyCommentEntity> replyCommentEntityList = this.baseMapper.selectList(replyCommentEntityQueryWrapper);
        rsList = replyCommentEntityList.stream().map(item -> {
            ReplyCommentInfoDto rs = new ReplyCommentInfoDto();
            rs.setComment(item.getComment());
            rs.setPictureUrl(userService.getById(item.getUserId()).getPictureUrl());
            rs.setUserName(userInfoService.getUserInfo(item.getUserId()).getUserName());
            return  rs;
        }).collect(Collectors.toList());
        return rsList;
    }
}
