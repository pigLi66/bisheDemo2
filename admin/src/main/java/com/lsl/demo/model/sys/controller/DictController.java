package com.lsl.demo.model.sys.controller;


import com.lsl.demo.common.base.controller.BaseController;
import com.lsl.demo.common.enums.Operation;
import com.lsl.demo.common.exceptions.BusinessException;
import com.lsl.demo.model.sys.entity.DictEntity;
import com.lsl.demo.utils.validate.UpdateGroup;
import com.lsl.demo.utils.validate.ValidatorUtil;
import com.lsl.demo.model.sys.dto.DictDto;
import com.lsl.demo.model.sys.service.IDictService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 字典表 前端控制器
 * </p>
 *
 * @author lsl_ja
 * @since 2020-02-13
 */
@RestController
@RequestMapping("/sys/dict")
@Api(value = "字典接口")
public class DictController extends BaseController<DictEntity, IDictService> {

    @ApiOperation("新建字典,输入字典的名字")
    @PostMapping
    public ResponseEntity<String> saveDict(@RequestBody String dictValue) {
        if (Objects.isNull(dictValue)) {
            throw new BusinessException("字典名不能为空");
        }
        return new ResponseEntity<>(this.service.saveDict(dictValue), HttpStatus.OK);
    }

    @ApiOperation("新增一个字典的值")
    @PostMapping("/v")
    public ResponseEntity<String> saveV(@RequestParam("dictKey") String dictKey, @RequestParam("vValue") String vValue) {
        if (Objects.isNull(dictKey) || Objects.isNull(vValue)) {
            throw  new BusinessException("参数不能为空");
        }
        return new ResponseEntity<>(this.service.saveV(dictKey, vValue), HttpStatus.OK);
    }

    @ApiOperation("根据dcitKey 字典键获取字典值")
    @GetMapping
    public ResponseEntity<String> getDictValue(String dictKey) {
        if (Objects.isNull(dictKey)) {
            throw new BusinessException("字典键不能为空");
        }
        return new ResponseEntity<>(this.service.getDictValue(dictKey), HttpStatus.OK);
    }

    @ApiOperation("根据dictKey 和vKey 获取vValue")
    @GetMapping("/v")
    public ResponseEntity<String> getVValue(String dictKey, String vKey) {
        if (Objects.isNull(dictKey) || Objects.isNull(vKey)) {
            throw new BusinessException("dictKey 和vKey 不能为空");
        }
        return new ResponseEntity<>(this.service.getVValue(dictKey, vKey), HttpStatus.OK);
    }

    @ApiOperation("更新字典，根据dictKey进行修改")
    @PutMapping
    public ResponseEntity<String> upDictValue(@RequestParam("dictKey") String dictKey, @RequestParam("dictValue") String dictValue) {
        if (Objects.isNull(dictKey) || Objects.isNull(dictValue)) {
            throw  new BusinessException("字典不能为空");
        }
        this.service.upDictValue(dictKey, dictValue);
        return new ResponseEntity<>(Operation.SUCCESS.get(), HttpStatus.OK);
    }

    @ApiOperation("根据dictKey 和vKey 更新vValue")
    @PutMapping("/v")
    public ResponseEntity<String> upVValue(@RequestBody DictDto dictDto) {
        ValidatorUtil.validateEntity(dictDto, UpdateGroup.class);
        this.service.upVValue(dictDto);
        return new ResponseEntity<>(Operation.SUCCESS.get(), HttpStatus.OK);
    }

    @ApiOperation("根据dictKey 删除字典")
    @DeleteMapping
    public ResponseEntity<String> deleteDictByKey(String dictKey) {
        if (Objects.isNull(dictKey)) {
            throw new BusinessException("字典键不能为空");
        }
        this.service.deleteDictByKey(dictKey);
        return new ResponseEntity<>(Operation.SUCCESS.get(), HttpStatus.OK);
    }

    @ApiOperation("根据id 删除字典值")
    @DeleteMapping("/v/{id}")
    public ResponseEntity<String> deleteV(@PathVariable String id) {
        this.service.removeById(id);
        return new ResponseEntity<>(Operation.SUCCESS.get(), HttpStatus.OK);
    }

    @ApiOperation("根据dictKey 和vKey 删除字典值")
    @DeleteMapping("/v")
    public ResponseEntity<String> deleteV(DictDto dictDto) {
        if (Objects.isNull(dictDto.getDictKey()) || (Objects.isNull(dictDto.getVKey()) && Objects.isNull(dictDto.getDictValue()))) {
            throw new BusinessException("dictKey，不能为空");
        }
        this.service.deleteV(dictDto);
        return new ResponseEntity<>(Operation.SUCCESS.get(), HttpStatus.OK);
    }

    @ApiOperation("获取所有的字典")
    @GetMapping("/listDict")
    public ResponseEntity<List<String>> getDictList() {
        return new ResponseEntity<>(this.service.getDictList(), HttpStatus.OK);
    }

    @ApiOperation("获取所有的字典值")
    @GetMapping("/v/{dictKey}")
    public ResponseEntity<List<DictDto>> getVList(@PathVariable String dictKey) {
        return new ResponseEntity<>(this.service.getVList(dictKey), HttpStatus.OK);
    }

}
