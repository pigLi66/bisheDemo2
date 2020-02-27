package com.lsl.demo;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@MapperScan("com.lsl.demo.**.**.mapper")
@SpringBootApplication
@EnableSwagger2Doc
public class BisheDemo2Application {

	public static void main(String[] args) {
		SpringApplication.run(BisheDemo2Application.class, args);
	}

}
