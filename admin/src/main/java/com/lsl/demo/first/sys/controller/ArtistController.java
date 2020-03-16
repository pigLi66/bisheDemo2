package com.lsl.demo.first.sys.controller;


import com.lsl.demo.first.sys.dto.ArtistDto;
import com.lsl.demo.first.sys.entity.ArtistEntity;
import com.lsl.demo.first.sys.service.IArtistService;
import com.lsl.demo.first.utils.annotation.interceptor.Auth;
import com.lsl.demo.first.utils.base.controller.BaseCRUDController;
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
