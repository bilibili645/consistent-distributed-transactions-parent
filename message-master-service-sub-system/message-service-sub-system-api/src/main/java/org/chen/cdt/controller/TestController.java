package org.chen.cdt.controller;

import org.chen.cdt.serviceImpl.TransactionsMessageService2CImpl;
import org.chen.ctd.common.enums.BizBaseResponse;
import org.chen.ctd.common.message.rest.RestMessage;
import org.chen.ctd.service.TransactionsMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

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
 * @Date: 2020/3/24 00:30
 * @Description:
 */
@RestController
@RequestMapping("/testController")
public class TestController {
    @Autowired
    private TransactionsMessageService2CImpl transactionsMessageService;

    @GetMapping("/testInfo")
    public BizBaseResponse testInfo() {
        RestMessage message = new RestMessage();
        message.setCreateTime(LocalDateTime.now());
        message.setMessageId("12345676");
        message.setRoutingKey("yewu.routing.key1");
        message.setId(2);
        message.setMessageBody("json for test.");
        return transactionsMessageService.confirmAndSendMessage2MQ(message);
    }
}
