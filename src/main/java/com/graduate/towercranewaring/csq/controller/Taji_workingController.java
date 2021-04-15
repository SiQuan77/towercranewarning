package com.graduate.towercranewaring.csq.controller;

import com.graduate.towercranewaring.csq.service.Taji_workingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
        model.addAttribute("taji_working",taji_workingService.getAllTaji_working());
        return "/equipment/taji_working";
    }
}
