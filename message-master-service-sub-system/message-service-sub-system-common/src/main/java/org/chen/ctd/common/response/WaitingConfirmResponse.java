package org.chen.ctd.common.response;

import lombok.Data;
import lombok.ToString;

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
 * @Date: 2020/3/28 19:53
 * @Description: 消息服务子系统内对于第一阶段的响应值。
 */
@Data
public class WaitingConfirmResponse implements Serializable {

    /**
     *  对于不同的实现,可能数据库id选择也是不一样。
     *  同理得messageId也是不一样。
     *  为什么要返回pkid + messId;
     *  因为确保这条消息唯一阿兄弟。
     *
     *  至于这条消息得状态应不应该返回过去。
     *  1. 消息服务子系统根本不关心此时得状态。
     *  2. 调用方也不关心。
     *  3. 只要确保此时预发送已成功即可。
     *  4. 返回PKID和messId只是为了后面正向流程得第二部能够因此去修改其状态。
     */


    private Serializable pkid;

    private Serializable messageId;
}
