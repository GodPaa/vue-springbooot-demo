//package com.lv.mybatis.controller;
//
//
//
//import com.lv.mybatis.exceptions.ParamsException;
//import com.lv.mybatis.response.Response;
//import com.lv.mybatis.response.ResultInfo;
//
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//
///**
// * 定义一个全局处理异常类
// */
////@RestController+@ControllerAdvice=@RestControllerAdvice
//@RestControllerAdvice
//public class GlobalExceptionHandler {
//
//    //处理异常的方法  并确定接收的是哪种类型的异常
//    @ExceptionHandler(Exception.class)
//    public ResultInfo exceptionHandler(Exception e)
//    {
//              // 捕获自定义参数的异常
//              if(e instanceof ParamsException)
//              {
//                  return Response.createFailResp(((ParamsException) e).getMsg());
//              }
//              // 其他异常
//              else {
//                  return Response.createFailResp("其他异常");
//              }
//
//    }
//
//    /*
//    @ExceptionHandler(NullPointerException.class)
//    public ResponseResult exceptionHandler(NullPointerException e) {
//
//    }
//    */
//
//
//
//
//}
