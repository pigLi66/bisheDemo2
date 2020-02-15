package com.lsl.demo.first.common.service.impl;

import com.lsl.demo.first.common.entity.CommentEntity;
import com.lsl.demo.first.common.mapper.CommentMapper;
import com.lsl.demo.first.common.service.ICommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
