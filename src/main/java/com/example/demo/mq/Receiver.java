package com.example.demo.mq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 消息消费者Receiver
 * 消费者实现了对hello队列的消费，消费操作为输出消息的字符串内容
* 
* 项目名称:  springBootRabbitMq
* 包:       com.example.demo.mq   
* 类名称:    Receiver.java
* 类描述:    
* 创建人:    yzx 
* 创建时间:  2017年11月2日
 */
@Component
@RabbitListener(queues="hello")//@RabbitListener注解定义该类对hello队列的监听
public class Receiver {

	@RabbitHandler//@RabbitHandler注解来指定对消息的处理方法
	public void process(String hello) {
		
		System.out.println("Receiver:"+hello);
	}
}
