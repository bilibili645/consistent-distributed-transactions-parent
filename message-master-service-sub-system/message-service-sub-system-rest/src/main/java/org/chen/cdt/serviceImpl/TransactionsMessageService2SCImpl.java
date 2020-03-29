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
import org.chen.ctd.common.utils.MessageCheckUtils;
import org.chen.ctd.mapper.MessageMapper;
import org.chen.ctd.service.TransactionsMessageService;
import org.chen.ctd.service.abstractImpl.TransactionsMessageService2SC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
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
 * @Date: 2020/3/29 15:57
 * @Description: 消息发送一致性的异常流程。
 */
@Slf4j
@Service
public class TransactionsMessageService2SCImpl implements TransactionsMessageService2SC<RestMessage> {
    @Autowired
    private MessageMapper mapper;

    @Autowired
    private TransactionsMessageService2CImpl tmService2C;

    @Override
    public BizBaseResponse getMessageByStateIsTimeOut(RestMessage message) {
        log.info("TransactionsMessageService2CImpl getMessageByStateIsTimeOut param value is :{}", JSONObject.toJSONString(message));
        MessageCheckUtils.checkMessage(message);
        MessageCheckUtils.checkMessageFileds(message.getMessageId());
        //依据status 和 全局id。此时可能会查出其他业务方的消息. 所以我们
        QueryWrapper<TransactionMessage> wrapper = new QueryWrapper<>();
        wrapper.eq("message_id", message.getMessageId())
                .eq("status", MessageStatusEnum.WAITING_CONFIRM.getValue())
                .eq("disabled", 0);
        List<TransactionMessage> tms = mapper.selectList(wrapper);
        log.info("TransactionsMessageService2CImpl getMessageByStateIsTimeOut end return  value is :{}", JSONObject.toJSONString(tms));
        //这里最重要的是返回业务返前置的业务字段。
        return BizBaseResponse.success(tms);
    }

    @Override
    public BizBaseResponse updateConfirmAndSendMessage2MQ(RestMessage message) {
        log.info("TransactionsMessageService2CImpl updateConfirmAndSendMessage2MQ param value is :{}", JSONObject.toJSONString(message));
        MessageCheckUtils.checkMessage(message);
        MessageCheckUtils.checkMessageFileds(message.getId(), message.getMessageId(), message.getRoutingKey());
        //内置messagId and Pid.
        return tmService2C.confirmAndSendMessage2MQ(message);
    }

    @Override
    public BizBaseResponse deleteMessageByMessage(RestMessage message) {
        log.info("TransactionsMessageService2CImpl deleteMessageByMessage param value is :{}", JSONObject.toJSONString(message));
        MessageCheckUtils.checkMessage(message);
        MessageCheckUtils.checkMessageFileds(message.getId(), message.getMessageId());
        TransactionMessage tm = new TransactionMessage();
        tm.setPid(message.getId());
        //物理删除即可。
        tm.setDisabled(1);
        int deleteCount = mapper.updateById(tm);
        log.info("TransactionsMessageService2CImpl deleteMessageByMessage return value is :{}", deleteCount);
        return BizBaseResponse.success(Boolean.TRUE);
    }
}
