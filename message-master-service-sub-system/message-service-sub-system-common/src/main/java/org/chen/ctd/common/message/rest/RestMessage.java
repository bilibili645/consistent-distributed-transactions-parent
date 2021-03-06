package org.chen.ctd.common.message.rest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.chen.ctd.common.message.BaseNetworkMessage;

import java.io.Serializable;

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
 * @Date: 2020/3/25 19:51
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestMessage extends BaseNetworkMessage implements Serializable {
    private String messageId;

    private String messageBody;

    private String messageDataType;

    private String routingKey;

    private Integer messageSendTime;

    private String areadlyDead;

    private String firstField;

    private String secondField;

    private String thirdField;
}
