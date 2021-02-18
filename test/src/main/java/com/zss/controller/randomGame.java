package com.zss.controller;

import com.zss.NormalClass.ShowRecord;
import com.zss.po.Record;
import com.zss.po.User;
import com.zss.service.userService;
import com.zss.service.recordService;
import com.zss.util.MyTimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 *  1. 需要查看每次抽奖中奖和没中奖的信息
 *  2. RandomGameController中其实可以不用写太复杂的业务，业务交由对应的 RandomGameService 来实现，Controller负责调度Service和组织返回给前段的对象结构
 *  3. 要熟悉 jdk 自带的一些 api
 *  4. 使用 restful 风格的接口定义
 *  5. 尝试使用迭代器来进行循环数据的处理，看着要舒服一点性能差不多
 */

@Controller
public class randomGame {  //todo 类名首字母大写,建议在类名中携带其功能信息 如 XxxUtil 或者 RandomGameController
    @Autowired
    private userService userService;
    @Autowired
    private recordService recordService;
    //todo private static final Logger LOGGER = LoggerFactory.getLogger(randomGame.class);


    //随机抽奖   //todo 使用方法注
    @RequestMapping("playGame")       //todo RequestMapping --->  PostMapping
    @ResponseBody                     //todo  @ResponseBody 移除
    public Map<String,String> playGame(){
        //抽奖
        List<User> gameInfo = userService.playRandomGame();   //todo gameInfo ---> xxxUserList
        Map<String,String> map = new ConcurrentHashMap<>();   //todo 初始化map时指定一个大小，尽量给一个方法内的变量起一个好名字
        for (int i = 0; i < gameInfo.size(); i++) {           //todo 对你拿到的数据集做空值判断不然会空指针
            User user = gameInfo.get(i);
            //存储抽奖结果 //todo 可以在抽象一个方法，用于后面扩展
            switch (i){
                case 0:map.put("一等奖",user.getName());
                        recordService.addRecord(user.getId(),System.currentTimeMillis(),"一等奖");  //todo 一般禁止在循环中操作数据库
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
    @RequestMapping("searchRecordByUserName/{name}") //todo RequestMapping ---> GetMapping
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
        System.out.println(records);  //todo System.out.println  ---> LOGGER.info

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
