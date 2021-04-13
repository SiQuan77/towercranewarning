package com.graduate.towercranewaring.csq.controller;

import com.graduate.towercranewaring.csq.service.Sjj_workingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
        model.addAttribute("sjj_working",sjj_workingService.getAllSjj_Working());

        return "/equipment/sjj_working";
    }
}
