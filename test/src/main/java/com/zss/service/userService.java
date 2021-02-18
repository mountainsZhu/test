package com.zss.service;

import com.zss.po.User;

import java.util.List;

public interface userService {
    //随机选出一二三等奖
    List<User> playRandomGame();
    //根据userid查询name
    String searchNameByUserId(Long userId);
}
