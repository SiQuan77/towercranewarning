package com.graduate.towercranewaring.csq.service;

import com.graduate.towercranewaring.csq.dao.*;
import com.graduate.towercranewaring.csq.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @ClassName: Alert_informationServiceImpl
 * @Description:
 * @Author:csq
 * @Date 2021/4/15
 * @Version 1.0
 **/
@Service
public class Alert_informationServiceImpl implements Alert_informationService{
    @Autowired
    private DriverDaoImpl driverDao;

    @Autowired
    private EquipmentDaoImpl equipmentDao;
    @Autowired
    private Sjj_workingDaoImpl sjj_workingDao;
    @Autowired
    private Taji_workingDaoImpl taji_workingDao;
    @Autowired
    private Alert_informationDaoImpl alert_informationDao;

//   给一个alert_information返回alert_information的包装类
    @Override
    public alert_information_packing returnPackingAlert(alert_information alert_information) {
//        因为单凭alertinformation中的idworking无法判别是升降机的工作记录预警还是塔机的工作记录预警，故进行两次查询
        sjj_working sjj_working=sjj_workingDao.getSjj_workingById(alert_information.getId_of_working());
        taji_working taji_working=taji_workingDao.getTaji_workingById(alert_information.getId_of_working());

        if(sjj_working!=null){
            return new alert_information_packing(alert_information,driverDao.getDriverById(sjj_working.getDriver_id()),
                    equipmentDao.getEquipmentBySn(sjj_working.getSn()));
        }else if (taji_working!=null){
            return new alert_information_packing(alert_information,driverDao.getDriverById(taji_working.getDriver_id()),
                    equipmentDao.getEquipmentBySn(taji_working.getSn()));
        }
        return null;
    }




    @Override
    public List<alert_information_packing> getAllAlertList() {
        HashMap<sjj_working, String> alertSjj_working = sjj_workingDao.getAlertSjj_working();
        HashMap<taji_working, String> alertTaji_working = taji_workingDao.getAlertTaji_working();
        List<alert_information> alert_informations=alert_informationDao.getAllAlertInformation();
        int sum_of_alert=alert_informations.size();
        List<alert_information_packing> return_list=new ArrayList<>();

        //先把已有的报警记录添加进去
        for(int i=0;i<alert_informations.size();i++){
            return_list.add(returnPackingAlert(alert_informations.get(i)));
        }


//        下面遍历两个hashmap

        Iterator<Map.Entry<sjj_working, String>> iter1=alertSjj_working.entrySet().iterator();
        while(iter1.hasNext()){
            Map.Entry entry= iter1.next();
            sjj_working key= (sjj_working) entry.getKey();
            String val= (String) entry.getValue();

            if(key.getIf_include()==0&& !val.equals("")){//如果该升降机工作记录未被记录进报警表,并且有报警信息
                alert_information new_alert=new alert_information("alert_"+ (++sum_of_alert),key.getId(),val);
                if(alert_informationDao.InsertAlertInformation(new_alert)){//如果添加预警信息成功
                    //将sjj_working表中的if_include置1
                    key.setIf_include(1);
                    sjj_workingDao.updateSjj_working(key);
                    return_list.add(returnPackingAlert(new_alert));
                }
            }
        }

        Iterator<Map.Entry<taji_working, String>> iter2=alertTaji_working.entrySet().iterator();
        while(iter2.hasNext()){
            Map.Entry entry= iter2.next();
            taji_working key= (taji_working) entry.getKey();
            String val2= (String) entry.getValue();

            if(key.getIf_include()==0&& !val2.equals("")){//如果该塔机工作记录未被记录进报警表,并且有报警信息
                alert_information new_alert=new alert_information("alert_"+ (++sum_of_alert),key.getId(),val2);
                if(alert_informationDao.InsertAlertInformation(new_alert)){//如果添加预警信息成功
                    key.setIf_include(1);
                    taji_workingDao.updateTaji_working(key);
                    return_list.add(returnPackingAlert(new_alert));
                }
            }
        }


        return return_list;
    }


}
