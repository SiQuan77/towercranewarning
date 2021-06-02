package com.graduate.towercranewaring.csq.service;

import com.graduate.towercranewaring.csq.pojo.taji_working;
import com.graduate.towercranewaring.csq.pojo.taji_working_packing;

import java.util.List;

public interface Taji_workingService {
    List<taji_working_packing> getAllTaji_working();
    taji_working_packing insert_new_working_automatically(String sn);
    boolean insertTaji_working(taji_working taji_working);
    boolean deleteEquipBySn(String sn);
}
