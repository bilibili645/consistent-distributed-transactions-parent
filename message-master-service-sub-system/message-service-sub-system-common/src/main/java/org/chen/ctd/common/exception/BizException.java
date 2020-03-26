package org.chen.ctd.common.exception;

import org.chen.ctd.common.enums.BizEnum;
import org.chen.ctd.common.enums.BizErrorCodeEnum;

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
 * @Date: 2020/3/25 20:46
 * @Description:
 */
public class BizException extends RuntimeException implements Serializable {

    private BizEnum           errorCode;

    private String            errorMessage;

    public BizException() {
        super(BizErrorCodeEnum.OPERATION_FAILED.getDesc());
        this.errorCode = BizErrorCodeEnum.OPERATION_FAILED;
        this.errorMessage = errorCode.getDesc();

    }

    public BizException(BizEnum errorCode) {
        super(errorCode.getDesc());
        this.errorCode = errorCode;
        this.errorMessage = errorCode.getDesc();
    }

    public BizException(BizEnum errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public BizException(BizEnum errorCode, String errorMessage, Throwable exception) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        super.initCause(exception);
    }

    public BizEnum getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorCode(BizEnum errorCode) {
        this.errorCode = errorCode;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public static boolean isBizException(Throwable exception) {

        return exception instanceof BizException;
    }

    public static boolean isErrorException(BizEnum errorCode) {

        boolean error = BizErrorCodeEnum.SYSTEM_ERROR.equals(errorCode)
                || BizErrorCodeEnum.CALLSERVICCE_ERROR.equals(errorCode)
                || BizErrorCodeEnum.URL_REQUEST_ERROR.equals(errorCode)
                || BizErrorCodeEnum.PROCESS_FAIL.equals(errorCode);

        return error;
    }
}
