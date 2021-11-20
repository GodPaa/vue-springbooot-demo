package com.lv.mybatis.model;



/**
 * @Author lv
 * @Date 2021/11/11 16:27
 * @Description   登录成功后给AJAX返回用户对象，但 User 有很多字段，而我们只需要其中几个
 *                这种情况我们可以使用一个封装类
 **/

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserModel {

//    private Integer userId;  要加密，不能直接显示 id
    private long userId;

//    private String userIdStr;
    private String userName;

//    private String trueName;

}
