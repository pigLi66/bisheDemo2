package com.lsl.demo.model.restapi.controller;

import com.lsl.demo.common.base.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.lslllxq.utils.print.Print;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lisiliang
 * @since 2020/3/16
 */
@Api("api接口")
@RestController
@RequestMapping("/api")
public class ApiController {

    private ApplicationContext applicationContext;

    public ApiController(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @ApiOperation("获取所有的api接口")
    @GetMapping
    public ResponseEntity<Map<String, String>> get() {
        Map<String, Object> map =
                this.applicationContext.getBeansWithAnnotation(RestController.class);
        Map<String, String> rsMap = new HashMap<>(map.size() << 1);
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            RequestMapping requestMapping = entry.getValue().getClass().getAnnotation(RequestMapping.class);
            if (null == requestMapping) {
                Print.print(entry.getKey() + entry.getValue().getClass().getName());
            } else {
                rsMap.put(entry.getKey().substring(0, entry.getKey().indexOf("Controller")),
                        String.join(",", requestMapping.value()));
            }
        }
        return ResponseEntity.ok(rsMap);
    }

}
