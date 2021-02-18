package com.zss.service;

import com.zss.dao.userDao;
import com.zss.po.User;
import com.zss.util.randomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class userSeviceImp implements userService{

//    public static void main(String[] args) {
//        List<Integer> rand =  randomUtil.getIntRandom(0,5,3);
//        for (int i = 0; i < rand.size(); i++) {
//            System.out.println(rand.get(i));
//        }
//    }

    @Autowired
    private userDao userDao;

    //随机抽取3个奖项
    @Override
    public List<User> playRandomGame() {
        List<User> userList = userDao.searchAllUsers();
        if(userList.size()<3) return null;  //todo 一般来所不能省去单if的大括号
        int userAmount = userList.size();
        List<User> winUserList = new ArrayList<>();
        //产生对应数量的随机数
        List<Integer> randList =  randomUtil.getIntRandom(0,userAmount,3);
        for (int i = 0; i < randList.size(); i++) {  //todo 同理 对数据集进行空值判断 ，尝试用迭代器来进行循环数据处理
            int index = randList.get(i);
            winUserList.add(userList.get(index));
        }
        return winUserList;
    }

    //根据userid查询name
    @Override
    public String searchNameByUserId(Long userId) {
        return userDao.searchNameByUserId(userId);
    }


}
