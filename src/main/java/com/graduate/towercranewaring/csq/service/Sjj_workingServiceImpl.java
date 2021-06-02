package com.graduate.towercranewaring.csq.service;

import com.graduate.towercranewaring.csq.dao.Alert_informationDaoImpl;
import com.graduate.towercranewaring.csq.dao.DriverDaoImpl;
import com.graduate.towercranewaring.csq.dao.EquipmentDaoImpl;
import com.graduate.towercranewaring.csq.dao.Sjj_workingDaoImpl;
import com.graduate.towercranewaring.csq.pojo.equipment;
import com.graduate.towercranewaring.csq.pojo.sjj_working;
import com.graduate.towercranewaring.csq.pojo.sjj_working_packing;
import com.graduate.towercranewaring.csq.utils.DateUtils;
import com.graduate.towercranewaring.csq.utils.RandomUtils;
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
    private Alert_informationDaoImpl alert_informationDao;

    @Autowired
    private DriverDaoImpl driverDao;

    @Override
    public List<sjj_working_packing> getAllSjj_Working() {
        List<sjj_working_packing> list=new ArrayList<>();
        List<sjj_working> allSjj_working = sjj_workingDao.getAllSjj_Working();

        for(int i=0;i<allSjj_working.size();i++){

            System.out.println(allSjj_working.get(i).getSn());
            list.add(new sjj_working_packing(allSjj_working.get(i),
                    equipmentDao.getEquipmentBySn(allSjj_working.get(i).getSn()),
                    driverDao.returnDriverWithImgBase64(driverDao.getDriverById(allSjj_working.get(i).getDriver_id()))));
        }
        return list;
    }

    @Override
    public boolean insertSjj_working(sjj_working sjj_working) {

        return sjj_workingDao.insertSjj_working(sjj_working);
    }

    @Override
    public sjj_working insertSjj_working_Automatically(String sn) {
        int sum_of_sjjworking_houzhui=sjj_workingDao.getAllSjj_Working().size()+1;
        String id_sjj_working="sjj_working"+sum_of_sjjworking_houzhui;
        int top_limit= RandomUtils.ReturnRandomNumber(100,120);
        int low_limit=0;
        int tilt_range=RandomUtils.ReturnRandomNumber(0,45);
        int height=RandomUtils.ReturnRandomNumber(50,102);
        int lock_conditon;
        if(RandomUtils.ReturnRandomNumber(0,100)%2==0){
            lock_conditon=0;
        }else {
            lock_conditon=1;
        }
        String driver_id="d_1";
        String time= DateUtils.getTime();
        sjj_working sjjWorking=new sjj_working(id_sjj_working,sn,top_limit,low_limit,tilt_range,lock_conditon,height,driver_id,time,0);
        insertSjj_working(sjjWorking);
        return sjjWorking;
    }

    @Override
    public boolean deleteEquipment(String sn) {
        List<sjj_working> list_workBySn = sjj_workingDao.getWorkBySn(sn);

        for(int i=0;i<list_workBySn.size();i++){
            //先删预警信息
            alert_informationDao.deleteAlertInformationByWorkId(list_workBySn.get(i).getId());
            //再删升降机工作记录
            sjj_workingDao.deleteSjj_working(list_workBySn.get(i));
        }
        return false;
    }
}
