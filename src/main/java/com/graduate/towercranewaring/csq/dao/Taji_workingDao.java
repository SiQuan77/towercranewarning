package com.graduate.towercranewaring.csq.dao;

import com.graduate.towercranewaring.csq.pojo.taji_working;

import java.util.HashMap;
import java.util.List;

public interface Taji_workingDao {
    List<taji_working> getAllTajiworking();
    HashMap<taji_working,String> getAlertTaji_working();
    String judgeIfAlert(taji_working taji_working);
    taji_working getTaji_workingById(String id);
    boolean updateTaji_working(taji_working taji_working);
    boolean insertTaji_working(taji_working taji_working);
}
