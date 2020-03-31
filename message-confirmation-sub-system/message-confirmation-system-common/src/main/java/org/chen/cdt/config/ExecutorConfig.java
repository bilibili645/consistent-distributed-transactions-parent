package org.chen.cdt.config;

import org.chen.cdt.customer.executor.CustomizableThreadPoolExecutor;
import org.chen.cdt.customer.queue.CustomizableTaskQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

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
 * @Date: 2020/3/30 23:40
 * @Description:
 */

@Configuration
@EnableAsync
public class ExecutorConfig {

    @Autowired
    private TaskExecutorConfig config;

    private final String executorName = "taskExecutor";

    /**
     * 替换原生线程池。每次来一个任务都是new一个Thread。
     * 这就不叫做线程池。
     * @return
     */
    @Bean({executorName})
    //@Primary
    public Executor taskExecutor() {
        return buildIOThreadPool();
    }

    private Executor buildIOThreadPool() {
        System.out.println(config);
        final CustomizableTaskQueue taskQueue = new CustomizableTaskQueue(config.getQueueSize());
        //如果想定制名称. OK。请在startInitialize之前设置前缀即可。
        CustomizableThreadPoolExecutor.SET_THREAD_PREFIX_NAME("chen");
        CustomizableThreadPoolExecutor executor = CustomizableThreadPoolExecutor.startInitializeCustomThreadPoolExecutor(true,
                config.getCorePoolSize(),
                config.getMaxPoolSize(), config.getKeepAliveTime(),
                config.getTimeUnit(), taskQueue,
                null, new ThreadPoolExecutor.CallerRunsPolicy(), null);
        return executor;
    }
}
