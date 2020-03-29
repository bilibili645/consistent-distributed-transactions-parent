package org.chen.ctd.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

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
 * @Date: 2020/3/28 21:00
 * @Description:
 */
@Slf4j
@Component
public class RabbitMqUtils<T> {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${yewu.exchange.topic}")
    private String exchange;

    public void sendMqToConsumer(String routingKey, T message) {
        log.info("RabbitMqUtils sendMqToConsumer param rsult routing key value is:{},{}",routingKey, message);
        rabbitTemplate.convertAndSend(exchange, routingKey, message);
    }
}
