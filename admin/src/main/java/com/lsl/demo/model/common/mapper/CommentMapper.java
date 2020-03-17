package com.lsl.demo.model.common.mapper;

import com.lsl.demo.model.common.dto.CommentInfoRSDto;
import com.lsl.demo.model.common.entity.CommentEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 电影评论表 Mapper 接口
 * </p>
 *
 * @author lsl_ja
 * @since 2020-02-13
 */
public interface CommentMapper extends BaseMapper<CommentEntity> {

    /**
     * 通过评论id 获取评论信息
     * @param ids
     * @return
     */
    List<CommentInfoRSDto> listCommentInfoById(@Param("ids") List<String> ids);

}
