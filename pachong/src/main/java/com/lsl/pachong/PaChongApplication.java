package com.lsl.pachong;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import com.lsl.demo.BisheDemo2Application;

/**
 * @author lisiliang
 * @since 2020/2/25
 */

@SpringBootApplication
@ComponentScan(basePackages = {"com.lsl.demo", "com.lsl.pachong"})
public class PaChongApplication {
     public static void main(String[] args) {
        SpringApplication.run(PaChongApplication.class, args);
    }
}
