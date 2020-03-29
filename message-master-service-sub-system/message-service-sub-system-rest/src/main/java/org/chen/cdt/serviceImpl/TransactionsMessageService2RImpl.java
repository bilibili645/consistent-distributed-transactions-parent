package org.chen.cdt.serviceImpl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.chen.ctd.common.dataObject.TransactionMessage;
import org.chen.ctd.common.enums.BizBaseResponse;
import org.chen.ctd.common.enums.MessageStatusEnum;
import org.chen.ctd.common.message.rest.RestMessage;
import org.chen.ctd.common.utils.MessageCheckUtils;
import org.chen.ctd.common.utils.RabbitMqUtils;
import org.chen.ctd.mapper.MessageMapper;
import org.chen.ctd.service.abstractImpl.TransactionsMessageService2R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

import static org.chen.ctd.common.enums.MessageStatusEnum.SENDED;

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
 * @Date: 2020/3/29 19:22
 * @Description:  消息接收的异常处理。
 */
@Slf4j
@Service
public class TransactionsMessageService2RImpl implements TransactionsMessageService2R<RestMessage> {

    @Autowired
    private MessageMapper mapper;

    @Autowired
    private RabbitMqUtils<String> rabbitMqUtils;


    @Override
    public BizBaseResponse getSendSuccessfullyNotConsumeMessage(RestMessage message) {
        log.info("TransactionsMessageService2CImpl getSendSuccessfullyNotConsumeMessage param value is :{}", JSONObject.toJSONString(message));
        MessageCheckUtils.checkMessage(message);
        MessageCheckUtils.checkMessageFileds(message.getId(),
                message.getMessageId(),
                message.getRoutingKey());
        QueryWrapper<TransactionMessage> wrapper = new QueryWrapper();
        wrapper.eq("disabled", 0)
                .eq("status", MessageStatusEnum.SENDED.getValue())
                .eq("pid", message.getId())
                .eq("message_id", message.getMessageId())
                .or()
                .eq("status", MessageStatusEnum.SENDING.getValue());
        TransactionMessage tm = mapper.selectOne(wrapper);
        log.info("TransactionsMessageService2CImpl getSendSuccessfullyNotConsumeMessage result value is :{}", JSONObject.toJSONString(tm));
        return BizBaseResponse.success(tm);
    }

    @Override
    public BizBaseResponse<List> getSendSuccessfullyNotConsumeMessages() {
        QueryWrapper<TransactionMessage> wrapper = new QueryWrapper();
        wrapper.eq("disabled", 0)
                .eq("status", SENDED.getValue());
        List<TransactionMessage> tms = mapper.selectList(wrapper);
        log.info("TransactionsMessageService2CImpl getSendSuccessfullyNotConsumeMessages result value is :{}", JSONObject.toJSONString(tms));
        return BizBaseResponse.success(tms);
    }

    /**
     * 当消息服务子系统投递成功，但是业务端消费一直失败或者未标记
     * 此消息已经被消费成功时，消息恢复子系统则
     * 仍然定时去拉取状态为: 投递成功但未被消费的消息。
     *
     * @param message
     * @return
     */
    @Override
    public void reSendMessage(RestMessage message) {
        log.info("TransactionsMessageService2CImpl reSendMessage param value is :{}", JSONObject.toJSONString(message));
        // check some field for message.
        MessageCheckUtils.checkMessage(message);
        MessageCheckUtils.checkMessageFileds(message.getId(),
                                            message.getMessageId(),
                                            message.getRoutingKey());
        //reSend message
        rabbitMqUtils.sendMqToConsumer(message.getRoutingKey(), message.getMessageBody());
        log.info("TransactionsMessageService2CImpl reSendMessage end!");
    }

    /**
     * 或者在消息管理界面对死亡队列的消息也是要重发的。
     * @param messages
     */
    @Override
    public void reSendMessageByList(List<RestMessage> messages) {
        if(CollectionUtils.isEmpty(messages)) {
            messages.stream().forEach(m -> {
                //用stream很不合适.一旦发生检测异常流就会被中断.
                //优雅的使用stream github上有对应的工具..
                reSendMessage(m);
            });
        }
    }

    @Override
    public void directSendMessage(RestMessage message) {
        reSendMessage(message);
    }

    @Override
    public BizBaseResponse markObjectDied(RestMessage message) {
        log.info("TransactionsMessageService2CImpl markObjectDied param value is :{}", JSONObject.toJSONString(message));
        MessageCheckUtils.checkMessage(message);
        MessageCheckUtils.checkMessageFileds(message.getId(),
                message.getMessageId());
        TransactionMessage ms = new TransactionMessage();
        ms.setAreadlyDead(1);
        ms.setPid(message.getId());
        int updateCount = mapper.updateById(ms);
        log.info("TransactionsMessageService2CImpl markObjectDied result value is :{}", updateCount);
        return BizBaseResponse.success(updateCount);
    }
}
