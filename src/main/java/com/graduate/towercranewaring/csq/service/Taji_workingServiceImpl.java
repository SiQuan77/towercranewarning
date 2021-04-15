package com.graduate.towercranewaring.csq.service;

import com.graduate.towercranewaring.csq.dao.DriverDaoImpl;
import com.graduate.towercranewaring.csq.dao.EquipmentDaoImpl;
import com.graduate.towercranewaring.csq.dao.Taji_workingDaoImpl;
import com.graduate.towercranewaring.csq.pojo.taji_working;
import com.graduate.towercranewaring.csq.pojo.taji_working_packing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: Taji_workingServiceImpl
 * @Description:
 * @Author:csq
 * @Date 2021/4/14
 * @Version 1.0
 **/
@Service
public class Taji_workingServiceImpl implements Taji_workingService{

    @Autowired
    private Taji_workingDaoImpl taji_workingDao;
    @Autowired
    private EquipmentDaoImpl equipmentDao;
    @Autowired
    private DriverDaoImpl driverDao;

    @Override
    public List<taji_working_packing> getAllTaji_working() {
        List<taji_working> taji_workings=taji_workingDao.getAllTajiworking();
        List<taji_working_packing> taji_working_packings=new ArrayList<>();
        for(int i=0;i<taji_workings.size();i++){
            taji_working_packings.add(new taji_working_packing(taji_workings.get(i),
                    equipmentDao.getEquipmentBySn(taji_workings.get(i).getSn()),
                    driverDao.returnDriverWithImgBase64(driverDao.getDriverById(taji_workings.get(i).getDriver_id()))));
        }


        return taji_working_packings;
    }
}
