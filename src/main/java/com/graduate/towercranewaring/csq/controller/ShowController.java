package com.graduate.towercranewaring.csq.controller;

import com.graduate.towercranewaring.csq.pojo.alert_information_packing;
import com.graduate.towercranewaring.csq.pojo.equipment;
import com.graduate.towercranewaring.csq.pojo.project_information;
import com.graduate.towercranewaring.csq.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
