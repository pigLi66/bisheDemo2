package com.lsl.demo;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.servlet.mvc.method.annotation.HttpEntityMethodProcessor;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class AdminApplicationTests {

	@Test
	void testService(){
		System.out.println("???????");
	}

	@Test
	void contextLoads() {
		Optional<String> str = Optional.of(new String("as"));
		System.out.println(str.orElse("asdf"));
	}

	@Test
	void testBeanUtil() throws Exception {
	}

}
