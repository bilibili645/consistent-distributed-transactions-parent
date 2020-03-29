package org.chen.cdt.rabbitMq;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitMqConfig {

    @Bean
    public Exchange topicNotifycationExchange() {
        return new TopicExchange("top.notificationExchange");
    }

    /**
     * durable(持久化重启时不需要新建队列),
     * exclusive(消息队列是否只在当前Connection生效),
     * autoDelete(消息队列没有在使用时不删除)
     * @return
     */
    @Bean
    public Queue sendMqForCTDTest() {
        return  new Queue("yewu.routing.key1");
    }
    /**
     * 绑定队列到交换机上并指定RoutingKey
     * @return
     */
    @Bean
    public Binding bindingFirstQueueToExchangeWithRoutingKey() {
        return BindingBuilder.bind(sendMqForCTDTest()).to(topicNotifycationExchange()).with(sendMqForCTDTest().getName()).noargs();
    }

}
