package com.lsl.demo.model.restapi.controller;

import com.lsl.demo.common.exceptions.ServerException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
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
public class ApiController implements CommandLineRunner {


    private ApplicationContext applicationContext;
    private Map<String, String> apiMap;

    public ApiController(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @ApiOperation("获取所有的api接口")
    @GetMapping
    public ResponseEntity<Map<String, String>> get() {
        if (apiMap == null) {
            throw new ServerException();
        } else {
            return ResponseEntity.ok(apiMap);
        }
    }

    @Override
    public void run(String... args) {
        Map<String, Object> map =
                this.applicationContext.getBeansWithAnnotation(RestController.class);
        this.apiMap = new HashMap<>(map.size() << 1);
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            RequestMapping requestMapping = entry.getValue().getClass().getAnnotation(RequestMapping.class);
            if (null == requestMapping) {
                Print.print(entry.getKey() + entry.getValue().getClass().getName());
            } else {
                apiMap.put(entry.getKey().substring(0, entry.getKey().indexOf("Controller")),
                        String.join(",", requestMapping.value()));
            }
        }

        /**
         * 自己的一些测试用的代码
         */
        this.test();
    }

    private void test() {
        Map<String, HandlerInterceptorAdapter> interceptorAdapterMap =
                this.applicationContext.getBeansOfType(HandlerInterceptorAdapter.class);
        Print.print(interceptorAdapterMap);
    }

}
