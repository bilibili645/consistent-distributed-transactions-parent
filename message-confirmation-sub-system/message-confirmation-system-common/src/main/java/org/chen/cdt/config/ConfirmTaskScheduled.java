package org.chen.cdt.config;

import lombok.extern.slf4j.Slf4j;
import org.chen.cdt.service.TestService4Async;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

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
 * @Date: 2020/3/30 23:15
 * @Description:
 */
@Slf4j
@Component
@Configurable
public class ConfirmTaskScheduled {

    @Autowired
    private TestService4Async service4Async;

    //每5分钟执行一次。
    private final String cronStr = "0/10 * * * * ? ";//"*/5 * * * *";

    @Scheduled(cron = cronStr)
    public void executeAsyncTask() {
        log.info("execute task started! & current thread name is :{}", Thread.currentThread().getName());
        service4Async.testAsync();
        log.info("execute task end!");
    }
}
