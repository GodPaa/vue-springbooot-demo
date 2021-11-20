package com.lv.mybatis.response;

/**
 *  封装状态码
 */
public enum StatusCode {

    //http定义好的状态码
    SUCCESS(200),

    SERVER_ERROR(500),

    URL_NOT_FOUND(404),

    //自定义  状态码
    NOT_ALLOWRD_REG(1001);

    public int code;

    StatusCode(int code)
    {
        this.code=code;
    }

}
