package org.chen.cdt.serviceImpl;

import org.chen.ctd.common.constant.RabbitMqConstant;
import org.chen.ctd.common.dataObject.TransactionMessage;
import org.chen.ctd.common.enums.BizErrorCodeEnum;
import org.chen.ctd.common.exception.BizException;
import org.chen.ctd.common.message.rest.RestMessage;
import org.chen.ctd.mapper.MessageMapper;
import org.chen.ctd.service.abstractImpl.TransactionsMessageService2C;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
 * @Date: 2020/3/25 19:17
 * @Description: 对服务主动方提供的Rest接口。
 * 1. 服务主动方预发送消息给消息子服务。
 */
@Service
public class TransactionsMessageService2CImpl extends TransactionsMessageService2C<RestMessage> {

    @Autowired
    private MessageMapper mapper;

    @Override
    public Integer saveAndConfirmWaitingMessage(RestMessage message) {
        //checkMessage(message);
        System.out.println(RabbitMqConstant.EXCHANGE_TOPIC_NOTIFICATION);
        System.out.println(RabbitMqConstant.FIRST_ROUTINGKEY_SENDMQ);
        System.out.println(RabbitMqConstant.SECOND_ROUTINGKEY_SENDMQ);
        System.out.println(mapper.selectById("1"));
        return null;
    }

    @Override
    public void confirmAndSendMessage2MQ(RestMessage message) {
        checkMessage(message);
        //RestMessage messageByMessageId = getMessageByMessageId(message.getMessageId());
        //checkMessage(messageByMessageId);
    }

    @Override
    public Integer saveAndSendMessage2MQ(RestMessage message) {
        checkMessage(message);
        return null;
    }

    private void checkMessage(RestMessage message) {
        if(Objects.isNull(message)) {
            throw new BizException(BizErrorCodeEnum.PARAM_IS_NULL);
        }
    }
}
