package com.graduate.towercranewaring.csq.controller;

import com.graduate.towercranewaring.csq.pojo.sjj_working_packing;
import com.graduate.towercranewaring.csq.service.Sjj_workingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @ClassName: Sjj_workingController
 * @Description:
 * @Author:csq
 * @Date 2021/4/13
 * @Version 1.0
 **/
@Controller
public class Sjj_workingController {
    @Autowired
    private Sjj_workingServiceImpl sjj_workingService;



    @RequestMapping("/equip/To_sjj_working")
    public String To_sjj_working(Model model){
        List<sjj_working_packing> arrayList = sjj_workingService.getAllSjj_Working();
        for(int i=0,j=arrayList.size()-1;i<j;i++,j--) {
            sjj_working_packing temp = arrayList.get(i);
            arrayList.set(i, arrayList.get(j));
            arrayList.set(j, temp);
        }
        model.addAttribute("sjj_working",arrayList);

        return "/equipment/sjj_working";
    }
}
