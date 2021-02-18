package com.zss.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class randomUtil {
    public static List<Integer> getIntRandom(int start,int end,int n){
        if(start>end || (end-start)<n) return null;
        List<Integer> rand = new ArrayList<>();
        int count = 0;
        Random random = new Random();
        while (count < n){
            int num = random.nextInt(end-start)+start;
            boolean flag = true;
            //判断是否出现过重复数据
            for (int i = 0; i < rand.size(); i++) {
                if(num == rand.get(i)){
                    flag = false;
                    break;
                }
            }
            //数据不重复
            if(flag){
                rand.add(num);
                count++;
            }
        }
        return rand;
    }
}
