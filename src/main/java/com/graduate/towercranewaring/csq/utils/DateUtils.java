package com.graduate.towercranewaring.csq.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName: DateUtils
 * @Description:
 * @Author:csq
 * @Date 2021/5/11
 * @Version 1.0
 **/
public class DateUtils {
    public static String getTime(){
        SimpleDateFormat sdf = new SimpleDateFormat();//格式化时间
        sdf.applyPattern("yyyy/MM/dd/HH:mm:ss");
        return sdf.format(new Date());
    }
    public static String getTime2(){
        Date date = new Date();
        long time = 10*1000;//60秒
        Date afterDate = new Date(date.getTime() + time);//10秒后的时间
        SimpleDateFormat sdf=new SimpleDateFormat();
        sdf.applyPattern("yyyy/MM/dd/HH:mm:ss");
        return sdf.format(afterDate);
    }


}
