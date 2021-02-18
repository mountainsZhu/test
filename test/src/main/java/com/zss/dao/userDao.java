package com.zss.dao;

import com.zss.po.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface userDao {
    //查询所有的用户
    List<User> searchAllUsers();
    //根据name查询userid
    Long searchUserIdByName(String name);
    //根据userid查询name
    String searchNameByUserId(Long userId);
}
