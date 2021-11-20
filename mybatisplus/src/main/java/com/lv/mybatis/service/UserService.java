package com.lv.mybatis.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lv.mybatis.mapper.UserMapper;
import com.lv.mybatis.model.UserModel;
import com.lv.mybatis.po.User;
import com.lv.mybatis.utils.AssertUtil;
import com.lv.mybatis.utils.Md5Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author lv
 * @Date 2021/11/8 16:36
 * @Description
 **/

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    // xml 自己写的方法
    // 查找所有用户
    public List<User> getAllUser(){
        return userMapper.getAllUser();
    };

    // 使用 BaseMapper 的方法
    // 查找所有用户
    public List<User> getAllUserPlus(){
        return userMapper.selectList(null);
    }

    // 增
    public int addOneUser(User user){
        return userMapper.insert(user);
    }
    // 删
    public int deleteUserById(long id){
        return userMapper.deleteById(id);
    }
    // 查
    public User getUserById(int id){
        return userMapper.selectById(id);
    }

    // 改
    public int updateUserById(User user){
        return userMapper.updateById(user);
    }


    // 分页查询
    public IPage<User> selectPage(Page<User> page) {
        // 不进行 count sql 优化，解决 MP 无法自动优化 SQL 问题，这时候你需要自己查询 count 部分
        // page.setOptimizeCountSql(false);
        // 当 total 为小于 0 或者设置 setSearchCount(false) 分页插件不会进行 count 查询
        // 要点!! 分页返回的对象与传入的对象是同一个
        return userMapper.selectPage(page);
    }






    /**
     Service层 （业务逻辑层：非空判断、条件判断等业务逻辑处理）
     1. 参数判断，判断用户姓名、用户密码非空弄
     如果参数为空，抛出异常（异常被控制层捕获并处理）
     2. 调用数据访问层，通过用户名查询用户记录，返回用户对象
     3. 判断用户对象是否为空
     如果对象为空，抛出异常（异常被控制层捕获并处理）
     4. 判断密码是否正确，比较客户端传递的用户密码与数据库中查询的用户对象中的用户密码
     如果密码不相等，抛出异常（异常被控制层捕获并处理）
     5. 如果密码正确，登录成功

     * @param userName
     * @param userPwd
     */
    public UserModel userLogin(String userName, String userPwd){
        /* 1. 参数判断，判断用户姓名、用户密码非空弄 */
        checkLoginParams(userName,userPwd);

        /* 2. 调用数据访问层，通过用户名查询用户记录，返回用户对象 */
        User user = userMapper.queryUserByName(userName);

        /* 3. 判断用户对象是否为空 */
        AssertUtil.isTrue(user == null,"用户姓名不存在");

        /* 4. 判断密码是否正确，比较客户端传递的用户密码与数据库中查询的用户对象中的用户密码 */
        checkUserPwd(userPwd,user.getPassword());

        /* 5. 返回构建用户对象 */
        UserModel userModel = new UserModel();
        userModel.setUserName(user.getUsername());
        userModel.setUserId(user.getId());
        return userModel;
//        return buildUserInfo(user);
    }

    /**
     * 参数校验
     * @param userName
     * @param userPwd
     */
    private void checkLoginParams(String userName, String userPwd) {
        // 验证用户姓名
        AssertUtil.isTrue(StringUtils.isBlank(userName),"用户姓名不能为空！");
        // 验证用户密码
        AssertUtil.isTrue(StringUtils.isBlank(userPwd),"用户密码不能为空！");
    }

    /**
     * 密码判断
     *    先将客户端传递的密码加密， 再与数据库中查询到的密码作比较
     * @param userPwd
     * @param userPwd1
     */
    private void checkUserPwd(String userPwd, String userPwd1) {
        // 将客户端传递的密码加密
//        userPwd = Md5Util.encode(userPwd);
        // 判断密码是否相等
        AssertUtil.isTrue(!userPwd.equals(userPwd1),"用户密码不正确");
    }




    /**
     修改密码的参数校验
     判断原始密码是否为空
     判断原始密码是否正确（查询的用户对象中的用户密码是否原始密码一致）
     判断新密码是否为空
     判断新密码是否与原始密码一致 （不允许新密码与原始密码）
     判断确认密码是否为空
     判断确认密码是否与新密码一致

     * @param user
     * @param oldPwd
     * @param newPwd
     * @param repeatPwd
     */
    private void checkPasswordParams(User user, String oldPwd, String newPwd, String repeatPwd) {

        /* 判断原始密码是否为空 */
        AssertUtil.isTrue(StringUtils.isBlank(oldPwd),"原始密码为空！");
        /* 判断原始密码是否正确（查询的用户对象中的用户密码是否原始密码一致） */
        AssertUtil.isTrue(!user.getPassword().equals(Md5Util.encode(oldPwd)),"原始密码不正确！");

        /* 判断新密码是否为空 */
        AssertUtil.isTrue(StringUtils.isBlank(newPwd),"新密码不能为空！");
        /* 判断新密码是否与原始密码一致 （不允许新密码与原始密码） */
        AssertUtil.isTrue(oldPwd.equals(newPwd),"新密码不能与原始密码一致！");

        /* 判断确认密码是否为空 */
        AssertUtil.isTrue(StringUtils.isBlank(repeatPwd),"确认密码为空！");
        /* 判断确认密码是否与新密码一致 */
        AssertUtil.isTrue(!repeatPwd.equals(newPwd),"新密码与确认密码不一致！");

    }

/*
    */
/**
     * 构建需要返回给客户端的用户对象
     * @param user
     *//*

    private UserModel buildUserInfo(User user) {
        UserModel userModel = new UserModel();
//        userModel.setUserId(user.getId());
        // 设置加密过的用户ID
        userModel.setUserIdStr(UserIDBase64.encoderUserID(user.getId()));
        System.out.println(UserIDBase64.encoderUserID(user.getId()));
        userModel.setUserName(user.getUserName());
        userModel.setTrueName(user.getTrueName());
        return userModel;
    }
*/





}
