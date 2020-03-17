package com.lsl.demo.model.common.dto;

import com.lsl.demo.model.sys.entity.ArtistEntity;
import lombok.Data;

import java.util.List;

/**
 * @author lisiliang
 * @since 2020/2/29
 */
@Data
public class MovieInfoDto {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 电影名
     */
    private String movieName;

    /**
     * 导演
     */
    private List<ArtistEntity> directors;

    /**
     * 演员
     */
    private List<ArtistEntity> performers;

    /**
     * 电影类别id
     */
    private String classIds;

    /**
     * 电影时长
     */
    private String span;

    /**
     * 电影简介
     */
    private String profile;

    /**
     * 电影图片的地址
     */
    private String pictureUrl;

    /**
     * 上映时间
     */
    private String releaseTime;

    /**
     * 电影的评级
     */
    private Double level;

}
