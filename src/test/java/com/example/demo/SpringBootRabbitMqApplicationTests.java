package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.demo.mq.Sender;

@RunWith(SpringJUnit4ClassRunner.class) // SpringJUnit支持，由此引入Spring-Test框架支持！ 
@SpringBootTest(classes = SpringBootRabbitMqApplication.class) // 指定我们SpringBoot工程的Application启动类
public class SpringBootRabbitMqApplicationTests {

	@Autowired
	private Sender sender;
	
	@Test
	public void hello() throws Exception {
		sender.send();
	}

}
