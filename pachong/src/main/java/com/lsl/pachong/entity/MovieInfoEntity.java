package com.lsl.pachong.entity;

import lombok.Data;

import java.util.List;

/**
 * @author lisiliang
 * @since 2020/2/25
 */
@Data
public class MovieInfoEntity {

    private List<Human> directors;

    private List<Human> performers;

    private String releaseTime;

    private String span;

    private String profile;

    private String picUrl;

    private Double level;

    private String doubanId;

    private String classId;

}
