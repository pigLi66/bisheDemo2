package com.lsl.demo.model.sys.controller;


import com.lsl.demo.model.sys.dto.ArtistDto;
import com.lsl.demo.model.sys.entity.ArtistEntity;
import com.lsl.demo.model.sys.service.IArtistService;
import com.lsl.demo.common.annotation.interceptor.Auth;
import com.lsl.demo.common.base.controller.BaseCRUDController;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lsl_ja
 * @since 2020-02-26
 */
@ApiOperation("艺术家接口")
@RestController
@RequestMapping("/sys/artist")
public class ArtistController extends BaseCRUDController<ArtistDto, ArtistEntity, IArtistService> {

    @Auth
    @PostMapping
    @Override
    public ResponseEntity<String> save(@RequestBody ArtistDto dto) {
        return super.save(dto);
    }

    @Auth
    @Override
    public ResponseEntity<String> deleteById(String id) {
        return super.deleteById(id);
    }

    @Override
    protected Class<ArtistEntity> getEntityClass() {
        return ArtistEntity.class;
    }

    @Override
    protected ArtistEntity getEntity() {
        return new ArtistEntity();
    }

}
