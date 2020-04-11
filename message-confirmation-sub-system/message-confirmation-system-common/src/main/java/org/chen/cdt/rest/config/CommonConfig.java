package org.chen.cdt.rest.config;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.SocketConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HttpContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

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
 * @Date: 2020/4/1 22:05
 * @Description:
 */
@Configuration
@EnableConfigurationProperties({HttpClientProperties.class})
public class CommonConfig {

    private final String httpClientName = "configHttpClient";

    @Autowired
    private HttpClientProperties properties;

    @Bean(name = httpClientName)
    @ConditionalOnMissingBean(name = httpClientName)
    @ConditionalOnExpression("${config.http.client.enable:true}")
    public CloseableHttpClient initializationHttpClient() {
        //请求相关配置
        RequestConfig reqConfig = getRequestConfig();
        //网络相关配置
        SocketConfig socketConfig = getSocketConfig();
        //依据配置做构造。
        return getHttpClientByConfig(reqConfig, socketConfig);
    }

    private RequestConfig getRequestConfig() {
        RequestConfig reqConfig = RequestConfig.custom()
                                        .setConnectTimeout(properties.getConnectTimeout())
                                        .setConnectionRequestTimeout(properties.getConnectTimeout())
                                        .setSocketTimeout(properties.getReadTimeout())
                                        .build();
        return reqConfig;
    }

    private SocketConfig getSocketConfig() {
        SocketConfig socketConfig = SocketConfig.custom()
                                        .setSoLinger(properties.getLingerTime())
                                        .setTcpNoDelay(properties.isTcpNoDelay())
                                        .build();
        return socketConfig;
    }

    private CloseableHttpClient getHttpClientByConfig(RequestConfig requestConfig, SocketConfig socketConfig) {
        CloseableHttpClient httpClient = HttpClients.custom()
                                            .setMaxConnPerRoute(properties.getMaxConnPerRoute())
                                            .setMaxConnTotal(properties.getMaxConnTotal())
                                            .setDefaultRequestConfig(requestConfig)
                                            .setDefaultSocketConfig(socketConfig)
                                            .setKeepAliveStrategy(new CustomizationConnectionKeepAliveStrategy())
                                            .build();
        return httpClient;
    }
    
    
    private  class CustomizationConnectionKeepAliveStrategy extends DefaultConnectionKeepAliveStrategy {

        private long keepAlive = 6000;

        //private String userName = "chen";

        @Override
        public long getKeepAliveDuration(HttpResponse response, HttpContext context) {
            //context.getAttribute(HttpClientContext.HTTP_TARGET_HOST)
            return -1 == super.getKeepAliveDuration(response, context) ? keepAlive : super.getKeepAliveDuration(response, context);
        }
    }


    /**
     * 自定义重试策略。
     */
    private class CustomizationHttpRetry extends DefaultHttpRequestRetryHandler {
        //指定重试策略。
        @Override
        public boolean retryRequest(IOException exception, int executionCount, HttpContext context) {
            boolean retry = super.retryRequest(exception, executionCount, context);
            if(true) {

            }
            return retry;
        }
    }
}
