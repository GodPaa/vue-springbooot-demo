package com.lv.mybatis.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lv.mybatis.model.UserModel;
import com.lv.mybatis.po.User;
import com.lv.mybatis.service.UserService;
import com.lv.mybatis.response.ResultInfo;
import jdk.nashorn.internal.objects.annotations.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author lv
 * @Date 2021/11/15 19:41
 * @Description
 **/
@RestController
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;


    // 查询所有数据
    @GetMapping("/users")
    public ResultInfo getAllUser(){

        // 实例化 返回对象
        ResultInfo resultInfo = new ResultInfo();

        // 调用service层的登录方法
        List<User> userList = userService.getAllUser();

        // 设置ResultInfo的result的值 （将数据返回用户对象）
        resultInfo.setData(userList);

        return resultInfo;
    }


    //查询一个用户数据
    @GetMapping("/users/{id}")
    public ResultInfo findUserById(@PathVariable("id") int id)
    {
        ResultInfo resultInfo = new ResultInfo();

        User user = userService.getUserById(id);

        List<User> userList = new ArrayList<>();
        userList.add(user);

        resultInfo.setData(userList);

        return resultInfo;
    }

    // post请求,添加数据
    @PostMapping("/users")
    public ResultInfo addUser(User user)
    {
        ResultInfo resultInfo = new ResultInfo();

        userService.addOneUser(user);

        return resultInfo;
    }

    //修改  put请求
    @PutMapping("/users")
    public ResultInfo updateUser(@RequestBody User user)
    {
        ResultInfo resultInfo = new ResultInfo();
        userService.updateUserById(user);
        return resultInfo;
    }


    //删除   delete请求
    @DeleteMapping("/users/{id}")
    public ResultInfo deleteUserById(@PathVariable("id") long id)
    {
        ResultInfo resultInfo = new ResultInfo();
        userService.deleteUserById(id);
        return  resultInfo;
    }

    // 登录请求
    @PostMapping("/login")
    public ResultInfo userLogin(@RequestBody User user){

        System.out.println(user.toString());
        ResultInfo resultInfo = new ResultInfo();

        // 调用service层的登录方法
        UserModel userModel = userService.userLogin(user.getUsername(),user.getPassword());

        // 设置ResultInfo的result的值 （将数据返回用户对象）
        resultInfo.setData(userModel);

        return resultInfo;
    }



    // 分页显示所有Users
    @GetMapping("/usersPage")
    public ResultInfo findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize)
    {
      ResultInfo resultInfo = new ResultInfo();

        Page<User> page = new Page<>(pageNum ,pageSize);

        IPage<User> userIPage =  userService.selectPage(page);

        resultInfo.setData(userIPage);

      return resultInfo;
    }
}
