package com.lsl.demo.model.common.dto;

import lombok.Data;

/**
 * @author lisiliang
 * @since 2020/2/29
 */
@Data
public class MovieAbstractDto {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 电影名
     */
    private String movieName;

    /**
     * 电影图片的地址
     */
    private String pictureUrl;

    /**
     * 电影评级
     */
    private Double level;

}
