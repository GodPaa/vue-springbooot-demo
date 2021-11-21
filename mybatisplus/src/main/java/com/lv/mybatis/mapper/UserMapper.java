package com.lv.mybatis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lv.mybatis.po.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.sql.Wrapper;
import java.util.List;
//@Mapper
@Repository
public interface UserMapper extends BaseMapper<User> {
    /**
     *  BaseMapper 里封装了一些常用的 CRUD
     *  直接继承 BaseMapper 可以省去xml的编写
     */


    /**
     * 当然也可以通过 xml 自定义 sql 查询语句
     */
    // 该方法使用User实体类
    public List<User> getAllUser();

    // 通过用户名查询用户记录，返回用户对象
    public User queryUserByName(String userName);


    // 获取所有User，并分页
    IPage<User> selectPage(Page<User> page);
}
