package com.graduate.towercranewaring.csq.controller;

import com.graduate.towercranewaring.csq.pojo.equipment;
import com.graduate.towercranewaring.csq.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;


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
        System.out.println("进来了！");
        return "/equipment/equipment_list";
    }

    @RequestMapping("/equip/delete")
    public String DeleteEquipmentBySn(HttpServletRequest request){
        String sn=request.getParameter("sn");
        equipmentService.deleteEquipmentBySn(sn);
        System.out.println("进入delete");
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
                                      @RequestParam(value = "tanchuang_ccdw")String dismantle_dw,
                                      @RequestParam(value = "tanchuang_left")int left,
                                      @RequestParam(value = "tanchuang_top") int top){
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
                install_dw,install_time,dismantle_dw2,dismantle_time2,left,top);
        if(equipmentService.updateEquipmentByEquip(new_equipment)){
            System.out.println("成功！");
        }else {
            System.out.println("失败！");
        }

        return "redirect:/equip/ToequipList";
    }


    @PostMapping("/equip/add")
    public String AddEquipmentBySn(@RequestParam(value = "tanchuang_sn2",required = true)String sn,
                                      @RequestParam(value = "tanchaung_equip_name2",required = true) String equip_name,
                                      @RequestParam(value = "tanchuang_xinxihao2",required = true)String xinxihao,
                                      @RequestParam(value = "tanchuang_type2") String type,
                                      @RequestParam(value = "tanchuang_sbcqdw2") String sbcqdw,
                                      @RequestParam(value = "tanchuang_xinyongdaima2") String xinyongdaima,
                                      @RequestParam(value = "tanchaung_azdw2") String install_dw,
                                      @RequestParam(value = "tanchuang_installtime2")String install_time,
                                      @RequestParam(value = "tanchaung_dismantle2")String dismantle_time,
                                      @RequestParam(value = "tanchuang_ccdw2")String dismantle_dw,
                                        @RequestParam(value = "tanchuang_left2")int left,
                                        @RequestParam(value = "tanchuang_top2") int top){
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
                install_dw,install_time,dismantle_dw2,dismantle_time2,left,top);
        if(equipmentService.addEquipment(new_equipment)){
            System.out.println("成功！");
        }else {
            System.out.println("失败！");
        }

        return "redirect:/equip/ToequipList";
    }


    @PostMapping("/equip/search")
    public String search(@RequestParam(value = "search_equip_name",required = false)String equip_name,
                         @RequestParam(value = "search_equip_sn",required = false) String equip_sn,
                         @RequestParam(value = "search_equip_type",required = false) String equip_type,
                         @RequestParam(value = "search_equip_cqdw",required = false) String equip_cqdw,
                         @RequestParam(value = "search_equip_azdw",required = false) String equip_azdw,
                         Model model){

        if(equip_name.equals("")){
            equip_name=null;
        }
        if(equip_sn.equals("")){
            equip_sn=null;
        }
        if(equip_type.equals("")){
            equip_type=null;
        }
        if(equip_cqdw.equals("")){
            equip_cqdw=null;
        }
        if(equip_azdw.equals("")){
            equip_azdw=null;
        }
        model.addAttribute("equip_list",equipmentService.searchEquipment(equip_name,equip_sn,equip_type,equip_cqdw,equip_azdw));



        return "/equipment/equipment_list";
    }

}
