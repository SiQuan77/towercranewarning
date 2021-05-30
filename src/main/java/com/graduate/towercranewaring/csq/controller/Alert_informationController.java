package com.graduate.towercranewaring.csq.controller;

import com.graduate.towercranewaring.csq.pojo.alert_information_packing;
import com.graduate.towercranewaring.csq.pojo.sjj_working_packing;
import com.graduate.towercranewaring.csq.service.Alert_informationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @ClassName: Alert_informationController
 * @Description:
 * @Author:csq
 * @Date 2021/4/15
 * @Version 1.0
 **/
@Controller
public class Alert_informationController {

    @Autowired
    private Alert_informationServiceImpl alert_informationService;

    @RequestMapping("/alert/ToAlertList")
    public String To_Alert_information_List(Model model){
        List<alert_information_packing> arrayList = alert_informationService.getAllAlertList();
        for(int i=0,j=arrayList.size()-1;i<j;i++,j--) {
            alert_information_packing temp = arrayList.get(i);
            arrayList.set(i, arrayList.get(j));
            arrayList.set(j, temp);
        }
        model.addAttribute("list_of_alert",arrayList);
        return "/alert/alert_information";
    }
}
