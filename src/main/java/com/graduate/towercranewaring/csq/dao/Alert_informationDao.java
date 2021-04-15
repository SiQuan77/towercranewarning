package com.graduate.towercranewaring.csq.dao;

import com.graduate.towercranewaring.csq.pojo.alert_information;
import com.graduate.towercranewaring.csq.pojo.alert_information_packing;

import java.util.List;

/**
 * @ClassName: Alert_informationDao
 * @Description:
 * @Author:csq
 * @Date 2021/4/15
 * @Version 1.0
 **/
public interface Alert_informationDao {
    List<alert_information> getAllAlertInformation();
    Boolean InsertAlertInformation(alert_information alert_information);
}
