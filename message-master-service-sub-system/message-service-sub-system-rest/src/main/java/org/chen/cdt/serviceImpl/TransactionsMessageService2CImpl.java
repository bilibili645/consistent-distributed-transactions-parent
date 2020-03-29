package org.chen.cdt.serviceImpl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.chen.ctd.common.dataObject.TransactionMessage;
import org.chen.ctd.common.enums.BizBaseResponse;
import org.chen.ctd.common.enums.BizErrorCodeEnum;
import org.chen.ctd.common.enums.MessageStatusEnum;
import org.chen.ctd.common.exception.BizException;
import org.chen.ctd.common.message.rest.RestMessage;
import org.chen.ctd.common.response.WaitingConfirmResponse;
import org.chen.ctd.common.utils.MessageCheckUtils;
import org.chen.ctd.common.utils.RabbitMqUtils;
import org.chen.ctd.mapper.MessageMapper;
import org.chen.ctd.service.abstractImpl.TransactionsMessageService2C;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
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
@Slf4j
@Service
public class TransactionsMessageService2CImpl implements TransactionsMessageService2C<RestMessage> {

    @Autowired
    private MessageMapper mapper;

    @Autowired
    private RabbitMqUtils<String> rabbitMqUtils;

    //需要规定第一次传入的参数。
    // 接收方可能会调用多次。产生多条消息。
    // 所以确定某一个需要messageId 和 pkid。
    @Override
    public BizBaseResponse saveAndConfirmWaitingMessage(RestMessage message) {
        log.info("TransactionsMessageService2CImpl saveAndConfirmWaitingMessage param value is :{}", JSONObject.toJSONString(message));
        MessageCheckUtils.checkMessage(message);
        MessageCheckUtils.checkMessageFileds(message.getMessageId());
        TransactionMessage tm = constructFirstMessageByCurrentMessage(message);
        mapper.insert(tm);
        if(Objects.nonNull(tm.getPid()) && tm.getPid() > 0) {
            WaitingConfirmResponse resp = new WaitingConfirmResponse();
            resp.setPkid(tm.getPid());
            resp.setMessageId(tm.getMessageId());
            return BizBaseResponse.success(resp);
        }
        return BizBaseResponse.operationFailed();
    }
    //需要规定第二次传入的参数。
    @Override
    public BizBaseResponse confirmAndSendMessage2MQ(RestMessage message) {
        log.info("TransactionsMessageService2CImpl confirmAndSendMessage2MQ param value is :{}", JSONObject.toJSONString(message));
        MessageCheckUtils.checkMessage(message);
        MessageCheckUtils.checkMessageFileds(message.getId(), message.getMessageId(), message.getRoutingKey());
        QueryWrapper<TransactionMessage> wrapper = new QueryWrapper();
        wrapper.eq("pid", message.getId())
                .eq("message_id", message.getMessageId())
                .eq("disabled", 0);
        TransactionMessage tm = mapper.selectOne(wrapper);
        log.info("TransactionsMessageService2CImpl confirmAndSendMessage2MQ select result value is :{}", JSONObject.toJSONString(tm));
        if(Objects.isNull(tm) || tm.getPid() < 1 ) {
            BizBaseResponse.operationFailed();
        }
        tm.setRoutingKey(message.getRoutingKey());
        tm.setMessageBody(message.getMessageBody());
        //先发送消息。是否投递到对应exchange, queue.
        //Q的内容需要再次进行考虑. 不能只传递一个mess body。需要添加其他属性。
        rabbitMqUtils.sendMqToConsumer(tm.getRoutingKey(), tm.getMessageBody());
        //修改其状态。  ----> Sending! not Sended。
        tm.setStatus(MessageStatusEnum.SENDING.getValue());
        tm.setStatusDesc(MessageStatusEnum.SENDING.getDesc());
        mapper.updateById(tm);

        //我们无法强行保证消息一定会被消费。但是尽最大可能将消息扔给Q。
        //一旦扔成功我们会利用日志链路吧当前的ack打印出来。
        //此时如果消费端ACK后。
        //消息业务消费端会去调用此服务的接口去修改这条消息的状态值.
        //异常情况是 消息恢复子系统重新投放消息。所以被动方需要接口幂等。
        //当被动方重试一定次数后就需要消息管理子系统介入.进行调度。
        WaitingConfirmResponse resp = new WaitingConfirmResponse();
        resp.setPkid(tm.getPid());
        resp.setMessageId(tm.getMessageId());
        log.info("TransactionsMessageService2CImpl confirmAndSendMessage2MQ end and return value result is:{}", JSONObject.toJSONString(resp));
        return BizBaseResponse.success(resp);
    }



    @Override
    public BizBaseResponse saveAndSendMessage2MQ(RestMessage message) {
        log.info("TransactionsMessageService2CImpl saveAndSendMessage2MQ param value is :{}", JSONObject.toJSONString(message));
        MessageCheckUtils.checkMessage(message);
        MessageCheckUtils.checkMessageFileds(message.getMessageId(), message.getRoutingKey());
        TransactionMessage tm = constructFirstMessageByCurrentMessage(message);
        tm.setStatus(MessageStatusEnum.SENDING.getValue());
        tm.setStatusDesc(MessageStatusEnum.SENDING.getDesc());
        mapper.insert(tm);
        return BizBaseResponse.success();
    }

    private TransactionMessage constructFirstMessageByCurrentMessage(RestMessage restMessage) {
        TransactionMessage message = TransactionMessage.builder()
                                        .messageId(restMessage.getMessageId())
                                        .createTime(restMessage.getCreateTime())
                                        .createUser(restMessage.getCreateUserName())
                                        .updateUser(restMessage.getUpdateUserName())
                                        .messageBody(restMessage.getMessageBody())
                                        .messageDataType(restMessage.getMessageDataType())
                                        .routingKey(restMessage.getRoutingKey())
                                        .messageRetryCount(0)
                                        .areadlyDead(0)
                                        .status(MessageStatusEnum.WAITING_CONFIRM.getValue())
                                        .statusDesc(MessageStatusEnum.WAITING_CONFIRM.getDesc())
                                        .remark(restMessage.getRemark())
                                        .firstField(restMessage.getFirstField())
                                        .secondField(restMessage.getSecondField())
                                        .thirdField(restMessage.getThirdField())
                                        .build();
        return message;
    }
}
