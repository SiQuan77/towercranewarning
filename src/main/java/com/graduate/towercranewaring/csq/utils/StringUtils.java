package com.graduate.towercranewaring.csq.utils;

/**
 * @ClassName: StringUtils
 * @Description:
 * @Author:csq
 * @Date 2021/4/9
 * @Version 1.0
 **/
public class StringUtils {
//    返回被分隔符分割后的最后一串字符串
    public static String RetrunSplitString(String to_split,String split_char){
        String[] temp =to_split.split(split_char);
        return temp[temp.length-1];
    }
}
