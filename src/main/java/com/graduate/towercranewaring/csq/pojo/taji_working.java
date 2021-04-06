package com.graduate.towercranewaring.csq.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: taji_working
 * @Description:
 * @Author:csq
 * @Date 2021/4/6
 * @Version 1.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class taji_working {
    private String id;
    private String sn;
    private String starting_time;
    private String ending_time;
    private int maximum_payload;
    private int height;
    private int load;
    private int range;
    private int moment;
    private String driver_id;
}
