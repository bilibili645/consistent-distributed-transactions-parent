package org.chen.cdt.serviceImpl;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.chen.ctd.common.dataObject.TransactionMessage;
import org.chen.ctd.common.enums.BizBaseResponse;
import org.chen.ctd.common.enums.MessageStatusEnum;
import org.chen.ctd.common.message.rest.RestMessage;
import org.chen.ctd.common.utils.MessageCheckUtils;
import org.chen.ctd.mapper.MessageMapper;
import org.chen.ctd.service.abstractImpl.TransactionsMessageService2P;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
 * @Date: 2020/3/29 20:34
 * @Description: 提供给业务方，当业务方确认消费后便去删除此消息。
 */

@Slf4j
@Service
public class TransactionsMessageService2PImpl implements TransactionsMessageService2P<RestMessage> {

    @Autowired
    private MessageMapper mapper;

    @Override
    public BizBaseResponse deleteMessageByAck(RestMessage message) {
        log.info("TransactionsMessageService2CImpl deleteMessageByAck param value is :{}", JSONObject.toJSONString(message));
        MessageCheckUtils.checkMessage(message);
        MessageCheckUtils.checkMessageFileds(message.getMessageId());
        TransactionMessage ms = new TransactionMessage();
        ms.setStatus(MessageStatusEnum.SUCCESS.getValue());
        ms.setStatusDesc(MessageStatusEnum.SUCCESS.getDesc());
        ms.setPid(message.getId());
        ms.setPid(message.getId());
        ms.setDisabled(1);
        int updateCount = mapper.updateById(ms);
        log.info("TransactionsMessageService2CImpl deleteMessageByAck param value is :{}", updateCount);
        return BizBaseResponse.success(updateCount);
    }
}
