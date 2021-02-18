package com.zss.service;

import com.zss.dao.recordDao;
import com.zss.dao.userDao;
import com.zss.po.Record;
import com.zss.util.MyTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class recordServiceImp implements recordService{

    @Autowired
    private userDao userDao;
    @Autowired
    private recordDao recordDao;

    //添加中奖纪录
    @Override
    public void addRecord(Long userId,Long date,String infomation) {
        recordDao.addRecord(userId,date,infomation);
    }

    //根据用户名查询中奖纪录
    @Override
    @Transactional
    public List<Record> searchRecordByUserName(String userName) {
        Long userId = userDao.searchUserIdByName(userName);
        if(userId == 0) return null;
        List<Record> recordList = recordDao.searchRecordByuserId(userId);
        return recordList;
    }

    ////根据时间范围查询中奖纪录
    @Override
    public List<Record> searchRecordByTime(Long startTime, Long endTime) {
        return recordDao.searchRecordByTime(startTime,endTime);
    }
}
