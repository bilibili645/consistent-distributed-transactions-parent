package org.chen.cdt.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.chen.cdt.service.TestService4Async;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

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
 * @Date: 2020/3/30 23:54
 * @Description:
 */
@Slf4j
@Service
public class TestService4AsyncImpl implements TestService4Async {
    //已经替换掉默认的SimpleAsyncExecutor线程池。
    @Async
    @Override
    public void testAsync() {
        log.info("currentThreadName is : {}", Thread.currentThread().getName());
    }
}
