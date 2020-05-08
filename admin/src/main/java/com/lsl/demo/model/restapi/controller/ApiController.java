package com.lsl.demo.model.restapi.controller;

import com.lsl.demo.common.annotation.aop.MyTest;
import com.lsl.demo.common.aop.MyTestAdvisor;
import com.lsl.demo.common.exceptions.ServerException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.lslllxq.utils.print.Print;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author lisiliang
 * @since 2020/3/16
 */
@Api("api接口")
@RestController
@RequestMapping("/api")
public class ApiController implements CommandLineRunner {


    private final ApplicationContext applicationContext;
    private Map<String, String> apiMap;

    public ApiController(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @ApiOperation("获取所有的api接口")
    @GetMapping
    @MyTest
    public ResponseEntity<Map<String, String>> get() {
        if (apiMap == null) {
            throw new ServerException();
        } else {
            Print.print(this.apiMap);
            return ResponseEntity.ok(this.apiMap);
        }
    }

    @Override
    public void run(String... args) {
        Map<String, Object> map =
                this.applicationContext.getBeansWithAnnotation(RestController.class);
        this.apiMap = new HashMap<>(map.size() << 1);
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            Print.print(entry.getValue().getClass().getName());
            RequestMapping requestMapping = null;
            Class<?> clazz = entry.getValue().getClass();
            while (clazz != Object.class &&Objects.isNull(requestMapping)) {
                requestMapping = clazz.getAnnotation(RequestMapping.class);
                clazz = clazz.getSuperclass();
            }
            if (Objects.nonNull(requestMapping)) {
                apiMap.put(entry.getKey().substring(0, entry.getKey().indexOf("Controller")),
                        String.join(",", requestMapping.value()));
            }
        }

        Print.print(this.apiMap);
        /**
         * 自己的一些测试用的代码
         */
        this.test();
    }

    private void test() {
        Map<String, MyTestAdvisor> map = this.applicationContext.getBeansOfType(MyTestAdvisor.class);
        map.entrySet().forEach(Print::print);
    }

}
