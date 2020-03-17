package com.lsl.demo.common.base.controller;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lsl.demo.common.base.entity.BaseEntity;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author lisiliang
 * @since 2020/3/11
 */
public class BaseController<E extends BaseEntity, S extends IService<E>> {

    @Autowired
    protected S service;

}
