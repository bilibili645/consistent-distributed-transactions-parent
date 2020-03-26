package org.chen.cdt.rabbitMq;

import org.chen.ctd.common.constant.RabbitMqConstant;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

/**
 * MIT License
 * <p>
 * Copyright (c) 2019 chenmudu (陈晨)
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * @Author chenchen6
 * @Date: 2020/3/25 21:04
 * @Description: rabbit mq 配置。
 */
@Configuration
public class RabbitMqConfiguration {

    @Bean("topicNotificationExchage")
    public Exchange topicNotificationExchage() {
        HashMap<String, Object> arguments = new HashMap<>();
        arguments.put("x-delayed-type", "topic");
        TopicExchange exchange = new TopicExchange(RabbitMqConstant.EXCHANGE_TOPIC_NOTIFICATION, true, false, arguments);
        return exchange;
    }

    //@Bean
    public Queue sendMqtoXXX() {
        return new Queue(RabbitMqConstant.FIRST_ROUTINGKEY_SENDMQ);
    }

    public Queue sendMqtoXXX2() {
        return new Queue(RabbitMqConstant.SECOND_ROUTINGKEY_SENDMQ);
    }

    //@Bean
    Binding bindingSentMqTaskQueue() {
        return BindingBuilder.
                    bind(sendMqtoXXX()).
                    to(topicNotificationExchage()).
                    with(sendMqtoXXX().getName()).
                    noargs();
    }
}
