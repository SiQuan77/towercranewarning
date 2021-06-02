package com.graduate.towercranewaring.csq.dao;

import com.graduate.towercranewaring.csq.pojo.sjj_working;

import java.util.HashMap;
import java.util.List;

public interface Sjj_workingDao {
    List<sjj_working> getAllSjj_Working();
    HashMap<sjj_working,String> getAlertSjj_working();
    String judgeIfAlert(sjj_working sjj_working);
    sjj_working getSjj_workingById(String id);
    boolean updateSjj_working(sjj_working sjj_working);
    boolean insertSjj_working(sjj_working sjj_working);
    boolean deleteSjj_working(sjj_working sjj_working);
    List<sjj_working> getWorkBySn(String sn);
}
