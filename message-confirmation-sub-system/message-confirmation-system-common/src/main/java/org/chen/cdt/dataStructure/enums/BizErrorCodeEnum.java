package org.chen.cdt.dataStructure.enums;

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
 * @Date: 2020/3/25 20:49
 * @Description:
 */
public enum BizErrorCodeEnum implements BizEnum {

    SUCCESS(10000, "success", "操作成功"),
    URL_REQUEST_ERROR(10001, "url.request.error", "异常接口调用"),
    PROCESS_FAIL(10002, "PROCESS_FAIL", "服务器处理失败"),
    TOO_MANY_REQUEST(10003, "TOO_MANY_REQUEST", "访问过于频繁"),
    PERMISSION_DENY(10004, "PERMISSION_DENY", "用户服务无权限"),
    AUTHENTICATION_EXPIRED(10005, "AUTHENTICATION_EXPIRED", "身份认证过期"),
    SUCCESS_EXIST(10006, "SUCCESS_EXIST", "记录已存在"),
    SUCCESS_ACOUNT_NOT_EXIST(10007, "SUCCESS_ACOUNT_NOT_EXIST", "账号不存在"),
    IP_LIMIT(10008, "IP_LIMIT", "IP限制"),
    PARAM_ERROR(10009, "PARAM_ERROR", "参数错误"),
    PARAM_IS_NULL(10010, "PARAM_IS_NULL", "参数为空"),
    INVALID_METHOD(10012, "INVALID_METHOD", "方法名不存在"),
    REQUEST_API_NOT_FOUND(10013, "REQUEST_API_NOT_FOUND", "请求的方法名不存在"),
    INVALID_FORMAT(10014, "INVALID_FORMAT", "无效的数据格式"),
    USER_DOES_NOT_EXISTS(10018, "USER_DOES_NOT_EXISTS", "用户不存在"),
    CONTENT_IS_NULL(10019, "CONTENT_IS_NULL", "内容为空"),
    CONTENT_IS_ILLEGAL(10020, "CONTENT_IS_ILLEGAL ", "包含非法内容"),
    AUTH_FAILD(10021, "AUTH_FAILD", "验证失败"),
    ILLEGAL_OPERATION(10022, "ILLEGAL_OPERATION", "非法操作"),
    USERNAME_OR_PASSWORD_ERROR(10023, "USERNAME_OR_PASSWORD_ERROR", "用户名或者密码错误"),
    OPERATION_FAILED(10024, "OPERATION_FAILED", "操作失败"),
    SYSTEM_ERROR(10025, "SYSTEM_ERROR", "系统异常"),
    CALLSERVICCE_ERROR(10026, "CALLSERVICCE_ERROR", "调用服务异常"),
    VERSION_ERROR(10027, "VERSION_ERROR", "版本号错误"),
    SUCCESS_NOT_EXIST(10028, "SUCCESS_NOT_EXIST", "数据不存在"),
    REPEAT_OPERATION(10029, "REPEAT_OPERATION", "重复操作"),
    FIELD_VALUE_NOT_EXSIT(10030, "FIELD_VALUE_NOT_EXSIT", "指定字段未携带."),

    NO_NEED_EXECUTE(11000, "NO_NEED_EXECUTE", "不需要执行");

    private Integer code;

    private String name;

    private String desc;

    BizErrorCodeEnum(Integer code, String name, String desc) {
        this.code = code;
        this.name = name;
        this.desc = desc;
    }

    @Override
    public int getCode() {
        return this.code;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getDesc() {
        return this.desc;
    }
}
