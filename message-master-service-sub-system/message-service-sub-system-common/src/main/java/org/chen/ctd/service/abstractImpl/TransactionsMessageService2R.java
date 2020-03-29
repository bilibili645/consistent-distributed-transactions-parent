package org.chen.ctd.service.abstractImpl;

import org.chen.ctd.common.enums.BizBaseResponse;
import org.chen.ctd.service.TransactionsMessageService;

import java.util.List;

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
 * @Date: 2020/3/25 00:06
 * @Description: 对消息恢复子系统提供的接口。
 * P端接收消息冲正和异常处理流程。
 */
public interface TransactionsMessageService2R<T> extends TransactionsMessageService {
    /**
     * 1. 消息恢复子系统会去查询消息服务子系统已经发送成功，
     * 但是没有被确认消费成功的消息。。
     */
    BizBaseResponse getSendSuccessfullyNotConsumeMessage(T message);

    BizBaseResponse<List> getSendSuccessfullyNotConsumeMessages();
    /**
     * 2.1 被消息恢复子系统重新发送消息。
     */
    void reSendMessage(T message);
    /**
     * 2.1 被消息恢复子系统重新发送消息。
     */
    void reSendMessageByList(List<T> message);
    /**
     * 2.2 直接发送消息(简单但不重要的业务.(比如商户通知之类的东西.))。
     *      check 后直接发送给MQ中间件即可。
     */
    void directSendMessage(T message);

    /**
     * 3. 经过消息恢复子系统重试多次的消息仍未被消费则被标记为死亡对象。
     */
    BizBaseResponse markObjectDied(T message);
}
