package org.chen.cdt.rest.config;

import lombok.Data;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

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
 * @Date: 2020/4/1 22:07
 * @Description:
 */
@Data
@ConfigurationProperties(prefix = "config.http.client")
public class HttpClientProperties {

    private int lingerTime = 30;

    private int connectTimeout = 10 * 1000;

    //这个参数有意义吗??????
    /**
     * client -> nginx -> tomcat(NioEndpoint) -> eureka server ->
     * eureka client -> tomcat(Wrapper) -> Servlet.
     */
    private int readTimeout = 40 * 1000;

    /**
     * 允许HttpClient可建立的最大连接数。
     */
    private int maxConnTotal = 2000;

    /**
     * 对同一个ip+port的最大活跃连接保持数。
     */
    private int maxConnPerRoute = 10;

    private int maxIdleTime = 10;

    /**
     * 关闭缓冲,小包即时发送。
     * TCP_Nagel算法。当开启后，会将小包积攒然后发送。
     * 这里true代表不开启。所以即时发送。实时性增大，
     * 但是吞吐量减少。
     * Netty中的这个参数可以直接提高QPS。
     */
    private boolean tcpNoDelay = true;

    private long keepAliveDuration = 6 * 1000L;
}