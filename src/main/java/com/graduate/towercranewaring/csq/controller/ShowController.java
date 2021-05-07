package com.graduate.towercranewaring.csq.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName: ShowController
 * @Description:
 * @Author:csq
 * @Date 2021/5/7
 * @Version 1.0
 **/
@Controller
public class ShowController {


    @RequestMapping("/show/ToBigShow")
    public String ToBigShow(){
        return "show/bigshow";
    }
}
