package com.graduate.towercranewaring.csq.controller;

import com.graduate.towercranewaring.csq.pojo.driver;
import com.graduate.towercranewaring.csq.service.DriverService;
import com.graduate.towercranewaring.csq.service.DriverServiceImpl;
import com.graduate.towercranewaring.csq.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;


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

    @Autowired
    private DriverServiceImpl driverService;

    @RequestMapping("/driver/Todriverlist")
    public String Initialize_DriverList(Model model){
        model.addAttribute("driver_list",driverService.getAllDriver());


        return "/driver/driver_list";
    }


    @PostMapping("/driver/upload_headimg")
    public String upload(@RequestParam("head_img")MultipartFile headimg, Model model) throws Exception{
        if(!headimg.isEmpty()){
            String originFileName=headimg.getOriginalFilename();
            headimg.transferTo(new File(upload_path+"\\"+originFileName));
        }
        return "/driver/driver_list";
    }
    @PostMapping("/driver/update")
    public String update(@RequestParam("tanchaung_driver_id") String driver_id,
                         @RequestParam("tanchaung_name") String driver_name,
                         @RequestParam("tanchuang_age") int driver_age,
                         @RequestParam("tanchuang_gender") String driver_gender,
                         @RequestParam("tanchuang_id_card") String driver_id_card,
                         @RequestParam("tanchuang_tel") String driver_tel,
                         @RequestParam(value = "tanchuang_photo",required = false) MultipartFile photo) throws IOException {
//        若未上传照片，则为null
/*
 * @Author:csq
 * @Description 因为springboot提交图片后，没法及时显示，需要重启idea才行。所以对于本地文件，直接覆盖即可，在进行展示的时候，现将图片转换为
 * base64格式，html直接渲染base64格式的图片即可！（转图片至base64的操作在service进行，本地数据库直接存储图片的绝对路径即可！）
 * @Date 22:53 2021/4/9
 * @Param [driver_id, driver_name, driver_age, driver_gender, driver_id_card, driver_tel, photo]
 * @return java.lang.String
 **/
        String photo_path=null;

        if(photo.isEmpty()){//若上传photo为空
            driverService.updateDriver(new driver(driver_id,driver_name,driver_age,driver_gender,driver_id_card,driver_tel,
                    null));
        }else {
            if(driverService.getDriverById(driver_id).getPhoto()==null){//如果用户本来就没上传照片
                photo_path=upload_path+"\\"+driverService.getDriverById(driver_id).getDriver_id()+".jpg";
                photo.transferTo(new File(photo_path));
            }else {
                photo.transferTo(new File(driverService.getDriverById(driver_id).getPhoto()));
            }
            driverService.updateDriver(new driver(driver_id,driver_name,driver_age,driver_gender,driver_id_card,
                    driver_tel,photo_path));
        }


        return "redirect:/driver/Todriverlist";
    }


    @PostMapping("/driver/add")
    public String add(@RequestParam("tanchaung_name2") String driver_name,
                      @RequestParam("tanchuang_age2") int driver_age,
                      @RequestParam("tanchuang_gender2") String driver_gender,
                      @RequestParam("tanchuang_id_card2") String driver_id_card,
                      @RequestParam("tanchuang_tel2") String driver_tel,
                      @RequestParam(value = "tanchuang_photo2",required = false) MultipartFile photo)throws IOException{
        int id_suffix=driverService.getAllDriver().size()+1;
        String driver_id="d_"+id_suffix;

        if(photo.isEmpty()){
            driverService.addDriver(new driver(driver_id,driver_name,driver_age,
                    driver_gender,driver_id_card,driver_tel,null));
        }else {
            String photo_path=upload_path+"\\"+driver_id+".jpg";

            photo.transferTo(new File(photo_path));
            driverService.addDriver(new driver(driver_id,driver_name,driver_age,
                    driver_gender,driver_id_card,driver_tel,photo_path));
        }

        return "redirect:/driver/Todriverlist";
    }

    @RequestMapping("/driver/delete")
    public String delete_driver(@RequestParam("driver_id") String driver_id){
        System.out.println("进入deletedriver了！");
        driverService.deleteDriverById(driver_id);
        return "/driver/driver_list";
    }


}
