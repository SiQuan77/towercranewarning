package com.graduate.towercranewaring.csq.controller;

import com.graduate.towercranewaring.csq.dao.Sjj_workingDaoImpl;
import com.graduate.towercranewaring.csq.pojo.*;
import com.graduate.towercranewaring.csq.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.support.SimpleTriggerContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: ShowController
 * @Description:
 * @Author:csq
 * @Date 2021/5/7
 * @Version 1.0
 **/
@Controller
public class ShowController {

    @Autowired
    private Project_informationServiceImpl project_informationService;

    @Autowired
    private Alert_informationServiceImpl alert_informationService;

    @Autowired
    private DriverServiceImpl driverService;

    @Autowired
    private EquipmentServiceImpl equipmentService;

    @Autowired
    private Taji_workingService taji_workingService;

    @Autowired
    private Sjj_workingService sjj_workingService;

    @RequestMapping("/show/ToBigShow")
    public String ToBigShow(Model model){
        project_information project= project_informationService.GetProject();
        model.addAttribute("project_information",project);

        ArrayList<Integer> arrayList = alert_informationService.returnAlert();
        model.addAttribute("chaozhong",arrayList.get(0));
        model.addAttribute("lijuyujing",arrayList.get(1));
        model.addAttribute("shangxianwei",arrayList.get(2));
        model.addAttribute("qingxie",arrayList.get(3));
        model.addAttribute("shangsuo",arrayList.get(4));

        List<alert_information_packing> allAlertList = alert_informationService.getAllAlertList();
        int alert_taji=0;
        int alert_sjj=0;//分别代表塔机和升降机发出预警的次数

        for(int i=0;i<allAlertList.size();i++){
            if(allAlertList.get(i).getEquipment().getXinxihao().startsWith("SJJ")){
                alert_sjj++;
            }
            if(allAlertList.get(i).getEquipment().getXinxihao().startsWith("TJ")){
                alert_taji++;
            }
        }

        model.addAttribute("alert_taji_cishu",alert_taji);
        model.addAttribute("alert_sjj_cishu",alert_sjj);


        List<equipment> allEquip = equipmentService.getAllEquip();
        int sum_sjj=0;
        int sum_taji=0;
        for(int i=0;i<allEquip.size();i++){
           if(allEquip.get(i).getXinxihao().startsWith("SJJ")){
               sum_sjj++;
           }
           if(allEquip.get(i).getXinxihao().startsWith("TJ")){
               sum_taji++;
           }
        }

        model.addAttribute("sum_taji",sum_taji);
        model.addAttribute("sum_sjj",sum_sjj);
        model.addAttribute("sum_driver",driverService.getAllDriver().size());
        return "show/bigshow";
    }

    @RequestMapping("/show/ToShow2")
    public String ToShow2(Model model){
        List<equipment> allEquip = equipmentService.getAllEquip();
        List<equipment> all_taji=new ArrayList<>();
        List<equipment> all_sjj=new ArrayList<>();
        for (int i=0;i<allEquip.size();i++){
            if(allEquip.get(i).getXinxihao().startsWith("TJ")){
                all_taji.add(allEquip.get(i));
            }else if(allEquip.get(i).getXinxihao().startsWith("SJJ")){
                all_sjj.add(allEquip.get(i));
            }
        }
        model.addAttribute("all_taji",all_taji);
        model.addAttribute("all_sjj",all_sjj);
        return "/show/show2";
    }



    @PostMapping("/show/getData")
    @ResponseBody
    public taji_working_packing getData(@RequestParam("sn") String sn, @RequestParam("type") String type){
        System.out.println("后台传过来塔机的sn是"+sn);
        List<String> stringList=new ArrayList<>();
        if(type.equals("taji")){
           return taji_workingService.insert_new_working_automatically(sn);
        }
        return null;
    }

    @PostMapping("/show/getData2")
    @ResponseBody
    public sjj_working getData2(@RequestParam("sn") String sn){
        System.out.println("后台传过来升降机的sn是"+sn);
        return sjj_workingService.insertSjj_working_Automatically(sn);
    }



}
