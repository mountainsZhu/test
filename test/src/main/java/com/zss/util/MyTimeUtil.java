package com.zss.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyTimeUtil {
    public static String TimeToString(Long times){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String timeStr = sdf.format(new Date(times));
        return timeStr;
    }
    public static Long StringToTime(String timeStr){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date time = new Date();
        try {
            time = sdf.parse(timeStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return time.getTime();
    }
}
