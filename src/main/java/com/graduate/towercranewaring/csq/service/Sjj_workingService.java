package com.graduate.towercranewaring.csq.service;


import com.graduate.towercranewaring.csq.pojo.sjj_working;
import com.graduate.towercranewaring.csq.pojo.sjj_working_packing;


import java.util.List;

public interface Sjj_workingService {
    List<sjj_working_packing> getAllSjj_Working();
    boolean insertSjj_working(sjj_working sjj_working);
    sjj_working insertSjj_working_Automatically(String sn);
}
