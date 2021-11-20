package com.lv.mybatis.response;

/**
 * 定义不同情景下，响应体各项返回的具体值
 *       code ： xxx
 *       msg  ： xxx
 *       data : xxx
 */
public class Response {

    // msg
    private static String SUCCESS="success";
    private static String FAIL="fail";

    // code
    // msg相对应的code去 statueCode 里面拿



    /************************   成功执行   ************************/
    // 返回null
    public static <T> ResultInfo<T> createOkResp()
    {
           return new ResultInfo<T>(StatusCode.SUCCESS.code,SUCCESS,null);
    }
    // 返回data
    public static <T> ResultInfo<T> createOkResp(T data)
    {
        return new ResultInfo<T>(StatusCode.SUCCESS.code,SUCCESS,data);
    }
    // 返回 msg
    public static <T> ResultInfo<T> createOkResp(String msg)
    {
        return new ResultInfo<T>(StatusCode.SUCCESS.code,msg,null);
    }
    // 返回 msg + data
    public static <T> ResultInfo<T> createOkResp(String msg, T data)
    {
        return new ResultInfo<T>(StatusCode.SUCCESS.code,msg,data);
    }





    /************************   失败执行   ************************/

    public static <T> ResultInfo<T> createFailResp()
    {
        return new ResultInfo<T>(StatusCode.SERVER_ERROR.code,FAIL,null);
    }
    //失败执行
    public static <T> ResultInfo<T> createFailResp(String msg)
    {
        return new ResultInfo<T>(500,msg,null);
    }
    //失败执行
    public static <T> ResultInfo<T> createFailResp(int code, String msg)
    {
        return new ResultInfo<T>(code,msg,null);
    }

    public static <T> ResultInfo<T> createResp(int code, String msg, T data)
    {
        return new ResultInfo<T>(code,msg,data);
    }



}
