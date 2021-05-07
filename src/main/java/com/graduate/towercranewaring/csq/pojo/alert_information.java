package com.graduate.towercranewaring.csq.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: alert_information
 * @Description:
 * @Author:csq
 * @Date 2021/4/6
 * @Version 1.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class alert_information {
    private String id;
    private String id_of_working;
    private String type;
//    预警的类型有：
//     塔机的：力矩预警、超重
//     升降机：上限位预警（当当前高度和上限位的差值小于10的时候），倾斜预警、上锁预警、
}
