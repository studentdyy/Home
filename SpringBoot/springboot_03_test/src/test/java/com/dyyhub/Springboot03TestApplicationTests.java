package com.dyyhub;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

//加载测试类专用属性，以及优先级顺序
@SpringBootTest(properties = "test.prop=test.value2" , args = "--test.prop=testValue3")
class Springboot03TestApplicationTests {

	@Value("${test.prop}")
	String value;

	@Test
	void contextLoads() {
		System.out.println(value);
	}

}
