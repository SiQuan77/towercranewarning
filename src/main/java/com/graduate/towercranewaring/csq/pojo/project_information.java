package com.graduate.towercranewaring.csq.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: project_information
 * @Description:
 * @Author:csq
 * @Date 2021/5/7
 * @Version 1.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class project_information {
    String project_id;
    String name;
    String location;
    String construction_unit;
    String start_time;
    String end_time;
}
