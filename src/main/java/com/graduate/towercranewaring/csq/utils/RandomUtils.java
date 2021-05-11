package com.graduate.towercranewaring.csq.utils;

/**
 * @ClassName: RandomUtils
 * @Description:
 * @Author:csq
 * @Date 2021/5/11
 * @Version 1.0
 **/
public class RandomUtils {
    public static int ReturnRandomNumber(int start,int end){
        return (int) (Math.random()*(end-start+1)+start);
    }
}
