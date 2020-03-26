package org.chen.ctd.service.abstractImpl;

import org.chen.ctd.service.TransactionsMessageService;

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
 * @Date: 2020/3/24 23:58
 * @Description: 对state confirm 消息状态确认子系统提供的服务。
 *  即：一致性消息发送过程的冲正和异常处理流程。   state confirm.
 */
public abstract class TransactionsMessageService2SC<T> implements TransactionsMessageService {
    /**
     * 1. 查询状态为'确认超时'的消息。
     * @param message
     * @return
     */
    public abstract T getMessageByStateIsTimeOut(T message);
    /**
     * 确认和投递消息给下游MQ
     * @param message
     * @return
     */
    public abstract T updateConfirmAndSendMessage2MQ(T message);
    /**
     * 提供删除消息的服务。
     * @param message
     * @return
     */
    public abstract Integer deleteMessageByMessage(T message);
}
