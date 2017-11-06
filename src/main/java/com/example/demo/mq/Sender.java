package com.example.demo.mq;

import java.util.Date;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 消息生产者Sender
* 
* 项目名称:  springBootRabbitMq
* 包:       com.example.demo.mq   
* 类名称:    Sender.java
* 类描述:    
* 创建人:    yzx 
* 创建时间:  2017年11月2日
 */
@Component
public class Sender {

	@Autowired
	private AmqpTemplate rabbitTemplate;
	
	public void send() {
		String msg = "rabbitMq " + new Date();
		System.out.println("Sender:"+msg);
		//会产生一个字符串，并发送到名为hello的队列中
		rabbitTemplate.convertAndSend("hello", msg);
	}
	
}
