package org.chen.cdt.rest.dataObject;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

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
 * @Date: 2020/3/25 21:47
 * @Description: Message表。作为全局事务控制的基石。
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Data
public class TransactionMessage implements Serializable {
    private Integer pid;
    private String messageId;

    private Integer version;
    private String updateUser;
    private String createUser;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private String messageBody;
    private String messageDataType;
    private String routingKey;
    private Integer messageRetryCount;
    private Integer areadlyDead;
    private Integer status;
    private String statusDesc;
    private String remark;
    private String firstField;
    private String secondField;
    private String thirdField;
    private Integer disabled;
}
