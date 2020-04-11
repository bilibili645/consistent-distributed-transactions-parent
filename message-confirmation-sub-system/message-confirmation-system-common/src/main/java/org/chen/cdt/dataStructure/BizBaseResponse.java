package org.chen.cdt.dataStructure;

import lombok.ToString;
import org.chen.cdt.dataStructure.enums.BizEnum;
import org.chen.cdt.dataStructure.enums.BizErrorCodeEnum;

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
 * @Date: 2020/3/28 19:35
 * @Description: copy form luowei1@tuhu.cn(前同事(途虎高级JAVA开发,负责途虎服务治理))
 *  制定标准的接口返回参数，真的是很重要。
 *  可以很快且准确的了解对应接口的执行结果。
 */
@ToString
public class BizBaseResponse<T> implements Serializable {

    private static final long serialVersionUID = 6657433146076866328L;

    private int               code;
    private String            message;
    private T                 data;

    public BizBaseResponse() {
        this(BizErrorCodeEnum.SUCCESS, BizErrorCodeEnum.SUCCESS.getDesc());
    }

    public BizBaseResponse(BizEnum errorCode) {
        this(errorCode, errorCode.getDesc());
    }

    public BizBaseResponse(T data) {
        this(BizErrorCodeEnum.SUCCESS, BizErrorCodeEnum.SUCCESS.getDesc(), data);
    }

    public BizBaseResponse(BizEnum errorCode, String message) {
        this(errorCode, message, null);
    }

    public BizBaseResponse(BizEnum errorCode, String message, T data) {
        this.code = errorCode.getCode();
        this.message = message;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    /**
     * 判断是否成功
     *
     * @return 成功返回<code>true</code>，否则返回<code>false</code>
     */
    public boolean isSuccess() {
        return this.code == BizErrorCodeEnum.SUCCESS.getCode();
    }

    public static BizBaseResponse success() {
        return new BizBaseResponse();
    }

    public static BizBaseResponse success(String message) {
        return new BizBaseResponse(BizErrorCodeEnum.SUCCESS, message);
    }

    public static <T> BizBaseResponse<T> success(T data) {
        return new BizBaseResponse<>(data);
    }

    public static <T> BizBaseResponse<T> success(String message, T data) {
        return new BizBaseResponse(BizErrorCodeEnum.SUCCESS, message, data);
    }

    public static BizBaseResponse operationFailed() {
        return new BizBaseResponse(BizErrorCodeEnum.OPERATION_FAILED);
    }

    public static BizBaseResponse operationFailed(String message) {
        return new BizBaseResponse(BizErrorCodeEnum.OPERATION_FAILED, message);
    }

    public static <T> BizBaseResponse<T> operationFailed(T data) {
        return new BizBaseResponse(BizErrorCodeEnum.OPERATION_FAILED, BizErrorCodeEnum.OPERATION_FAILED.getDesc(),
                data);
    }

    public static <T> BizBaseResponse<T> operationFailed(String message, T data) {
        return new BizBaseResponse(BizErrorCodeEnum.OPERATION_FAILED, message, data);
    }

    public static BizBaseResponse systemError() {
        return new BizBaseResponse(BizErrorCodeEnum.SYSTEM_ERROR);
    }

    public static BizBaseResponse systemError(String message) {
        return new BizBaseResponse(BizErrorCodeEnum.SYSTEM_ERROR, message);
    }

    public static <T> BizBaseResponse<T> systemError(T data) {
        return new BizBaseResponse<>(BizErrorCodeEnum.SYSTEM_ERROR, BizErrorCodeEnum.SYSTEM_ERROR.getDesc(), data);
    }

    public static <T> BizBaseResponse<T> systemError(String message, T data) {
        return new BizBaseResponse(BizErrorCodeEnum.SYSTEM_ERROR, message, data);
    }

    public static BizBaseResponse paramError() {
        return new BizBaseResponse(BizErrorCodeEnum.PARAM_ERROR);
    }

    public static BizBaseResponse paramError(String message) {
        return new BizBaseResponse(BizErrorCodeEnum.PARAM_ERROR, message);
    }

    public static <T> BizBaseResponse<T> paramError(T data) {
        return new BizBaseResponse(BizErrorCodeEnum.PARAM_ERROR, BizErrorCodeEnum.PARAM_ERROR.getDesc(), data);
    }

    public static <T> BizBaseResponse<T> paramError(String message, T data) {
        return new BizBaseResponse(BizErrorCodeEnum.PARAM_ERROR, message, data);
    }

    public static BizBaseResponse paramIsNull() {
        return new BizBaseResponse(BizErrorCodeEnum.PARAM_IS_NULL);
    }

    public static BizBaseResponse paramIsNull(String message) {
        return new BizBaseResponse(BizErrorCodeEnum.PARAM_IS_NULL, message);
    }

    public static <T> BizBaseResponse<T> paramIsNull(T data) {
        return new BizBaseResponse(BizErrorCodeEnum.PARAM_IS_NULL, BizErrorCodeEnum.PARAM_IS_NULL.getDesc(), data);
    }

    public static <T> BizBaseResponse<T> paramIsNull(String message, T data) {
        return new BizBaseResponse(BizErrorCodeEnum.PARAM_IS_NULL, message, data);
    }
}