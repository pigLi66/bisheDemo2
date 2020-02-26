package com.lsl.pachong.entity;

import lombok.Data;

import java.util.List;

/**
 * @author lisiliang
 * @since 2020/2/21
 */
@Data
public class MovieBean {

    private List<String>directors;  // 导演的列表

    private String rate;        // 电影的评级

    private String star;

    private String title;       // 电影的名称

    private String url;         // 电影的url

    private List<String> casts; // 演员列表

    private String cover;       // 电影图片的url

    private String id;          // 豆瓣的id

}
