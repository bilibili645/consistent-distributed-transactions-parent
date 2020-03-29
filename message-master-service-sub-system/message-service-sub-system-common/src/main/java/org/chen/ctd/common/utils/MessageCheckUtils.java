package org.chen.ctd.common.utils;

import org.chen.ctd.common.enums.BizErrorCodeEnum;
import org.chen.ctd.common.exception.BizException;
import org.chen.ctd.common.message.rest.RestMessage;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

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
 * @Date: 2020/3/29 16:23
 * @Description:
 */

@Component
public class MessageCheckUtils {
    public static void checkMessage(RestMessage message) {
        if(Objects.isNull(message)) {
            throw new BizException(BizErrorCodeEnum.PARAM_IS_NULL);
        }
    }

    public static void checkMessageFileds(Serializable... fieldValue) {
        for (Serializable field : fieldValue) {
            if(Objects.isNull(field)) {
                //break;
                throw new BizException(BizErrorCodeEnum.FIELD_VALUE_NOT_EXSIT);
            }
        }
    }
}
