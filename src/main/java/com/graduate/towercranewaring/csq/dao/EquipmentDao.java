package com.graduate.towercranewaring.csq.dao;

import com.graduate.towercranewaring.csq.pojo.equipment;

import java.util.List;

public interface EquipmentDao {
    List<equipment> getAllEquip();
    equipment getEquipmentBySn(String equip_sn);
    boolean deleteEquipmentBySn(String equip_sn);
    boolean updateEquipmentByEquip(equipment equipment);
    boolean addEquipment(equipment equipment);
}
