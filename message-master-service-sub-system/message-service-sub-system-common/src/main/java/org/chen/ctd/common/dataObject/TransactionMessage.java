package org.chen.ctd.common.dataObject;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
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
@Data
@TableName("transaction_message")
public class TransactionMessage implements Serializable {
    @TableId
    private String pid;

    @TableField("version")
    private Integer version;
    @TableField("update_user")
    private String updateUser;
    @TableField("create_user")
    private String createUser;
    @TableField("create_time")
    private LocalDateTime createTime;
    @TableField("update_time")
    private LocalDateTime updateTime;
    @TableField("message_id")
    private String messageId;
    @TableField("message_body")
    private String messageBody;
    @TableField("message_data_type")
    private String messageDataType;
    @TableField("exchange")
    private String exchange;
    @TableField("routing_key")
    private String routingKey;
    @TableField("message_retry_count")
    private Integer messageRetryCount;
    @TableField("areadly_dead")
    private String areadlyDead;
    @TableField("status")
    private Integer status;
    @TableField("status_desc")
    private String statusDesc;
    @TableField("remark")
    private String remark;
    @TableField("first_field")
    private String firstField;
    @TableField("second_field")
    private String secondField;
    @TableField("third_field")
    private String thirdField;
}
