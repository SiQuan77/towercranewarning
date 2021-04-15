package com.graduate.towercranewaring.csq.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: taji_working_packing
 * @Description:塔机工作记录的包装类
 * @Author:csq
 * @Date 2021/4/14
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class taji_working_packing {
    private taji_working taji_working;
    private equipment equipment;
    private driver driver;
}
