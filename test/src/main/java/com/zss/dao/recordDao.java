package com.zss.dao;

import com.zss.po.Record;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Component
@Mapper
public interface recordDao {
    //记录中奖信息
    void addRecord(@Param("userId") Long userId, @Param("date") Long date, @Param("information") String information);
    //根据用户id查询中奖纪录
    List<Record> searchRecordByuserId(Long userId);
    //根据时间范围查询中奖纪录
    List<Record> searchRecordByTime(@Param("startTime") Long startTime,@Param("endTime") Long endTime);
}
