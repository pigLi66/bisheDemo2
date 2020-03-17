package com.lsl.demo.common.base.controller;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lsl.demo.utils.ConvertUtil;
import com.lsl.demo.common.base.entity.BaseEntity;
import com.lsl.demo.common.enums.Operation;
import com.lsl.demo.utils.validate.AddGroup;
import com.lsl.demo.utils.validate.UpdateGroup;
import com.lsl.demo.utils.validate.ValidatorUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author lisiliang
 * @since 2020/3/11
 */
public abstract class BaseCRUDController<D, E extends BaseEntity, S extends IService<E>>
        extends BaseController<E, S> {

    @PostMapping
    public ResponseEntity<String> save(@RequestBody D dto) {
        ValidatorUtil.validateEntity(dto, AddGroup.class);
        E entity = ConvertUtil.sourceToTarget(dto, this.getEntityClass());
        this.service.save(entity);
        return ResponseEntity.ok(entity.getId());
    }

    @GetMapping("/{id}")
    public ResponseEntity<E> getById(@PathVariable String id) {
        return ResponseEntity.ok(this.service.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateById(@PathVariable String id, @RequestBody D dto) {
        ValidatorUtil.validateEntity(dto, UpdateGroup.class);
        E entity = ConvertUtil.sourceToTarget(dto, this.getEntityClass());
        this.service.updateById(entity);
        return ResponseEntity.ok(Operation.SUCCESS.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable String id) {
        this.service.removeById(id);
        return ResponseEntity.ok(Operation.SUCCESS.get());
    }

    /**
     * 获取实体类的Class对象
     * @return
     */
    protected abstract Class<E> getEntityClass();

    /**
     * 获取实体类的实例
     * @return
     */
    protected abstract E getEntity();

}
