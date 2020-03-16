package com.lsl.demo.first.utils.base.controller;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lsl.demo.first.utils.base.entity.BaseEntity;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author lisiliang
 * @since 2020/3/11
 */
public class BaseController<E extends BaseEntity, S extends IService<E>> {

    @Autowired
    protected S service;

}
