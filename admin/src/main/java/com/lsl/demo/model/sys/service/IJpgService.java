package com.lsl.demo.model.sys.service;

import java.io.InputStream;

/**
 * @author lisiliang
 * @since 2020/3/17
 */
public interface IJpgService {

    /**
     * 保存图片
     * @param inputStream 图片的输入流
     * @return 图片的地址
     */
    String saveJpg(InputStream inputStream);

}
