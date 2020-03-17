package com.lsl.demo.model.sys.controller;

import com.lsl.demo.model.sys.service.IJpgService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author lisiliang
 * @since 2020/3/17
 */
@RestController
@RequestMapping("/jpg")
public class JpgController {

    private IJpgService jpgService;

    public JpgController(IJpgService jpgService) {
        this.jpgService = jpgService;
    }

    @PostMapping
    public ResponseEntity<String> save(@RequestParam MultipartFile file) throws IOException {
        return ResponseEntity.ok(this.jpgService.saveJpg(file.getInputStream()));
    }

}
