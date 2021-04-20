package com.graduate.towercranewaring.csq.controller;

import com.graduate.towercranewaring.csq.pojo.equipment;
import com.graduate.towercranewaring.csq.service.EquipmentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: MapController
 * @Description:
 * @Author:csq
 * @Date 2021/4/19
 * @Version 1.0
 **/
@Controller
public class MapController {

    @Autowired
    private EquipmentServiceImpl equipmentService;

    @RequestMapping("/map/ToMap")
    public String ToMap(Model model){
        List<equipment> allEquip = equipmentService.getAllEquip();
        List<equipment> taji_list=new ArrayList<>();
        List<equipment> sjj_list=new ArrayList<>();
        for(int i=0;i<allEquip.size();i++){
            if(allEquip.get(i).getXinxihao().startsWith("TJ")){
                taji_list.add(allEquip.get(i));
            }else if(allEquip.get(i).getXinxihao().startsWith("SJJ")){
                sjj_list.add(allEquip.get(i));
            }
        }
        model.addAttribute("taji_list",taji_list);
        model.addAttribute("sjj_list",sjj_list);
        return "map/map";
    }
}
