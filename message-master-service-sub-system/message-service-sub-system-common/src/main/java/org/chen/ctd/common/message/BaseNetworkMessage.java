package org.chen.ctd.common.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.chen.ctd.common.utils.StringUtils;

import java.io.Serializable;
import java.time.LocalDate;

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
 * @Date: 2020/3/25 19:25
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseNetworkMessage implements Serializable {
    private String id = StringUtils.get32UUID();

    private Integer version = 0;

    private Integer status;

    private String  statusDes;

    private String createUserName;

    private LocalDate createTime;

    private String updateUserName;

    private LocalDate updateTime;

    private String remark;
}
