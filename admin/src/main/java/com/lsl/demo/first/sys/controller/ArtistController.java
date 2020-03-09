package com.lsl.demo.first.sys.controller;


import com.lsl.demo.first.sys.dto.ArtistDto;
import com.lsl.demo.first.sys.entity.ArtistEntity;
import com.lsl.demo.first.sys.service.IArtistService;
import com.lsl.demo.first.utils.validate.AddGroup;
import com.lsl.demo.first.utils.validate.UpdateGroup;
import com.lsl.demo.first.utils.validate.ValidatorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
public class ArtistController {

    @Autowired
    private IArtistService artistService;

    @PostMapping
    public ResponseEntity<String> saveArtist(@RequestBody ArtistDto dto) {
        ValidatorUtil.validateEntity(dto, AddGroup.class    );
        return new ResponseEntity<>("接口暂未开放", HttpStatus.FORBIDDEN);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArtistEntity> getArtistById(@PathVariable String id) {
        return ResponseEntity.ok(artistService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateArtistById(@RequestBody ArtistDto dto, @PathVariable String id) {
        ValidatorUtil.validateEntity(dto, UpdateGroup.class);
        return new ResponseEntity<>("接口暂未开放", HttpStatus.FORBIDDEN);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteArtistById(@PathVariable String id) {
        return new ResponseEntity<>("接口暂未开放", HttpStatus.FORBIDDEN);
    }

}
