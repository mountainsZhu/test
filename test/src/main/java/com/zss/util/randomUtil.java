package com.zss.util;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class randomUtil {
    public static List<Integer> getIntRandom(int start, int end, int n) { //todo n ---> maxSize
        if (start > end || (end - start) < n) return null;  //todo 同理
        List<Integer> rand = new ArrayList<>();
        int count = 0;
        Random random = new Random();
        while (count < n) {
            int num = random.nextInt(end - start) + start;  //todo 一般在循环中定义的变量起名以 loopXxx
            //判断是否出现过重复数据
//            for (int i = 0; i < rand.size(); i++) {
//                if (num == rand.get(i)) {
//                    flag = false;
//                    break;
//                }
//            }
            //数据不重复           //todo List 的常用api  org.springframework.util.CollectionUtils 类的常用api
            if (!rand.contains(num)) {
                rand.add(num);
                count++;
            }
        }
        return rand;
    }
}
