package com.graduate.towercranewaring.csq.service;

import com.graduate.towercranewaring.csq.dao.DriverDaoImpl;
import com.graduate.towercranewaring.csq.dao.EquipmentDaoImpl;
import com.graduate.towercranewaring.csq.dao.Taji_workingDaoImpl;
import com.graduate.towercranewaring.csq.pojo.taji_working;
import com.graduate.towercranewaring.csq.pojo.taji_working_packing;
import com.graduate.towercranewaring.csq.utils.DateUtils;
import com.graduate.towercranewaring.csq.utils.RandomUtils;
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

    @Override
    public taji_working_packing insert_new_working_automatically(String sn) {
        int sum_working=taji_workingDao.getAllTajiworking().size()+1;
        String id="taji_"+sum_working;


        int maximum_payload= 230;
        int height=RandomUtils.ReturnRandomNumber(1,100);
        int loading_pay=RandomUtils.ReturnRandomNumber(150,240);
        int rang_size=RandomUtils.ReturnRandomNumber(10,30);
        int moment=RandomUtils.ReturnRandomNumber(5,8);
        String driver_id="d_1";
        String start_time= DateUtils.getTime();
        String end_time=DateUtils.getTime2();


        taji_working tajiWorking=new taji_working(id,sn,start_time,end_time,maximum_payload,height,loading_pay,rang_size,moment,driver_id,0);

        taji_workingDao.insertTaji_working(tajiWorking);

        taji_working_packing tajiWorkingPacking=new taji_working_packing();
        tajiWorkingPacking.setTaji_working(tajiWorking);
        tajiWorkingPacking.setDriver(driverDao.returnDriverWithImgBase64(driverDao.getDriverById(tajiWorking.getDriver_id())));
        tajiWorkingPacking.setEquipment(equipmentDao.getEquipmentBySn(tajiWorking.getSn()));

        return tajiWorkingPacking;
    }

    @Override
    public boolean insertTaji_working(taji_working taji_working) {
        return taji_workingDao.insertTaji_working(taji_working);
    }
}
