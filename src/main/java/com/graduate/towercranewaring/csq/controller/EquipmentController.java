package com.graduate.towercranewaring.csq.controller;

import com.graduate.towercranewaring.csq.pojo.equipment;
import com.graduate.towercranewaring.csq.service.EquipmentService;
import com.sun.org.glassfish.gmbal.ParameterNames;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @ClassName: EquipmentController
 * @Description: 对有关设备的操作的controller
 * @Author:csq
 * @Date 2021/4/7
 * @Version 1.0
 **/
@Controller
public class EquipmentController {
    @Autowired
    private EquipmentService equipmentService;

    @RequestMapping("/equip/ToequipList")
    public String ToGetEquipmentList(Model model){
        model.addAttribute("equip_list",equipmentService.getAllEquip());
        return "/equipment/equipment_list";
    }

    @RequestMapping("/equip/delete")
    public String DeleteEquipmentBySn(HttpServletRequest request){
        String sn=request.getParameter("sn");

        equipmentService.deleteEquipmentBySn(sn);
        System.out.println("进来了！！！！！！！");
        return "/equipment/equipment_list";
    }

    @PostMapping("/equip/update")
    public String UpdateEquipmentBySn(@RequestParam(value = "tanchaung_sn",required = true)String sn,
                                      @RequestParam(value = "tanchaung_equip_name",required = true) String equip_name,
                                      @RequestParam(value = "tanchuang_xinxihao",required = true)String xinxihao,
                                      @RequestParam(value = "tanchuang_type") String type,
                                      @RequestParam(value = "tanchuang_sbcqdw") String sbcqdw,
                                      @RequestParam(value = "tanchuang_xinyongdaima") String xinyongdaima,
                                      @RequestParam(value = "tanchaung_azdw") String install_dw,
                                      @RequestParam(value = "tanchuang_installtime")String install_time,
                                      @RequestParam(value = "tanchaung_dismantle")String dismantle_time,
                                      @RequestParam(value = "tanchuang_ccdw")String dismantle_dw){
        System.out.println("进来啦！");
        String dismantle_time2;
        if(dismantle_time=="1900-01-01"){
            dismantle_time2=null;
        }else{
            dismantle_time2=dismantle_time;
        }
        String dismantle_dw2;
        if(dismantle_dw==null||dismantle_dw=="dismantle_contractor"){
            dismantle_dw2=null;
        }else {
            dismantle_dw2=dismantle_dw;
        }
        equipment new_equipment=new equipment(sn,equip_name,xinxihao,type,sbcqdw,xinyongdaima,
                install_dw,install_time,dismantle_dw2,dismantle_time2);
        if(equipmentService.updateEquipmentByEquip(new_equipment)){
            System.out.println("成功！");
        }else {
            System.out.println("失败！");
        }

        return "redirect:/equip/ToequipList";
    }
}
