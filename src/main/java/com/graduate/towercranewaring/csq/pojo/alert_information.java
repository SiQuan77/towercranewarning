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
    private String sn;
    private String project_name;
    private String starting_time;
    private String ending_time;
    private String type;
}
