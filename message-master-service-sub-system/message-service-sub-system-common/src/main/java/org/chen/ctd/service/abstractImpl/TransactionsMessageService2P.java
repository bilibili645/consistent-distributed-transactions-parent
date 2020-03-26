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
 * @Date: 2020/3/25 00:34
 * @Description: P为业务消费域。对于此域，仅提供删除功能。
 * 只有业务消费域成功收获P端传递的消费成功信息,才会对消息服务子系统进行删除操作。
 * 并对对应MQ信息做ack答应机制。
 */
public abstract class TransactionsMessageService2P<T> implements TransactionsMessageService {
    /**
     * 标志分布式事务流程的完成.完成MQ及消息服务子系统的ACK机制。
     * @param message
     */
    public abstract void deleteMessageByAck(T message);
}
