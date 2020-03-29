package org.chen.ctd.common.enums;

import org.omg.CORBA.PUBLIC_MEMBER;

import java.util.HashMap;

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
 * @Date: 2020/3/28 19:13
 * @Description: 消息状态枚举值。
 * 1-2 代表的主动方正向流程走完。
 * 3-4 标志这被动方的正向流程走完。
 */
public enum MessageStatusEnum {
    //待确认,调用方第一次预发存储信息。
    WAITING_CONFIRM(1, "待确认"),
    //消息已进存储，且投递了。
    SENDING(2, "发送中"),
    //消费方已成功
    SENDED(3, "发送完成"),
    //消费完成。
    SUCCESS(4, "消费方处理成功");

    private String enumDesc;
    private Integer enumValue;

    private static HashMap<Integer, String> MAP = new HashMap();
    static{
        for(MessageStatusEnum type : MessageStatusEnum.values()){
            MAP.put(type.enumValue, type.enumDesc);
        }
    }
    MessageStatusEnum(int enumValue, String enumDesc ) {
        this.enumDesc = enumDesc;
        this.enumValue = enumValue;
    }
    public static String getDescByValue(int value) {
        return MAP.get(value);
    }

    public int getValue() {
        return enumValue;
    }

    public String getDesc() {
        return enumDesc;
    }
}
