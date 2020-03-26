package org.chen.ctd.common.constant;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
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
 * @Date: 2020/3/25 21:00
 * @Description:
 */
//@Component      //不加这个注解的话, 使用@Autowired 就不能注入进去了
//@ConfigurationProperties(prefix = "yw")
@Data
public class RabbitMqConstant {
    public static String EXCHANGE_TOPIC_NOTIFICATION = "top.notificationExchage";

    //@Value("${ywRoutingKey1}")
    public static String FIRST_ROUTINGKEY_SENDMQ;
    //@Value("${ywRoutingKey2}")
    public static String SECOND_ROUTINGKEY_SENDMQ;

    @Value("${ywRoutingKey1}")
    public void setKey1(String key1) {
        FIRST_ROUTINGKEY_SENDMQ = key1;
    }

    @Value("${ywRoutingKey2}")
    public void setKey2(String key2) {
        SECOND_ROUTINGKEY_SENDMQ = key2;
    }
//    public static String FIRST_ROUTINGKEY_SENDMQ = "XXXXX";
//    public static String FIRST_ROUTINGKEY_SENDMQ = "XXXXX";
//    public static String FIRST_ROUTINGKEY_SENDMQ = "XXXXX";
}
