package com.graduate.towercranewaring.csq.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: alert_information_packing
 * @Description:
 * @Author:csq
 * @Date 2021/4/15
 * @Version 1.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class alert_information_packing {
    private alert_information alert_information;
    private driver driver;
    private equipment equipment;
}
