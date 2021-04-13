package com.graduate.towercranewaring.csq.service;

import com.graduate.towercranewaring.csq.dao.DriverDaoImpl;
import com.graduate.towercranewaring.csq.dao.EquipmentDaoImpl;
import com.graduate.towercranewaring.csq.dao.Sjj_workingDaoImpl;
import com.graduate.towercranewaring.csq.pojo.equipment;
import com.graduate.towercranewaring.csq.pojo.sjj_working;
import com.graduate.towercranewaring.csq.pojo.sjj_working_packing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @ClassName: Sjj_workingServiceImpl
 * @Description:
 * @Author:csq
 * @Date 2021/4/13
 * @Version 1.0
 **/
@Service
public class Sjj_workingServiceImpl implements Sjj_workingService{
    @Autowired
    private Sjj_workingDaoImpl sjj_workingDao;
    @Autowired
    private EquipmentDaoImpl equipmentDao;

    @Autowired
    private DriverDaoImpl driverDao;

    @Override
    public List<sjj_working_packing> getAllSjj_Working() {
        List<sjj_working_packing> list=new ArrayList<>();
        List<sjj_working> allSjj_working = sjj_workingDao.getAllSjj_Working();
        for(int i=0;i<allSjj_working.size();i++){
            list.add(new sjj_working_packing(allSjj_working.get(i),
                    equipmentDao.getEquipmentBySn(allSjj_working.get(i).getSn()),
                    driverDao.getDriverById(allSjj_working.get(i).getDriver_id())));
        }
        return list;
    }
}
