package com.zss.controller;

import com.zss.NormalClass.ShowRecord;
import com.zss.po.Record;
import com.zss.po.User;
import com.zss.service.userService;
import com.zss.service.recordService;
import com.zss.util.MyTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Controller
public class randomGame {

    @Autowired
    private userService userService;
    @Autowired
    private recordService recordService;


    //随机抽奖
    @RequestMapping("playGame")
    @ResponseBody
    public Map<String,String> playGame(){
        //抽奖
        List<User> gameInfo = userService.playRandomGame();
        Map<String,String> map = new ConcurrentHashMap<>();
        for (int i = 0; i < gameInfo.size(); i++) {
            User user = gameInfo.get(i);
            //存储抽奖结果
            switch (i){
                case 0:map.put("一等奖",user.getName());
                        recordService.addRecord(user.getId(),System.currentTimeMillis(),"一等奖");
                        break;
                case 1:map.put("二等奖",user.getName());
                        recordService.addRecord(user.getId(),System.currentTimeMillis(),"二等奖");
                        break;
                case 2:map.put("三等奖",user.getName());
                        recordService.addRecord(user.getId(),System.currentTimeMillis(),"三等奖");
                        break;
                default:break;
            }
        }
        return map;
    }

    //查询获奖信息
    @RequestMapping("searchRecordByUserName/{name}")
    @ResponseBody
    public List<ShowRecord> searchRecordByUserName(@PathVariable("name") String name){
        List<Record> records = recordService.searchRecordByUserName(name);
        List<ShowRecord> list = new ArrayList<>();
        for (int i = 0; i < records.size(); i++) {
            ShowRecord showRecord = new ShowRecord();
            Record record = records.get(i);
            showRecord.setName(name);
            showRecord.setDateStr(MyTimeUtil.TimeToString(record.getDate()));
            showRecord.setInfomation(record.getInformation());
            list.add(showRecord);
        }
        return list;
    }

    //根据时间范围查询获奖信息endTime
    @RequestMapping("searchRecordByTime/{startTime}/{endTime}")
    @ResponseBody
    //实际情况前端传回的就是一个字符串的时间
    public List<ShowRecord> searchRecordByTime(@PathVariable("startTime") String startTime,@PathVariable("endTime") String endTime){
        //需要string转换为long的时间
        Long start = MyTimeUtil.StringToTime(startTime);
        Long end = MyTimeUtil.StringToTime(endTime);
        List<Record> records = recordService.searchRecordByTime(start,end);
        System.out.println(records);

        List<ShowRecord> list = new ArrayList<>();
        for (int i = 0; i < records.size(); i++) {
            ShowRecord showRecord = new ShowRecord();
            Record record = records.get(i);
            String name = userService.searchNameByUserId(record.getUserId());
            showRecord.setName(name);
            showRecord.setDateStr(MyTimeUtil.TimeToString(record.getDate()));
            showRecord.setInfomation(record.getInformation());
            list.add(showRecord);
        }
        return list;
    }
}
