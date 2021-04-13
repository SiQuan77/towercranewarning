package com.graduate.towercranewaring.csq.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: sjj_working_packing
 * @Description:
 * @Author:csq
 * @Date 2021/4/13
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class sjj_working_packing {
    private sjj_working sjj_working;
    private equipment equipment;
    private driver driver;
}
