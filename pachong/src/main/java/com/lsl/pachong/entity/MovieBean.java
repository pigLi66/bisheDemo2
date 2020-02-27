package com.lsl.pachong.entity;

import lombok.Data;

import java.util.List;

/**
 * @author lisiliang
 * @since 2020/2/21
 */
@Data
public class MovieBean {

    private String rate;        // 电影的评级

    private String title;       // 电影的名称

    private String url;         // 电影的url

    private String cover;       // 电影图片的url

    private String id;          // 豆瓣的id

}
