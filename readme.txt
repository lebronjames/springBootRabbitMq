我们通过在Spring Boot应用中整合RabbitMQ，
并实现一个简单的发送、接收消息的例子来对RabbitMQ有一个直观的感受和理解。

在Spring Boot中整合RabbitMQ是一件非常容易的事，因为之前我们已经介绍过Starter POMs，
其中的AMQP模块就可以很好的支持RabbitMQ，下面我们就来详细说说整合过程：

1)新建一个Spring Boot工程，命名为：“rabbitmq-hello”。

2)在pom.xml中引入如下依赖内容，其中spring-boot-starter-amqp用于支持RabbitMQ。

3)在application.properties中配置关于RabbitMQ的连接和用户信息，
用户可以回到上面的安装内容，在管理页面中创建用户。
spring.application.name=rabbitmq-hello
spring.rabbitmq.host=localhost
spring.rabbitmq.port=5672
spring.rabbitmq.username=spring
spring.rabbitmq.password=123456

4)创建消息生产者Sender。通过注入AmqpTemplate接口的实例来实现消息的发送，
AmqpTemplate接口定义了一套针对AMQP协议的基础操作。在Spring Boot中会根据配置来注入其具体实现。
在该生产者，我们会产生一个字符串，并发送到名为hello的队列中。
@Component
public class Sender {
    @Autowired
    private AmqpTemplate rabbitTemplate;
    public void send() {
        String context = "hello " + new Date();
        System.out.println("Sender : " + context);
        this.rabbitTemplate.convertAndSend("hello", context);
    }
}

5)创建消息消费者Receiver。通过@RabbitListener注解定义该类对hello队列的监听，
并用@RabbitHandler注解来指定对消息的处理方法。所以，该消费者实现了对hello队列的消费，
消费操作为输出消息的字符串内容。
@Component
@RabbitListener(queues = "hello")
public class Receiver {
    @RabbitHandler
    public void process(String hello) {
        System.out.println("Receiver : " + hello);
    }
}

6)创建RabbitMQ的配置类RabbitConfig，用来配置队列、交换器、路由等高级信息。
这里我们以入门为主，先以最小化的配置来定义，以完成一个基本的生产和消费过程。
@Configuration
public class RabbitConfig {
    @Bean
    public Queue helloQueue() {
        return new Queue("hello");
    }
}

7)创建应用主类：
@SpringBootApplication
public class HelloApplication {
    public static void main(String[] args) {
        SpringApplication.run(HelloApplication.class, args);
    }
}

8)创建单元测试类，用来调用消息生产：
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = HelloApplication.class)
public class HelloApplicationTests {
    @Autowired
    private Sender sender;
    @Test
    public void hello() throws Exception {
        sender.send();
    }
}

