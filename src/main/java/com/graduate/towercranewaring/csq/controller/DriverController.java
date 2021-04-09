package com.graduate.towercranewaring.csq.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;


/**
 * @ClassName: DriverController
 * @Description:
 * @Author:csq
 * @Date 2021/4/9
 * @Version 1.0
 **/
@Controller
public class DriverController {
//    从配置文件中读取文件应该存储的位置，这样就方便修改文件存储位置了
    @Value("${web.upload-path}")
    private String upload_path;


    @RequestMapping("/driver/Todriverlist")
    public String Initialize_DriverList(){

        return "/driver/driver_list";
    }


    @PostMapping("/driver/upload_headimg")
    public String upload(@RequestParam("head_img")MultipartFile headimg, Model model) throws Exception{

//        if(!headimg.isEmpty()){
//            String originFileName=headimg.getOriginalFilename();
//            headimg.transferTo(new File(upload_path+"\\"+originFileName));
//        }

        model.addAttribute("filename","/imgs/driver_head/001.jpg");

        return "/driver/driver_list";
    }
}
