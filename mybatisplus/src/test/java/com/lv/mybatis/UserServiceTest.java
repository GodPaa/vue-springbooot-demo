package com.lv.mybatis;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lv.mybatis.po.User;
import com.lv.mybatis.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
class UserServiceTest {


    @Autowired
    private UserService userService;

    @Autowired
    private User user;


    @Test
    void getAllUser() {
        System.out.println("通过xml自定义的查询方法：");
        List<User> userList = userService.getAllUser();
        for(User user : userList) {
            System.out.println(user);
        }
    }


    @Test
    void getAllUserPlus() {
        List<User> userList = userService.getAllUserPlus();
        for(User user : userList) {
            System.out.println(user);
        }
    }

    @Test
    // 增
    void addOneUser() {
        int ifAdd = userService.addOneUser(user);
        System.out.println(ifAdd);
        if (ifAdd == 1){
            System.out.println("添加成功");
        }
    }

    @Test
    // 查
    void getUserById() {
        User user = userService.getUserById(1);
        System.out.println(user.toString());
    }

    @Test
    // 改
    void updateUserById() {
        user.setId(1);
        user.setUsername("吕璟源");
        user.setPassword("20191002882");
        userService.updateUserById(user);
        User user = userService.getUserById(1);
        System.out.println("修改成功！ \r\n" + user.toString());
    }

    @Test
    // 删
    void deleteUserById() {
        int ifDelete = userService.deleteUserById(6);
        if (ifDelete == 1){
            System.out.println("删除");
        }
    }

    @Test
    void selectPage(){
        Page<User> page = new Page<>(1 ,5);
        userService.selectPage(page);
        page.getRecords().forEach(System.out::println);
    }




}