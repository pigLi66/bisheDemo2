package com.lsl.demo;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.LambdaUtils;
import com.baomidou.mybatisplus.core.toolkit.SerializationUtils;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.lsl.demo.common.base.entity.BaseEntity;
import com.lsl.demo.model.sys.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import top.lslllxq.utils.print.Print;

import javax.swing.text.html.parser.Entity;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootTest
@SuppressWarnings("serial")
class AdminApplicationTests {

	@Test
	public final void testFunctional() {
		Set<Integer> set = new TreeSet<>((Integer a, Integer b) -> b.compareTo(a));
	}

	@Test
	public void test() {
	}

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
	    Map<UserEntity, Object> map = new TreeMap<>(Comparator.comparingInt(UserEntity::hashCode));
	    UserEntity userEntity = new UserEntity();
	    int last=-1;
	    while (last < map.size()) {
	        last = map.size();
	        userEntity.setName("lsl" + Math.random());
	        map.put(userEntity, null);
	        Print.print(map.size());
        }
	    Print.print(userEntity);
	}

}
