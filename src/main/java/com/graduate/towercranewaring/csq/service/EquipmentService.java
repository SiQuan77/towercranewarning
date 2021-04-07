package com.graduate.towercranewaring.csq.service;

import com.graduate.towercranewaring.csq.pojo.equipment;

import java.util.List;

public interface EquipmentService {
    List<equipment> getAllEquip();
    equipment getEquipmentBySn(String equip_sn);
    boolean deleteEquipmentBySn(String equip_sn);
    boolean updateEquipmentByEquip(equipment equipment);
}
