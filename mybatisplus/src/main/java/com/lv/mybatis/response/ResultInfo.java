package com.lv.mybatis.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 定义响应结果统一格式
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultInfo<T> {

    private int code = 200;

    private String msg = "success";

    private  T  data;

}
