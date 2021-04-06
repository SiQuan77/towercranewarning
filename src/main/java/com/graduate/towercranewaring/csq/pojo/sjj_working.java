package com.graduate.towercranewaring.csq.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: sjj_working
 * @Description:
 * @Author:csq
 * @Date 2021/4/6
 * @Version 1.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class sjj_working {
    private String id;
    private String sn;
    private int top_limit;
    private int low_limit;
    private int tilt_range;
    private String lock_condition;
    private int height;
    private String driver_id;
}
