package com.zss.service;

import com.zss.po.Record;

import java.util.List;

public interface recordService {
    //添加中奖纪录
    void addRecord(Long userId,Long date,String information);
    //根据用户名查询中奖纪录
    List<Record> searchRecordByUserName(String userName);
    //根据时间范围查询中奖纪录
    List<Record> searchRecordByTime(Long startTime,Long endTime);
}
