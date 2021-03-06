package com.graduate.towercranewaring.csq.service;

import com.graduate.towercranewaring.csq.dao.EquipmentDao;
import com.graduate.towercranewaring.csq.dao.Sjj_workingDao;
import com.graduate.towercranewaring.csq.dao.Taji_workingDao;
import com.graduate.towercranewaring.csq.pojo.equipment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: EquipmentServiceImpl
 * @Description:
 * @Author:csq
 * @Date 2021/4/7
 * @Version 1.0
 **/
@Service
public class EquipmentServiceImpl implements EquipmentService{
    @Autowired
    private EquipmentDao equipmentDao;

    @Autowired
    private Sjj_workingDao sjj_workingDao;

    @Autowired
    private Taji_workingDao taji_workingDao;
    @Autowired
    private Sjj_workingService sjj_workingService;

    @Autowired
    private Taji_workingService taji_workingService;

    @Override
    public List<equipment> getAllEquip() {
        return equipmentDao.getAllEquip();
    }

    @Override
    public equipment getEquipmentBySn(String equip_sn) {
        return equipmentDao.getEquipmentBySn(equip_sn);
    }

    @Override
    public boolean deleteEquipmentBySn(String equip_sn) {
        equipment equipment=getEquipmentBySn(equip_sn);
        if(equipment!=null){
            if(equipment.getXinxihao().startsWith("SJJ")){
                sjj_workingService.deleteEquipment(equip_sn);
            }else if(equipment.getXinxihao().startsWith("TJ")){
                taji_workingService.deleteEquipBySn(equip_sn);
            }
        }else {
            return false;
        }

        return equipmentDao.deleteEquipmentBySn(equip_sn);
    }

    @Override
    public boolean updateEquipmentByEquip(equipment equipment) {
        return equipmentDao.updateEquipmentByEquip(equipment);
    }

    @Override
    public boolean addEquipment(equipment equipment) {
        return equipmentDao.addEquipment(equipment);
    }

    @Override
    public List<equipment> searchEquipment(String equip_name, String equip_sn, String equip_type, String equip_cqdw, String equip_azdw) {
        return equipmentDao.searchEquipment(equip_name,equip_sn,equip_type,equip_cqdw,equip_azdw);
    }

    @Override
    public boolean dingweiEquip(String equip_sn, int x, int y) {
        return equipmentDao.dingweiEquip(equip_sn,x,y);
    }
}
