package com.graduate.towercranewaring.csq.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: equipment
 * @Description:
 * @Author:csq
 * @Date 2021/4/6
 * @Version 1.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class equipment {
    private String sn;
    private String name;
    private String xinxihao;
    private String type;
    private String property_contractor;
    private String property_contractor_id;
    private String installation_contractor;
    private String installing_time;
    private String dismantle_contractor;
    private String dismantle_time;

}
