package org.chen.ctd.common;

import org.chen.ctd.service.TransactionsMessageService;

import java.util.Objects;

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
 * @Date: 2020/3/24 22:43
 * @Description: 消息服务确认子系统对C(Consumer)端的响应。
 * 一致性消息发送方的正向流程。
 */
public abstract class TransactionsMessageService2C implements TransactionsMessageService {

    /**
     * 接收Consumer端的预发送消息，回存，并保存在消息服务子系统内置DB内。
     * @param message
     * @return
     */
    abstract Integer saveAndConfirmWaitingMessage(Object message);

    /**
     * 主动方(C)
     * 2.1 C端接收到1.saveAndConfirmWaitingMessage存储成功的消息后执行C端自身的业务流程。
     * 2.2 然后调用此接口,通过此接口查询1中存储在DB内的消息并设置其状态后进行投放下游MQ。
     * @param messageId
     */
    abstract void confirmAndSendMessage2MQ(Object messageId);


    /**
     * 业务需求：存储并发送此消息。直接C端调用。(透传)
     * @param message
     * @return
     */
    abstract Integer saveAndSendMessage2MQ(Object message);
}
