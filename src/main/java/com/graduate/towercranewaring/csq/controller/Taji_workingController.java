package com.graduate.towercranewaring.csq.controller;

import com.graduate.towercranewaring.csq.pojo.sjj_working_packing;
import com.graduate.towercranewaring.csq.pojo.taji_working_packing;
import com.graduate.towercranewaring.csq.service.Taji_workingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @ClassName: Taji_workingController
 * @Description:
 * @Author:csq
 * @Date 2021/4/14
 * @Version 1.0
 **/
@Controller
public class Taji_workingController {
    @Autowired
    private Taji_workingServiceImpl taji_workingService;

    @RequestMapping("/equip/To_taji_working")
    public String To_taji_working(Model model){

        List<taji_working_packing> arrayList = taji_workingService.getAllTaji_working();
        for(int i=0,j=arrayList.size()-1;i<j;i++,j--) {
            taji_working_packing temp = arrayList.get(i);
            arrayList.set(i, arrayList.get(j));
            arrayList.set(j, temp);
        }
        model.addAttribute("taji_working",arrayList);
        return "/equipment/taji_working";
    }
}
